<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${ORDER_SYS_NAME}-安全告警提示</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body style="font-family:微软雅黑">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<img src="img/logo.png" class="navbar-brand" /> <span
				class="navbar-brand">${ORDER_SYS_NAME}</span>
		</div>







	</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron"
		style="background-image: url('img/loginbg.jpg');text-shadow: 0 2px 2px #000;color: white;margin-bottom:10px">
		<div class="container">
			<h1 style="color:red">
				<i class="icon-warning-sign icon-large"></i>&nbsp;您有需要注意的消息！
			</h1>
			<p>${ORDER_SYS_NAME}安全警告：这是一条很重要的消息，它关系到您的账户是否安全。出现提示的原因是执行了某些存在安全隐患的操作，具体原因请查看页面中的具体提示，并根据页面中的要求对您的账户信息进行操作！</p>
			<p>
				<a class="btn btn-primary btn-lg" href="toknowledge.order" role="button">查看更多的安全提示
					&raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<h2>原因说明</h2>
				<p>有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！</p>

			</div>
			<div class="col-md-4">
				<h2>防御措施</h2>
				<p>本系统通过数据来源监视，能够在一定程度上发现此种攻击，并在发现钓鱼网站共计后锁定用户账户，自动跳转到本网页，提示用户修改账户信息！</p>

			</div>
			<div class="col-md-4">
				<h2 style="color:red;font-weight: bold;">修改密码</h2>
				<p>
					<span style="color:red;font-weight: bold"><i
						class="icon-warning-sign icon-large"></i>&nbsp;您在钓鱼网站中输入了用户帐号和密码信息</span>，现在本系统已经锁定您的帐号,<span style="color:red;font-weight: bold">请点击下面的按钮立即修改密码！</span>
				</p>
				<p>
					<a class="btn btn-danger" href="modifymyinfo.order" role="button">修改密码 &raquo;</a>
				</p>
			</div>
		</div>

		<hr>

		<footer>
		<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
		</footer>
	</div>
</body>
</html>
