<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Merchant</title>

<style type="text/css">
body, h1, h2, p {
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

header {
	background-color: #007BFF;
	color: #fff;
	padding: 10px 0;
	text-align: center;
}

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

th input[type="email"], th input[type="password"] {
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

.signup-link {
	text-align: center;
	margin-top: 20px;
}

.signup-link a {
	color: #007BFF;
	text-decoration: none;
}

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
		<h1>Merchant Page</h1>
	</header>

	<div class="container">
		<h3>${pos}</h3>
		<h2>${neg}</h2>
		<form action="/merchant/login" method="post">
			<fieldset>
				<legend>Login Here</legend>
				<table>
					<tr>
						<th>Email:</th>
						<th><input type="email" name="email" required="required" /></th>
					</tr>
					<tr>
						<th>Password:</th>
						<th><input type="password" name="password"
							required="required" /></th>
					</tr>
					<tr>
						<th><button>Login</button></th>
						<th><button type="reset">Cancel</button></th>
					</tr>
				</table>
			</fieldset>
		</form>

		<div class="signup-link">
			<a href="/merchant/signup">New? Click here to Create Account</a>
		</div>

		<div class="back-button">
			<a href="/"><button>Back</button></a>
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
