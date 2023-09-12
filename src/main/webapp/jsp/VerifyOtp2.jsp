<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verify OTP</title>
</head>
<body>
<h1>Verify Otp Here</h1>
<h1 style="color:green">${pos}</h1>
<h1 style="color:red">${neg}</h1>
<form action="/customer/verify-otp" method="post">
<input type="text" name="id" value="${id}" hidden="hidden">
Enter OTP:<input type="text" name="otp">
<button>submit</button>
</form>
<br>
<a href="/customer/signup"><button>Back</button></a>
</body>
</html>