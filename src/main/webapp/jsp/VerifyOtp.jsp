<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/merchant/verify-otp" method="post">
<input type="text" name="id" value="${id}" hidden="hidden">
Enter OTP:<input type="text" name="otp">
<button>submit</button>
</form>
</body>
</html>