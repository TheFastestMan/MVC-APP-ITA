
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Product</title>
</head>
<body>
<h1>Product</h1>

<h3>Name: ${product.name}</h3>
<h3>Quantity: ${product.quantity}</h3>

<h1>Add product</h1>
<form action="/productServlet" method="POST">
  <label for="name">Name:</label><br>
  <input type="text" id="name" name="name" value="${product.name}"><br>
  <label for="quantity">Quantity:</label><br>
  <input type="text" id="quantity" name="quantity" value="${product.quantity}"><br>
  <input type="submit" value="add">
</form>
</body>
</html>
