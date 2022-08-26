<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/main.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="${base_path }/static/js/html5shiv.min.js"></script>
    <script src="${base_path }/static/js/respond.min.js"></script>
    <![endif]-->




<script type="text/javascript">
	//取得XMLHttpRequest对象是AJAX的要点  
	//此getXMLRequest()方法是根据不同浏览器来取得XMLHttpRequest对象  
	function getXMLRequest() {
		var request;
		try {
			//for火狐等浏览器  
			request = new XMLHttpRequest();
		} catch (e) {
			try {
				//for IE  
				request = new ActiveXObject("Microsoft.XMLHttp");
			} catch (e) {
				alert("您的浏览器不支持AJAX!!!");
				return null;
			}
		}
		return request;
	}

	//此checkcode()方法是更换验证码图片的要点  
	function checkcode() {
		var request = getXMLRequest();//得到XMLHttpRequest对象  
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				document.getElementById("code").src = "code";//改变验证码图片  
			}
		}
		//将请求发送出去  
		request.open("GET", "code", true);
		request.send(null);
	}
</script>


</head>
<body class="login-body">

	<div class="panel login-panel">
		<form role="form" method="post" action="login">
			<h2 class="text-center">点餐系统登录</h2>
			<c:if test="${not empty ERROR_MSG}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
					${ERROR_MSG}
				</div>
			</c:if>
			<div class="form-group login-text">
				<label>用户名:</label> <input type="text" class="form-control"
					name="loginName" placeholder="请输入您的用户名" required autofocus>
			</div>
			<div class="form-group login-text">
				<label>密码：</label> <input type="password" class="form-control"
					name="password" placeholder="请输入您的密码" required>
			</div>

			<div class="form-group login-text">
				<label>验证码：</label><input class="form-control login-inputcode"
					type="text" name="codetext" />&nbsp;&nbsp;&nbsp;<img id="code"
					src="code" onclick="checkcode()" style="width:100px;height:30px;">
			</div>

			<button class="btn btn-lg btn-primary btn-block login-text"
				type="submit">登录</button>
		</form>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="bootstrap/js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>