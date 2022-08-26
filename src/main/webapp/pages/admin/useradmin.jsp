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

<title>${ORDER_SYS_NAME}-餐厅管理员</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/dashboard.css">


<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	function deleteUser(id, name, obj) {

		if (confirm("您真的要删除员工【" + name + "】吗？")) {
			xmlAjaxRequest("deleteuser.order?userId=" + id + "&time="
					+ Math.random(), "get", true, null, deleteCallback, obj,
					null);

		}

	}
	function deleteCallback(responseTxt, obj) {

		alert("员工删除成功！");
		obj.parentNode.parentNode.parentNode
				.removeChild(obj.parentNode.parentNode);
	}

	function getUserList(page) {

		xmlAjaxRequest("getuserbypage.order?page=" + page + "&time="
				+ Math.random(), "get", true, null, showList, null, null);
	}

	function showList(responseXml, obj) {

		var maxPage = responseXml.getElementsByTagName("maxPage");
		maxPage = maxPage[0].childNodes;
		//alert(maxPage[0].nodeValue);
		var link = document.getElementById("last");
		link.href = "javascript:getUserList(" + maxPage[0].nodeValue + ")";

		var page = responseXml.getElementsByTagName("page");
		page = page[0].childNodes;
		link = document.getElementById("next");
		link.href = "javascript:getUserList("
				+ (parseInt(page[0].nodeValue) + 1) + ")";
		link = document.getElementById("pre");
		link.href = "javascript:getUserList("
				+ (parseInt(page[0].nodeValue) - 1) + ")";
		var table = document.getElementById("orderTable");
		table.innerHTML = "";
		var users = responseXml.getElementsByTagName("user");
		for ( var i = 0; i < users.length; i++) {
			var user = users[i];
			var attrs = user.childNodes;
			var userId;
			var userAccount;
			var roleName;
			var faceimg;
			for ( var j = 0; j < attrs.length; j++) {
				var attr = attrs[j];
				if (attr.nodeName == "userId") {
					userId = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "userAccount") {
					userAccount = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "roleName") {
					roleName = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "faceimg") {
					faceimg = attr.childNodes[0].nodeValue;
				}
			}

			var newLine = "<tr><td>" + userId + "</td><td>" + userAccount
					+ "</td><td>" + roleName + "</td>";
			newLine += "<td>";
			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-cogs icon-large' title='修改员工信息' onclick='window.location=\"tomodifyuser.order?userId="
					+ userId + "\"'></i>&nbsp;&nbsp;";

			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-sitemap icon-large' title='查看员工详情' onclick='detail(\""+userAccount+"\",\""+roleName+"\",\""+faceimg+"\")'></i>&nbsp;&nbsp;";

			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-remove-sign icon-large' title='删除该员工' onclick='deleteUser("
					+ userId + ",\"" + userAccount + "\",this)'></i>";

			newLine += "</td></tr>";

			table.innerHTML += newLine;

		}

	}

	function cancel(obj) {

		if (confirm("此订单将不结账而直接作废？")) {
			obj.parentNode.parentNode.parentNode
					.removeChild(obj.parentNode.parentNode);
		}
	}
	function detail(account, role, img) {

		var userAccount = document.getElementById("userAccount");
		var userRole = document.getElementById("role");
		var faceimg = document.getElementById("faceimg");
		faceimg.src = "img.order?path=D:/store/img/faces/" + img;
		userAccount.innerHTML = account;
		userRole.innerHTML = role;
		$("#myModal").modal("show");
	}
</script>
</head>

<body style="font-family: 微软雅黑" onload="getUserList(1)">
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
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
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><span class="navbar-brand">餐厅管理界面</span></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><img
						src="img.order?path=D:/store/img/faces/${USER_INFO.faceimg }" width="24" height="24"
						class="img-circle" style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount
						}】,身份为管理员 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img.order?path=D:/store/img/faces/${USER_INFO.faceimg }" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="modifymyinfo.order">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="toonlinewaiters.order">查看当前在线的厨师 </a></li>
						<li><a href="toonlinekitchen.order">查看当前在线的点餐员</a></li>


					</ul></li>
				<li><a href="logout.order">退出登录</a></li>
			</ul>



			<form class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" class="form-control" placeholder="公告"
					style="width:300px" name="bord"><input
					class="btn btn-default" type="submit" value="发送" />
				<iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
			</form>
		</div>
	</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-credit-card icon-large"></i>&nbsp;运营功能</li>
					<li ><a href="toadminmain.order"><i
							class="icon-money icon-large"></i>&nbsp; 顾客结账界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-cog icon-large"></i>&nbsp;餐厅管理</li>
					<li  class="active"><a href="touseradmin.order"><i
							class="icon-group icon-large"></i>&nbsp;员工管理</a></li>
					<li><a href="todishesadmin.order"><i
							class="icon-coffee icon-large"></i>&nbsp;菜品管理</a></li>
					<li><a href="tooperatedata.order"><i
							class="icon-bar-chart icon-large"></i>&nbsp;查看经营数据 </a></li>

				</ul>
			</div>







			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>管理员</li>
					<li class="active">员工管理界面</li>
				</ol>






				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">员工列表</h3>
					</div>
					<div style="text-align: right;">
						如果您需要在系统中添加一名员工，请点击右边的按钮：<a href="toadd.order"
							class="btn btn-success"
							style="width:120px;margin-top: 10px;margin-right: 10px">添加员工</a>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>员工编号</th>
										<th>员工帐号</th>
										<th>员工角色</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="orderTable">

								</tbody>
							</table>

							<nav>
							<ul class="pager">
								<li><a href="javascript:getUserList(1)" id="firstpage">&larr;首页</a></li>
								<li><a href="#" id="pre">上一页</a></li>
								<li><a href="#" id="next">下一页</a></li>
								<li><a href="#" id="last">末页&rarr;</a></li>
							</ul>
							</nav>

						</div>

					</div>
				</div>

				<div
					style="height:1px;width: 100%;background: #CCC;margin-bottom: 10px"></div>
				<footer>
				<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
				</footer>

			</div>
			<%----%>
		</div>
	</div>

	<br>






	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详细信息</h4>
				</div>
				<div class="modal-body"
					style="background-image: url('/img/body-bg.png')">










					<div class="panel panel-danger" style="margin-top: 10px">
						<div class="panel-heading">
							<h3 class="panel-title">员工详情</h3>
						</div>
						<div class="panel-body" style="text-align: center;">

							<img src="img/faces/default.jpg" id="faceimg" width="200px"
								height="200px" class="img-circle"
								style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);" />
							<p><h2>员工帐号：
							
								<span id="userAccount"></span>
							</h2>
							</p>
							<p><h3>
								员工角色：<span id="role"></span></h3>
							</p>


						</div>



					</div>











				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>

				</div>
			</div>
		</div>
	</div>














</body>
</html>
