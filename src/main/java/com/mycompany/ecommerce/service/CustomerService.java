package com.mycompany.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.CustomerDao;
import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.dto.Customer;
import com.mycompany.ecommerce.dto.CustomerProduct;
import com.mycompany.ecommerce.dto.MerchantProduct;
import com.mycompany.ecommerce.dto.PaymentDetails;
import com.mycompany.ecommerce.dto.ShoppingCart;
import com.mycompany.ecommerce.dto.ShoppingOrder;
import com.mycompany.ecommerce.helper.AES;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.helper.MailHelper;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	MailHelper mailHelper;

	@Autowired
	ProductDao productDao;

	@Autowired
	ShoppingOrder order;


	public String signup(Customer customer, ModelMap modelMap) {
		Customer customer1 = customerDao.fetchByEmail(customer.getEmail());
		Customer customer2 = customerDao.fetchByMobile(customer.getMobile());
		if (customer1 == null && customer2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
			customer.setOtp(otp);
			customerDao.save(customer);
			// mailHelper.sendOtp(customer);
			modelMap.put("id", customer.getId());
			return "VerifyOtp2";
		} else {
			if (customer1 != null) {
				if (customer1.isStatus()) {
					modelMap.put("neg", "Email Already Exists");
					return "CustomerSignup";
				} else {
					if (customer2 != null) {
						mailHelper.sendOtp(customer1);
						modelMap.put("id", customer1.getId());
						return "VerifyOtp2";
					} else {
						modelMap.put("neg", "Same Email with Different Number Exists");
						return "CustomerSignup";
					}
				}
			} else {
				modelMap.put("neg", "Phone Number Already Exists");
				return "CustomerSignup";
			}
		}
	}

	public String verfiyOtp(int id, int otp, ModelMap modelMap) {
		Customer customer = customerDao.fetchById(id);
		if (customer == null) {
			modelMap.put("neg", "Something went Wrong");
			return "Main";
		} else {
			if (customer.getOtp() == otp) {
				customer.setStatus(true);
				customerDao.save(customer);
				modelMap.put("pos", "Account Verified Successfully");
				return "Customer";
			} else {
				modelMap.put("neg", "OTP MissMatch");
				modelMap.put("id", id);
				return "VerifyOtp2";
			}
		}
	}

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		Customer customer = customerDao.fetchByEmail(helper.getEmail());
		if (customer == null) {
			map.put("neg", "Incorrect Email");
			return "Customer";
		} else {
			if (AES.decrypt(customer.getPassword(), "123").equals(helper.getPassword())) {
				if (customer.isStatus()) {
					session.setMaxInactiveInterval(150);
					session.setAttribute("customer", customer);
					map.put("pos", "Login Success");
					return "CustomerHome";
				} else {
					map.put("neg", "Verify Your OTP First");
//					mailHelper.sendOtp(customer);
					map.put("id", customer.getId());
					return "VerifyOtp2";
				}
			} else {
				map.put("neg", "Incorrect Password");
				return "Customer";
			}
		}
	}

	public String fetchProducts(ModelMap modelMap, Customer customer) {
		List<MerchantProduct> list = productDao.fetchAprovedProducts();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Products Available");
			return "CustomerHome";
		} else {
			List<CustomerProduct> cartitems = null;
			if (customer.getCart() != null)
			{
				if(customer.getCart().getCustomerProducts() != null)
				cartitems = customer.getCart().getCustomerProducts();
			}
			modelMap.put("cartitems", cartitems);
			modelMap.put("list", list);
			return "CustomerProducts";
		}
	}

	public String addToCart(int id, HttpSession session, Customer customer, ModelMap modelMap) {
		MerchantProduct product = productDao.findById(id);
		if (product != null) {
			if (product.getStock() > 0) {
				ShoppingCart cart = customer.getCart();
				if (cart == null) {
					cart = new ShoppingCart();
					customer.setCart(cart);
				}
				List<CustomerProduct> customerProducts = cart.getCustomerProducts();
				if (customerProducts == null) {
					customerProducts = new ArrayList<CustomerProduct>();
				}

				boolean flag = true;
				for (CustomerProduct customerProduct : customerProducts) {
					if (customerProduct.getName().equals(product.getName())) {
						customerProduct.setQuantity(customerProduct.getQuantity() + 1);
						customerProduct.setPrice(customerProduct.getPrice() + product.getPrice());
						flag = false;
						break;
					}
				}
				if (flag) {
					CustomerProduct customerProduct = new CustomerProduct();
					customerProduct.setName(product.getName());
					customerProduct.setCategory(product.getCategory());
					customerProduct.setPicture(product.getPicture());
					customerProduct.setPrice(product.getPrice());
					customerProduct.setQuantity(1);
					customerProducts.add(customerProduct);
					cart.setCustomerProducts(customerProducts);
				}
				customerDao.save(customer);
				session.setAttribute("customer", customerDao.fetchById(customer.getId()));
				product.setStock(product.getStock() - 1);
				productDao.save(product);

				modelMap.put("pos", "Item Added to Cart");

			} else {
				modelMap.put("neg", "Out of Stock");
			}
			return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
		} else {
			modelMap.put("neg", "Something went Wrong");
			return "Main";
		}
	}

	public String removeFromCart(int id, HttpSession session, Customer customer, ModelMap modelMap) {
		MerchantProduct product = productDao.findById(id);
		if (product != null) {
			ShoppingCart cart = customer.getCart();
			if (cart == null) {
				modelMap.put("neg", "No Items in Cart");
				return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
			} else {
				List<CustomerProduct> list = cart.getCustomerProducts();
				if (list == null) {
					modelMap.put("neg", "No Items in Cart");
					return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
				} else {
					CustomerProduct customerProduct = null;
					for (CustomerProduct customerProduct2 : list) {
						if (product.getName().equals(customerProduct2.getName())) {
							customerProduct = customerProduct2;
							break;
						}
					}
					if (customerProduct == null) {
						modelMap.put("neg", "No Items in Cart");
						return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
					} else {
						if (customerProduct.getQuantity() > 1) {
							customerProduct.setQuantity(customerProduct.getQuantity() - 1);
							customerProduct.setPrice(customerProduct.getPrice() - product.getPrice());
							product.setStock(product.getStock() + 1);
							productDao.save(product);
							productDao.save(customerProduct);
						} else {
							list.remove(customerProduct);
							product.setStock(product.getStock() + 1);
							productDao.save(product);
							productDao.save(cart);
							productDao.delete(customerProduct);
						}
						modelMap.put("pos", "Item removed from Cart");
						session.setAttribute("customer", customerDao.fetchById(customer.getId()));
						return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
					}
				}
			}
		} else {
			modelMap.put("neg", "Something went Wrong");
			return "Main";
		}
	}

	public String viewCart(HttpSession session, Customer customer, ModelMap modelMap) throws RazorpayException {
		ShoppingCart cart = customer.getCart();
		if (cart == null) {
			modelMap.put("neg", "No Items in Cart");
			return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
		} else {
			List<CustomerProduct> list = cart.getCustomerProducts();
			if (list == null || list.isEmpty()) {
				modelMap.put("neg", "No Items in Cart");
				return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
			} else {
				boolean flag = true;
				for (CustomerProduct customerProduct : list) {
					if (customerProduct.getQuantity() > 0)
						flag = false;
					break;
				}
				if (flag) {
					modelMap.put("neg", "No Items in Cart");
					return fetchProducts(modelMap, customerDao.fetchById(customer.getId()));
				} else {
					double amount = 0;
					for (CustomerProduct customerProduct : list) {
						amount = amount + customerProduct.getPrice();
					}

					JSONObject object = new JSONObject();
					object.put("amount", (int) (amount * 100));
					object.put("currency", "INR");

					RazorpayClient client = new RazorpayClient("rzp_test_pXzztvFSoP8U0y", "CSRywILSxpj4nnthtfisyY57");
					Order order = client.orders.create(object);
					PaymentDetails details=new PaymentDetails();
					details.setAmount(order.get("amount").toString());
					details.setCurrency(order.get("currency").toString());
					details.setPaymentId(null);
					details.setOrderId(order.get("id").toString());
					details.setStatus(order.get("status"));
					details.setKeyDetails("rzp_test_pXzztvFSoP8U0y");

					session.setAttribute("customer", customerDao.fetchById(customer.getId()));
					modelMap.put("details", productDao.saveDetails(details));
					modelMap.put("items", list);
					modelMap.put("customer", customerDao.fetchById(customer.getId()));
					return "ViewCart";
				}
			}
		}
	}

	public String checkPayment(int id, Customer customer, String razorpay_payment_id, HttpSession session, ModelMap map)
			throws RazorpayException {
		PaymentDetails details = productDao.find(id);
		if (details == null) {
			map.put("neg", "Something went wrong");
			return "Main";
		} else {
			if (razorpay_payment_id != null) {
				details.setStatus("success");
				details.setTime(LocalDateTime.now());
				details.setPaymentId(razorpay_payment_id);
				productDao.saveDetails(details);

				order.setDateTime(LocalDateTime.now());
				order.setPayment_id(razorpay_payment_id);
				order.setPrice(Double.parseDouble(details.getAmount()) / 100);
				order.setCustomerProducts(customer.getCart().getCustomerProducts());

				List<ShoppingOrder> list = customer.getOrders();
				if (list == null)
					list = new ArrayList<ShoppingOrder>();
				list.add(order);

				customer.setOrders(list);
				customer.setCart(null);
				customerDao.save(customer);
				
				session.setAttribute("customer", customerDao.fetchById(customer.getId()));

				map.put("pos", "Payment Done, Ordere Placed");
				return "CustomerHome";

			} else {
				map.put("neg", "Payment Not Done");
				return viewCart(session, customer, map);
			}
		}
	}

	public String fetchOrders(ModelMap modelMap, Customer customer) {
		List<ShoppingOrder> orders = customer.getOrders();
		if(orders==null || orders.isEmpty())
		{
			modelMap.put("neg", "No Orders Found");
			return "CustomerHome";
		}
		else {
			modelMap.put("orders", orders);
			return "CustomerOrders";
		}
	}

}
