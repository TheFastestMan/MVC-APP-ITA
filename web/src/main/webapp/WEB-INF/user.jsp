<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>User information</title>
</head>
<body>
<h1>User information</h1>

<h3>Name: ${user.name}</h3>
<h3>Age: ${user.age}</h3>
<h3>Email: ${user.email}</h3>
<h3>Login: ${user.login}</h3>
<h3>Password: ${user.password}</h3>

<h1>Edit user</h1>
<form action="/userServlet" method="POST">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${user.name}"><br>
    <label for="age">Age:</label><br>
    <input type="text" id="age" name="age" value="${user.age}"><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" value="${user.email}"><br>
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login" value="${user.login}"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" value="${user.password}"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
