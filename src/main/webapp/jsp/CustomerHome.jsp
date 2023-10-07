<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Home</title>
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

h2 {
	color: green;
}

h3 {
	color: red;
}

.button-container {
	margin-top: 20px;
}

button {
	padding: 10px 20px;
	background-color: #007BFF;
	color: #fff;
	border: none;
	border-radius: 5px;
	margin: 10px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<h1>Customer Home</h1>

	<h2>${pos}</h2>
	<h3>${neg}</h3>

	<div class="button-container">
		<a href="/customer/fetch-products"><button>View Products</button></a>
		<a href="/customer/fetch-orders"><button>View Orders</button></a><a href="/logout"><button>Logout</button></a>
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
