<%--
  Created by IntelliJ IDEA.
  User: dingkun
  Date: 4/28/20
  Time: 5:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>PicXe</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .block{
            width: 400px;
            display: block;
            margin:5px 0;
        }
        .center{
            text-align: center;
        }
        label {
            display: inline-block;
            width: 100px;
            text-align: right;
        }
        input,textarea{
            vertical-align: top;
        }

    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="./pCatalog">PicXe</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"></li>
            <li><a href="./pContact.xml">Contact</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <center>
        <h2>Change Password</h2>

        <form action="changePassword" method="post" class="login-item">
            <div class="block">
                <span>${errormessage2}</span><br/>
                <label>Old Pass:</label><input type="password" name="oldpass"/><br/><br/>
                <span>${errormessage1}</span><br/>
                <label>New Pass:</label><input type="password" name="newpass"/><br/><br/>
            </div>
            <div style="padding-left:39px" class="block center">
                <input type="submit" class="btn btn-primary" name="change" value="Change password"/>
                <input type="submit" class="btn btn-primary" name="cancel" value="Back to Account"/>
            </div>
        </form>
    </center>
</div>
</html>