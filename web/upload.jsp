<%--
  Created by IntelliJ IDEA.
  User: madman
  Date: 2018/12/8
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<span><a href="hello">hello world</a></span>
<form method="post" action="uploadFile" enctype="multipart/form-data">
    <label>upload image：</label> <input type="file" name="image" id="image"/>
    <label>name:</label> <input name="name" id="name"/>
    <label>age:</label><input name="age" id="age"/>
    <button type="submit">submit</button>
</form>
</body>
</html>
