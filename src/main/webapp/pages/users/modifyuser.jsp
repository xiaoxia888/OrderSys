<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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

<title>${ORDER_SYS_NAME}-修改个人账户信息</title>

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

<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	function begin() {

		var input = document.getElementById("inputfile");
		if (input.value != "") {
			var preview = document.getElementById("face");
			preview.src = "img/loading.gif";
			imgform.submit();
			request();
		}

	}

	function request() {
		txtAjaxRequest(
				"state.order?messageTitle=upstate&time=" + Math.random(),
				"get", true, null, showPreview, null, null);
	}
	function showPreview(responseTxt, obj) {

		var preview = document.getElementById("face");
		preview.src = "img.order?path=D:/store/img/faces/" + responseTxt;
		var path = document.getElementById("faceimgname");
		path.value = responseTxt;
	}
	var formstate = 0;
	function testState() {

		var btu = document.getElementById("addbtu");
		if ((formstate & 3) == 3) {
			btu.disabled = false;
		} else {
			btu.disabled = "disabled";
		}
	}

	function checkpass() {
		var pass = document.getElementById("userPass");
		var pass1 = document.getElementById("userPass1");
		var error = document.getElementById("passerror");
		if (pass.value == pass1.value) {
			formstate = formstate | 2;
			error.innerHTML = "";
		} else {
			if (formstate & 2 != 0) {
				formstate = formstate ^ 2;
			}
			error.innerHTML = "两次密码不匹配！";
		}

		testState();
	}

	function checkoldpass() {

		var id = document.getElementById("userId");
		var oldPass = document.getElementById("userOldPass");
		txtAjaxRequest("checkpass.order?userId=" + id.value + "&userPass="
				+ oldPass.value + "&time=" + Math.random(), "get", true, null,
				userCallback, null, null);
	}
	function userCallback(responseTxt, obj) {
		var error = document.getElementById("usererror");
		if (responseTxt == "OK") {
			formstate = formstate | 1;
			error.innerHTML = "";
		} else {

			if (formstate & 1 != 0) {
				formstate = formstate ^ 1;
			}
			error.innerHTML = "您输入的原密码错误，请重新输入！";

		}
		testState();
	}
</script>
</head>

<body style="font-family:微软雅黑;background-image: url('img/body-bg.png')">

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
				<i class="icon-cogs icon-large"></i>&nbsp;修改您的账户信息
			</h1>
			<p>
				亲爱的<span style="color: red;font-weight: bold;">【${USER_INFO.userAccount}】</span>,${ORDER_SYS_NAME}提示：您可以在本界面修改您的账户密码及头像信息！
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





	<div class="container">
		<div class="row" style="padding-top: 0px">
			<div style="width: 50%;display: inline-block;">
				<div>

					<h2>您可以在此修改您的账户信息：</h2>
					<p>请在下面的界面中修改您的密码（需要提供现密码用于认证），您的账号名及身份角色信息由餐厅管理员指定，不能修改</p>


					<div class="panel panel-danger">
						<div class="panel-heading">
							<i class="icon-warning-sign"></i>&nbsp;注意事项
						</div>
						<div class="panel-body">两次输入的新密码必须一致，否则系统拒绝修改用户，如果不在左边的界面中修改自定义的头像，将继续使用用户原有的头像图片。</div>
					</div>

					<p>
					<form class="form-horizontal" role="form" style="margin-top: 20px"
						method="post" action="modifyuser.order">
						<input type="hidden" name="userId" id="userId"
							value="${USER_INFO.userId}" />
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">用户名:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="firstname"
									name="userAccount" placeholder="请输入新员工的用户名" disabled="disabled"
									value="${USER_INFO.userAccount}">
							</div>

							<div style="color: red;float: right;">您的用户名由系统管理员指定，不能修改</div>
						</div>

						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">旧密码:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="userOldPass"
									name="userOldPass" placeholder="请输入新员工的密码"
									onblur="checkoldpass()">
							</div>
							<div style="color: red;float: right;" id="usererror"></div>
						</div>


						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">新密码:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="userPass"
									name="userPass">
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">确&nbsp;&nbsp;&nbsp;&nbsp;认:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="userPass1"
									name="userPass1"  onblur="checkpass()">
							</div>

							<div style="color: red;float: right;" id="passerror"></div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;色:</label>
							<div class="col-sm-10">
								<select class="form-control" name="roleId" disabled="disabled">
									<option value="1"
										<c:if test="${USER_INFO.roleId==1}"> selected="selected"</c:if>>餐厅管理员</option>
									<option value="2"
										<c:if test="${USER_INFO.roleId==2}"> selected="selected"</c:if>>厨师</option>
									<option value="3"
										<c:if test="${USER_INFO.roleId==3}"> selected="selected"</c:if>>点餐员</option>

								</select>
							</div>
							<div style="color: red;float: right;" id="usererror">您的角色信息由系统管理员指定，不能修改</div>
						</div>

						<input type="hidden" value="${USER_INFO.faceimg}" id="faceimgname"
							name="faceimg" />

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" class="btn btn-danger" disabled="disabled"
									id="addbtu" value="确认修改" />
							</div>
						</div>
					</form>
				</div>

			</div>
			<div
				style="width: 5%;display: inline-block;padding-top: 0px;margin-top: 0px;vertical-align: top;padding-left: 20px">
				<div
					style="background-color:#CCC; width:1px;height: 580px;margin-top: 0px"></div>
			</div>


			<div style="width: 40%;display: inline-block;vertical-align: top;">
				<h2>您可以为自己设置新的头像信息：</h2>
				<p style="margin-bottom: 10px">您选择的新头像图片上传成功后将在下面直接预览。</p>
				<div style="text-align: center;">
					<img src="img/faces/${USER_INFO.faceimg}" id="face" width="200px"
						height="200px" class="img-circle"
						style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);" />
					<p style="margin-top: 15px">当前的头像预览</p>
					<p style="margin-top: 15px">
						更新头像图片，请选择头像文件后，点击<span style="color: red;font-weight: bold;">更新头像图片</span>按钮，上传头像后需要点击左侧<span
							style="color: red;font-weight: bold;">确定修改</span>按钮才能生效
					</p>
					<div>

						<form class="form-inline" role="form"
							enctype="multipart/form-data" action="upimg.order" name="imgform"
							target="submitform" method="post">

							<div class="form-group">
								<label class="sr-only" for="inputfile">文件输入</label> <input
									type="file" id="inputfile" name="uploadFile">
							</div>

							<input type="button" class="btn btn-danger" value="更新头像图片"
								onclick="begin()" />
						</form>
					</div>


				</div>
			</div>


			<iframe src="" width="0" height="0" style="display: none"
				name="submitform"></iframe>


			
			<div style="height:1px;width: 100%;background: #CCC;margin-bottom: 10px"></div>
			<footer>
			<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
			</footer>
</body>
</html>
