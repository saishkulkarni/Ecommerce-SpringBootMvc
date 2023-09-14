<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup</title>
</head>
<body>
	<h1 style="color: green">${pos}</h1>
	<h1 style="color: red">${neg}</h1>
	<h1>Merchant Signup</h1>
	<x:form action="/merchant/signup" method="post"
		modelAttribute="merchant">
		<fieldset>
			<legend>Signup Here,</legend>
			<table>
				<tr>
					<th>Name:</th>
					<th><x:input path="name" /></th>
					<th><span style="color: red"><x:errors path="name" /></span></th>
				</tr>
				<tr>
					<th>Mobile:</th>
					<th><x:input type="tel" path="mobile" /></th>
					<th><span style="color: red"><x:errors path="mobile" /></span></th>
				</tr>
				<tr>
					<th>Email:</th>
					<th><x:input path="email" /></th>
					<th><span style="color: red"><x:errors path="email" /></span></th>
				</tr>
				<tr>
					<th>Password:</th>
					<th><x:password path="password" /></th>
					<th><span style="color: red"><x:errors path="password" /></span></th>
				</tr>
				<tr>
					<th>Date of Birth:</th>
					<th><x:input type="date" path="dob" /></th>
					<th><span style="color: red"><x:errors path="dob" /></span></th>
				</tr>
				<tr>
					<th>Gender:</th>
					<th><x:radiobutton path="gender" value="male" />Male <x:radiobutton
							path="gender" value="female" />Female</th>
					<th><span style="color: red"><x:errors path="gender" /></span></th>
				</tr>
				<tr>
					<th><button>Signup</button></th>
					<th><button type="reset">Cancel</button></th>
					<th></th>
				</tr>
			</table>
		</fieldset>
	</x:form>
	<br>
	<a href="/merchant"><button>Back</button></a>
</body>
</html>