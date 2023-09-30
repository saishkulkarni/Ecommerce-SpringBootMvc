<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Products</title>

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
		<h1>Admin Products</h1>
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
					<th>Stock</th>
					<th>Status</th>
					<th>Change Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${list}">
					<tr>
						<td>${product.getName()}</td>
						<td><img class="product-image" alt="Product Image"
							src="data:image/jpeg;base64,${Base64.encodeBase64String(product.getPicture())}">
						</td>
						<td>${product.getCategory()}</td>
						<td>${product.getPrice()}</td>
						<td>${product.getStock()}</td>
						<td>${product.isApproved()}</td>
						<td><a href="/admin/change-status/${product.getId()}"
							class="button-container"><button>Change</button></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="button-container">
			<a href="/admin/home"><button>Back</button></a>
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
</body>
</html>
