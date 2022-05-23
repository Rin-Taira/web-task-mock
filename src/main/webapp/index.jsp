<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
  <div class="header">
    <h1 class="site_logo">商品管理システム</h1>
  </div>

  <div class="login_form">
    <img src="./images/logo.png" class="login_logo">
    <div class="error">
    	<c:if test="${not empty msg1}">
    		<p>${msg1}</p>
  		</c:if>
  	</div>

    <form action="login" method="get">
      <fieldset>
        <div class="cp_iptxt">
          <input class="base_input" type="text" name="loginId" placeholder="ID">
          <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
          <div class="error">
          	<c:if test="${not empty msg2}">
    			<p>${msg2}</p>
  			</c:if>
          </div>
        </div>

        <div>
          <input class="base_input" type="password" name="pass" placeholder="PASS">
          <div class="error">
          	<c:if test="${not empty msg3}">
    			<p>${msg3}</p>
  			</c:if>
          </div>
        </div>
      </fieldset>
      <button class="logout_btn" type="submit">ログイン</button>
    </form>
  </div>
</body>
</html>
