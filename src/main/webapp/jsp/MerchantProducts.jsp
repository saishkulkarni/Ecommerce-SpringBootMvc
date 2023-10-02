<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Merchant Products</title>
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

        table {
            width: 90%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }

        img {
            max-width: 100px;
            max-height: 100px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 5px 10px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        a {
            text-decoration: none;
        }

        .back-button {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1 style="color: green">${pos}</h1>
    <h1 style="color: red">${neg}</h1>
    <h1>Merchant Products</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Picture</th>
                <th>Category</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${list}">
                <tr>
                    <td>${product.getName()}</td>
                    <td>
                        <c:set var="base64" value="${Base64.encodeBase64String(product.getPicture())}"></c:set>
                        <img height="100px" width="100px" alt="unknown" src="data:image/jpeg;base64,${base64}">
                    </td>
                    <td>${product.getCategory()}</td>
                    <td>${product.getPrice()}</td>
                    <td>${product.getStock()}</td>
                    <td><a href="/merchant/delete/${product.getId()}"><button>Delete</button></a></td>
                    <td><a href="/merchant/edit/${product.getId()}"><button>Edit</button></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br />
    <a href="/merchant/home"><button>Back</button></a>
    
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
