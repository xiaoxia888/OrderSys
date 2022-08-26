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
<link href="css/justified-nav.css" rel="stylesheet">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>

</head>

<body style="font-family:微软雅黑">

	<div class="container">

		<!-- The justified navigation menu is meant for single line per list item.
           Multiple lines will require custom code not provided by Bootstrap. -->
		<div class="masthead">
			<h3 class="text-muted">${ORDER_SYS_NAME}-安全知识</h3>
			<nav>
			<ul class="nav nav-justified">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">Projects</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Downloads</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			</nav>
		</div>

		<!-- Jumbotron -->
		<div class="jumbotron">
			<h1>Marketing stuff!</h1>
			<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas
				eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris
				condimentum nibh, ut fermentum massa justo sit amet.</p>
			<p>
				<a class="btn btn-lg btn-success" href="#" role="button">Get
					started today</a>
			</p>
		</div>

		<!-- Example row of columns -->
		<div class="row">
			<div class="col-lg-4">
				<h2>Safari bug warning!</h2>
				<p class="text-danger">As of v8.0, Safari exhibits a bug in
					which resizing your browser horizontally causes rendering errors in
					the justified nav that are cleared upon refreshing.</p>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn btn-primary" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn btn-primary" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2>Heading</h2>
				<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
					egestas eget quam. Vestibulum id ligula porta felis euismod semper.
					Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
					nibh, ut fermentum massa.</p>
				<p>
					<a class="btn btn-primary" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
		</div>

		<!-- Site footer -->
		<footer class="footer">
		<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
		</footer>

	</div>
	<!-- /container -->



	

	<div class="container" style="padding: 0px 150px">
		<div class="row">

			<div class="col-md-4" style="padding: 1px">
				<div style="background-color: #CCC;padding: 15px">
					<h2>原因说明</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！
					</p>
				</div>


			</div>


			<div class="col-md-4" style="padding: 1px">

				<div style="background-color: #0AF;padding: 15px">

					<h2>原因说明</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！
					</p>
				</div>
			</div>

			<div class="col-md-4" style="padding: 1px">
				<div style="background-color: #FA0;padding: 15px">
					<h2>原因说明</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！
					</p>
				</div>
			</div>
		</div>
		
		
		<div class="row">

			<div class="col-md-4" style="padding: 1px">
				<div style="background-color: #CCC;padding: 15px">
					<h2>原因说明</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！
					</p>
				</div>


			</div>


			<div class="col-md-4" style="padding: 1px">

				<div style="background-color: #FAF;padding: 15px">

					<h2>原因说明</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！
					</p>
				</div>
			</div>

			<div class="col-md-4" style="padding: 1px">
				<div style="background-color: #CCC;padding: 15px">
					<h2>原因说明</h2>
					<p>
						有一种攻击行为叫做<span style="color:red;font-weight: bold">"钓鱼网站"</span>，不法分子仿造真实的登录网站制作了山寨版本的界面，诱使用户在自己创建的界面中输入用户名及密码信息并利用JS代码获取后再提交到真实的系统中，于是您的账户信息在不知不觉中就泄露了！
					</p>
				</div>
			</div>
		</div>
	</div>


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
