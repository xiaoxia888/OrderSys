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

<title>${ORDER_SYS_NAME}-安全知识</title>

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
				<i class="icon-google-plus-sign icon-large"></i>&nbsp;WEB系统安全常识
			</h1>
			<p>${ORDER_SYS_NAME}推广：本页面包含常见的安全常识内容，包含攻击与防御方法</p>
			<p>
				<a class="btn btn-primary btn-lg" href="/OrderSys" role="button">登录系统
					&raquo;</a>
			</p>
		</div>
	</div>



<!--
	<div class="masthead">

		<nav class="navbar navbar-default" role="navigation">
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#">Projects</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Downloads</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Contact</a></li>
		</ul>
		</nav>
	</div>

  -->




	<div class="container" style="padding: 0px 150px">
		<div class="row" style="text-align: center;">
			<div class="col-md-2" style="padding: 1px;width: 20%">
				<h1 style="color:gray;font-size: 100">
					<i class="icon-comments icon-large"></i>
				</h1>
			</div>
			<div class="col-md-2" style="padding: 1px;width: 20%">
				<h1 style="color:gray;font-size: 32">
					<i class="icon-arrow-right icon-large" style="line-height: 167px;margin: auto"></i>
				</h1>
			</div>
			<div class="col-md-2" style="padding: 1px;width: 20%">
				<h1 style="color:gray;font-size: 100">
					<i class="icon-question-sign icon-large"  style="line-height: 167px;margin: auto"></i>
				</h1>
			</div>
			<div class="col-md-2" style="padding: 1px;width: 20%">
				<h1 style="color:gray;font-size: 32">
					<i class="icon-arrow-right icon-large" style="line-height: 167px;margin: auto"></i>
				</h1>
			</div>
			<div class="col-md-2" style="padding: 1px;width: 20%;height:167px;line-height: 167px;margin: auto">
				<h1 style="color:gray;font-size: 100">
					<i class="icon-globe icon-large"></i>
				</h1>
			</div>

		</div>
	</div>






<div class="container-fluid" style="padding: 0px 250px">
	<div class="row-fluid">
		<div class="span6" style="width:30%;float:left">
		<div style="background-color: #66A;padding: 15px;margin: 1px;height:450px;box-shadow:0 0 20px rgba(0, 100, 200, 1);color:white;">
					<h2 style="color: white;font-weight: bold;font-family: 微软雅黑">重要提示</h2>
					<p>
						对于互联网中的应用系统而言，<span style="color:red;font-weight: bold">安全控制尤为重要</span>，需要随时保证用户的账户信息不被泄露，尽可能防止恶意攻击
					</p>
					
					<p style="text-align: center;font-size: 64;margin-top: 40px "><i class="icon-warning-sign icon-large"></i></p>
				</div>
		</div>
		<div class="span6" style="width:70%;float:left">
			<div class="row-fluid">
				<div class="span6" style="width:50%;float:left">
				<div style="background-color: #CCC;padding: 15px;margin: 1px;height:224px;box-shadow:0 0 20px rgba(0, 100, 200, 1);">
					<h2>SQL注入攻击</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"SQL注入"</span>，登陆验证系统不严密，导致可以在界面中输入SQL片段而绕过用户名密码验证的漏洞。
						
					</p>
					<p style="text-align: center;font-size: 32;margin-top: 10px "><i class=" icon-tags icon-large"></i></p>
				</div>
				
				</div>
				<div class="span6" style="width:50%;float:left">
				
				<div style="background-color: #CF0;padding: 15px;margin: 1px;height:224px;box-shadow:0 0 20px rgba(0, 100, 200, 1);">
					<h2>钓鱼网站</h2>
					<p>
						恶意制作<span style="color:red;font-weight: bold">"钓鱼网站"</span>，可以诱使用户主动输入账户信息，在获取信息后将数据提交给真实系统。
					</p>
					<p style="text-align: center;font-size: 32;margin-top: 10px "><i class=" icon-random icon-large"></i></p>
				</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6" style="width:50%;float:left">
				
				<div style="background-color: #FA0;padding: 15px;margin: 1px;height:224px;box-shadow:0 0 20px rgba(0, 100, 200, 1);">
					<h2>QoS攻击</h2>
					<p>
						最为<span style="color:red;font-weight: bold">原始</span>，但却最有效的攻击手法之一，不停的向服务器发送大量无意义请求，导致过载宕机。
					</p>
					<p style="text-align: center;font-size: 32;margin-top: 10px "><i class="icon-fire icon-large"></i></p>
				</div>
				</div>
				<div class="span6" style="width:50%;float:left">
				
				<div style="background-color: #CCC;padding: 15px;margin: 1px;height:224px;box-shadow:0 0 20px rgba(0, 100, 200, 1);">
					<h2>其他攻击</h2>
					<p>
						<span style="color:red;font-weight: bold">未完待续</span>
					</p>
					<p style="text-align: center;font-size: 32;margin-top: 10px "><i class="icon-circle-arrow-right icon-large"></i></p>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>




	



	<hr>

	<footer>
	<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
	</footer>
	
</body>
</html>
