<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Edit Product</h1>
	<mvc:form action="/merchant/update-product" method="post" modelAttribute="product" enctype="multipart/form-data">
	<mvc:hidden path="id"/>
	<table>
	<tr>
	<th>Name:</th>
	<th><mvc:input path="name"/></th>
	<th><mvc:errors path="name"/></th>
	</tr>
	<tr>
	<th>Stock:</th>
	<th><mvc:input path="stock"/></th>
	<th><mvc:errors path="stock"/></th>
	</tr>
	<tr>
	<th>Price:</th>
	<th><mvc:input path="price"/></th>
	<th><mvc:errors path="price"/></th>
	</tr>
	<tr>
	<th>Category:</th>
	<th>
	<mvc:select path="category">
	<mvc:option value="">Select One Option</mvc:option>
	<mvc:option value="Clothing">Clothing</mvc:option>
	<mvc:option value="Electronics">Electronics</mvc:option>
	<mvc:option value="Provisions">Provisions</mvc:option>
	</mvc:select>
	</th>
	<th><mvc:errors path="category"/></th>
	</tr>
	<tr>
	<th>Picture:</th>
	<th>
	<c:set var="base64"
							value="${Base64.encodeBase64String(product.getPicture())}"></c:set>
				    <img
						height="50px" width="50px" alt="unknown"
						src="data:image/jpeg;base64,${base64}">
	<input type="file" name="pic">
	</th>
	<th></th>
	</tr>
	<tr>
	<th><button>Update</button></th>
	<th><button type="reset">Cancel</button></th>
	</tr>
	</table>
	</mvc:form>
	<br>
	<a href="/merchant/fetch-products"><button>Back</button></a>
</body>
</html>