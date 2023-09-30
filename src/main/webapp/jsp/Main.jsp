<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>E-Commerce</title>

<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

h1, h2, p {
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

header {
	 background-color: #007BFF;
	padding: 10px 0;
	text-align: center;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	margin-top: 20px;
}

h1 {
	color: #F1EFEF;
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

.btn {
	display: inline-block;
	padding: 10px 20px;
	background-color: #007BFF;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<header>
		<h1>E-Commerce</h1>
	</header>

	<div class="container">
		<h3>${pos}</h3>
		<h2>${neg}</h2>
		<a href="/admin" class="btn">Admin</a> <a href="/merchant" class="btn">Merchant</a>
		<a href="/customer" class="btn">Customer</a>
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