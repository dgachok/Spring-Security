<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
  <title>Login Page</title>
  <style>
    #login-box {
      width: 300px;
      padding: 20px;
      margin: 100px auto;
      background: #fff;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border: 1px solid #000;
    }
  </style>
</head>
<center>
<h1>Registration Success</h1></center>

<div id="login-box">

  <center><h3>Login with Username and Password</h3>
  <c:url var="addUrl" value="/login"/>
  <h3><p><a href="${addUrl}">Return on Main Page</a></p></h3></center>
</div>

</body>
</html>