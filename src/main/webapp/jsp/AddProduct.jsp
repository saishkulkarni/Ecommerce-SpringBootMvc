<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	text-align: center;
	margin: 0;
	padding: 0;
}

h1 {
	color: #333;
}

table {
	margin: 20px auto;
	border-collapse: collapse;
	width: 60%;
	max-width: 480px;
	border: 2px solid black;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #007BFF;
	color: #fff;
}

input[type="text"], input[type="number"], input[type="file"] {
	width: 90%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

select {
	width: 97%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

button[type="submit"], button[type="reset"] {
	padding: 10px 20px;
	background-color: #007BFF;
	color: #fff;
	border: none;
	border-radius: 5px;
	margin-right: 10px;
	cursor: pointer;
}

button[type="submit"]:hover, button[type="reset"]:hover {
	background-color: #0056b3;
}

a {
	text-decoration: none;
}

.back-button {
	text-align: center;
	margin-top: 20px;
}
</style>
</head>
<body>
	<h1>Add Product</h1>
	<mvc:form action="/merchant/add-product" method="post"
		modelAttribute="product" enctype="multipart/form-data">
		<table>
			<tr>
				<th>Name:</th>
				<td><mvc:input path="name" /></td>
				<td><mvc:errors path="name" /></td>
			</tr>
			<tr>
				<th>Stock:</th>
				<td><mvc:input path="stock" /></td>
				<td><mvc:errors path="stock" /></td>
			</tr>
			<tr>
				<th>Price:</th>
				<td><mvc:input path="price" /></td>
				<td><mvc:errors path="price" /></td>
			</tr>
			<tr>
				<th>Category:</th>
				<td><mvc:select path="category">
						<mvc:option value="">Select One Option</mvc:option>
						<mvc:option value="Clothing">Clothing</mvc:option>
						<mvc:option value="Electronics">Electronics</mvc:option>
						<mvc:option value="Provisions">Provisions</mvc:option>
					</mvc:select></td>
				<td><mvc:errors path="category" /></td>
			</tr>
			<tr>
				<th>Picture:</th>
				<td><input type="file" name="pic"></td>
				<td></td>
			</tr>
			<tr>
				<th><button type="submit">Add</button></th>
				<th><button type="reset">Cancel</button></th>
			</tr>
		</table>
	</mvc:form>
	<div class="back-button">
		<a href="/merchant/home"><button>Back</button></a>
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
