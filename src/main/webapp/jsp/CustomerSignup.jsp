<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Signup</title>

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

/* Form styles */
form {
	margin-top: 20px;
}

fieldset {
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 20px;
}

legend {
	font-size: 24px;
	font-weight: bold;
}

table {
	width: 100%;
}

th {
	text-align: left;
	padding: 10px;
}

th input[type="text"], th input[type="tel"], th input[type="email"], th input[type="password"],
	th input[type="date"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

th button {
	background-color: #007BFF;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
}

th button[type="reset"] {
	background-color: #ccc;
	margin-left: 10px;
}

/* Error message styles */
.error-message {
	color: red;
	font-size: 14px;
}

/* Back button */
.back-button {
	text-align: center;
	margin-top: 20px;
}

.back-button button {
	background-color: #007BFF;
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
		<h1>Customer Signup</h1>
	</header>

	<div class="container">
		<h3>${pos}</h3>
		<h2>${neg}</h2>
		<x:form action="/customer/signup" method="post"
			modelAttribute="customer">
			<fieldset>
				<legend>Signup Here</legend>
				<table>
					<tr>
						<th>Name:</th>
						<th><x:input path="name" /></th>
						<th><div class="error-message">
								<x:errors path="name" />
							</div></th>
					</tr>
					<tr>
						<th>Mobile:</th>
						<th><x:input type="tel" path="mobile" /></th>
						<th><div class="error-message">
								<x:errors path="mobile" />
							</div></th>
					</tr>
					<tr>
						<th>Email:</th>
						<th><x:input path="email" /></th>
						<th><div class="error-message">
								<x:errors path="email" />
							</div></th>
					</tr>
					<tr>
						<th>Password:</th>
						<th><x:password path="password" /></th>
						<th><div class="error-message">
								<x:errors path="password" />
							</div></th>
					</tr>
					<tr>
						<th>Date of Birth:</th>
						<th><x:input type="date" path="dob" /></th>
						<th><div class="error-message">
								<x:errors path="dob" />
							</div></th>
					</tr>
					<tr>
						<th>Gender:</th>
						<th><x:radiobutton path="gender" value="male" />Male <x:radiobutton
								path="gender" value="female" />Female</th>
						<th><div class="error-message">
								<x:errors path="gender" />
							</div></th>
					</tr>
					<tr>
						<th><button>Signup</button></th>
						<th><button type="reset">Cancel</button></th>
						<th></th>
					</tr>
				</table>
			</fieldset>
		</x:form>

		<div class="back-button">
			<a href="/customer"><button>Back</button></a>
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
