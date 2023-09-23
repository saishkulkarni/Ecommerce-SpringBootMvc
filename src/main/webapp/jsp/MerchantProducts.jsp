<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Merchant Products</title>
  </head>
  <body>
    <h1>Merchant Products</h1>
    <table border="1">
      <thead>
        <tr>
          <th>Product Name</th>
          <th>Picture</th>
          <th>Category</th>
          <th>Price</th>
          <th>Stock</th>
          <th>Delete</th>
          <th>Edit</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="product" items="${list}">
          <tr>
            <td>${product.getName()}</td>
            <td></td>
            <td>${product.getCategory()}</td>
            <td>${product.getStock()}</td>
            <td>${product.getPrice()}</td>
            <td><button>Delete</button></td>
            <td><button>Edit</button></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <br />
    <a href="/merchant/home"><button>Back</button></a>
  </body>
</html>
