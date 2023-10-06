<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Cart</title>

<style type="text/css">
/* Reset some default styles */
body, h1, h2, p {
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

/* Header styles */
header {
	background-color: #007BFF;
	color: #fff;
	padding: 10px 0;
	text-align: center;
}

/* Page content styles */
.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	margin-top: 20px;
}

h1 {
	color: white;
	font-size: 36px;
	margin-bottom: 20px;
}

h2 {
	color: red;
	font-size: 28px;
}

h3 {
	color: green;
	font-size: 28px;
}

/* Table styles */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
	text-align: center;
}

th {
	background-color: #007BFF;
	color: #fff;
}

/* Image styles */
.product-image {
	max-width: 100px;
	max-height: 100px;
}

a {
	text-decoration: none;
	font-size: 50px;
}

/* Button styles */
.button-container {
	text-align: center;
	margin-top: 20px;
}

.button-container button {
	background-color: #007BFF;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
	margin: 10px;
}

/* Back button style */
.back-button {
	text-align: center;
	margin-top: 20px;
}

.back-button button {
	background-color: #FF4500;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
}
</style>
</head>
<body>
	<header>
		<h1>Cart</h1>
	</header>

	<div class="container">
		<h3>${pos}</h3>
		<h2>${neg}</h2>
		<table>
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Picture</th>
					<th>Category</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${items}">
					<tr>
						<td>${product.getName()}</td>
						<td><img class="product-image" alt="Product Image"
							src="data:image/jpeg;base64,${Base64.encodeBase64String(product.getPicture())}">
						</td>
						<td>${product.getCategory()}</td>
						<td>${product.getPrice()/product.getQuantity()}</td>
						<td>${product.getQuantity()}</td>
						<td>${product.getPrice()}&#8377</td>
						</tr>
				</c:forEach>
				<tr>
				<th colspan="5">Total Price</th>
				<td>${details.getAmount()/100}&#8377</td>
				</tr>
			</tbody>
		</table>

		<div class="button-container">
		<button id="rzp-button1" >Pay</button>
		<a href="/customer/fetch-products"><button>Back</button></a>
		</div>
	</div>
	<script>
		function hideElements() {
			var h2Element = document.querySelector('h2');
			var h3Element = document.querySelector('h3');

			if (h2Element) {
				setTimeout(function() {
					h2Element.style.display = 'none';
				}, 1000);
			}

			if (h3Element) {
				setTimeout(function() {
					h3Element.style.display = 'none';
				}, 1000);
			}
		}
		window.onload = hideElements;
	</script>
	<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
	<script>
		var options = {
			"key" : "${details.getKeyDetails()}", // Enter the Key ID generated from the Dashboard
			"amount" : "${details.getAmount()}", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
			"currency" : "${details.getCurrency()}",
			"name" : "Shopping Cart", //your business name
			"description" : "Test Transaction",
			"image" : "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRllS5xtNEkqUQT5mY5HvLBeKfvwLqsrTl3Zw&usqp=CAU",
			"order_id" : "${details.getOrderId()}", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
			"callback_url" : "/customer/payment/${details.getId()}",
			"prefill" : { //We recommend using the prefill parameter to auto-fill customer's contact information especially their phone number
				"name" : "${customer.getName()}", //your customer's name
				"email" : "${customer.getEmail()}",
				"contact" : "+91${customer.getMobile()}" //Provide the customer's phone number for better conversion rates 
			},
			"notes" : {
				"address" : "Razorpay Corporate Office"
			},
			"theme" : {
				"color" : "#047fc2"
			}
		};
		var rzp1 = new Razorpay(options);
		document.getElementById('rzp-button1').onclick = function(e) {
			rzp1.open();
			e.preventDefault();
		}
	</script>
</body>
</html>
