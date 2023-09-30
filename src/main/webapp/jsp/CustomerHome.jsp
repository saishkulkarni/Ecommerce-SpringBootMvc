<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Home</title>
</head>
<body>
	<h1 style="color: green">${pos}</h1>
	<h1 style="color: red">${neg}</h1>
	<h1>This is Customer Home Page</h1>
	<a href=""><button>View Products</button></a>
	<a href=""><button>View Orders</button></a>
	<a href=""><button>Edit Details</button></a>
	<a href="/logout"><button>Logout</button></a>
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