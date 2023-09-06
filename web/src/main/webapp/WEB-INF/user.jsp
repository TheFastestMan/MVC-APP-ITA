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
<a href="/updateUser.html">Edit</a>
</body>
</html>
