<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.javaguru.entity.Product" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Product</title>
</head>
<body>
<h1>Delete product</h1>
<form action="/productDeleteServlet" method="POST">
    <label for="productName">Choose a product:</label>

    <select name="productName" id="productName">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (Product product : products) {
        %>
        <option value="<%= product.getName() %>"><%= product.getName() %>
        </option>
        <% } %>
    </select>

    <br>
    <input type="submit" value="Delete">
</form>

<!-- Display success or error messages -->
<%
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (successMessage != null) {
%>
<p style="color: green;"><%= successMessage %>
</p>
<% }
    if (errorMessage != null) {
%>
<p style="color: red;"><%= errorMessage %>
</p>
<% } %>

</body>
</html>
