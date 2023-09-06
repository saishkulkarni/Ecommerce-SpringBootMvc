<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup</title>
</head>
<body>
	<h1>Merchant Signup</h1>
	<form>
		<fieldset>
			<legend>Signup Here,</legend>
			<table>
				<tr>
					<th>Name:</th>
					<th><input type="text" name="name" required="required"></th>
				</tr>
				<tr>
					<th>Mobile:</th>
					<th><input type="tel" name="mobile" pattern="[0-9]{10}"
						required="required"></th>
				</tr>
				<tr>
					<th>Email:</th>
					<th><input type="email" name="email" required="required"></th>
				</tr>
				<tr>
					<th>Password:</th>
					<th><input type="password" name="password" required="required"></th>
				</tr>
				<tr>
					<th>Date of Birth:</th>
					<th><input type="date" name="dob" required="required"></th>
				</tr>
				<tr>
					<th>Gender:</th>
					<th><input type="radio" name="gender" value="male"
						required="required">Male <input type="radio" name="gender"
						value="female">Female</th>
				</tr>
				<tr>
					<th><button>Signup</button></th>
					<th><button type="reset">Cancel</button></th>
				</tr>
			</table>
		</fieldset>
	</form>
	<br>
	<a href="/merchant0"><button>Back</button></a>
</body>
</html>