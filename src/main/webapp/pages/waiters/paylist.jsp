<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
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
	function createXmlDom(str) {
		if (document.all) {
			var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
			xmlDom.loadXML(str);
			return xmlDom;
		} else {
			return new DOMParser().parseFromString(str, "text/xml");
		}
	}

	function getOrderList(page) {

		xmlAjaxRequest("getpaylist.order?page=" + page + "&time="
				+ Math.random(), "get", true, null, showList, null, null);
	}

	function begin() {

		txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
				+ Math.random(), "get", true, null, bordCallback, null, null);
		getOrderList(1);

	}

	function bordCallback(responseTxt, obj) {
		if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {
			var msg = document.getElementById("msg");

			msg.innerHTML = responseTxt;

		}
		txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
				+ Math.random(), "get", true, null, bordCallback, null, null);

	}

	function deal(obj, tableId, dishesName) {

		//alert("deal"+obj.className);
		//alert(obj+"        "+obj.className);
		if (obj.className == "btn btn-success") {
			obj.className = "btn btn-danger";
			obj.innerHTML = "正在烹制";
		} else {

			//alert("test");
			obj.parentNode.parentNode.parentNode
					.removeChild(obj.parentNode.parentNode);
			alert(tableId + "桌的菜品：" + dishesName + "完成，已经通知服务员传菜！");
			txtAjaxRequest("dishesdone.order?tableId=" + tableId
					+ "&dishesName=" + encodeURIComponent(dishesName)
					+ "&time=" + Math.random(), "get", true, null, done, null,
					null);
		}
	}

	function done() {

	}

	function showList(responseXml, obj) {

		var maxPage = responseXml.getElementsByTagName("maxPage");
		maxPage = maxPage[0].childNodes;
		//alert(maxPage[0].nodeValue);
		var link = document.getElementById("last");
		link.href = "javascript:getDishesList(" + maxPage[0].nodeValue + ")";

		var page = responseXml.getElementsByTagName("page");
		page = page[0].childNodes;
		link = document.getElementById("next");
		link.href = "javascript:getDishesList("
				+ (parseInt(page[0].nodeValue) + 1) + ")";
		link = document.getElementById("pre");
		link.href = "javascript:getDishesList("
				+ (parseInt(page[0].nodeValue) - 1) + ")";
		var table = document.getElementById("orderTable");
		table.innerHTML = "";

		var orders = responseXml.getElementsByTagName("order");

		for ( var i = 0; i < orders.length; i++) {
			var order = orders[i];
			var attrs = order.childNodes;
			var orderId;
			var tableId;
			var userAccount;
			var orderBeginDate;

			for ( var j = 0; j < attrs.length; j++) {
				var attr = attrs[j];
				if (attr.nodeName == "orderId") {
					orderId = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "tableId") {
					tableId = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "userAccount") {
					userAccount = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "orderBeginDate") {
					orderBeginDate = attr.childNodes[0].nodeValue;
				}

			}

			var newLine = "<tr><td>" + tableId + "</td><td>" + userAccount
					+ "</td><td>" + orderBeginDate + "</td>";
			newLine += "<td><a class='btn btn-danger' style='width:350px' onclick='pay("
					+ orderId + ",this)'>买单</a>";
			newLine += "</td></tr>";

			table.innerHTML += newLine;

		}
	}

	function pay(orderId,obj) {

		txtAjaxRequest("requestpay.order?orderId=" + orderId + "&time="
				+ +Math.random(), "get", true, null, payCallback, obj, null);

	}
	function payCallback(responseTxt, obj) {
		alert("已经向餐厅管理员发送买单信息！");
		obj.parentNode.parentNode.parentNode
					.removeChild(obj.parentNode.parentNode);
	}

	function cancel(obj) {

		if (confirm("此订单将不结账而直接作废？")) {
			obj.parentNode.parentNode.parentNode
					.removeChild(obj.parentNode.parentNode);
		}
	}
</script>
</head>

<body style="font-family: 微软雅黑" onload="begin()">
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
						src="img/faces/${USER_INFO.faceimg }" width="24" height="24"
						class="img-circle" style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount}】,身份为服务员
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img/faces/${USER_INFO.faceimg }" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="modifymyinfo.order">修改我的密码</a></li>
						<li role="separator" class="divider"></li>



					</ul></li>
				<li><a href="logout.order">退出登录</a></li>
			</ul>




		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-user icon-large"></i>&nbsp;服务员点餐功能</li>
					<li><a href="towaitermain.order"><i
							class="icon-glass icon-large"></i>&nbsp; 服务点餐界面 <span
							class="sr-only">(current)</span></a></li>
					<li class="active"><a href="topaylist.order"><i
							class="icon-credit-card icon-large"></i>&nbsp; 餐桌结账 <span
							class="sr-only">(current)</span></a></li>


				</ul>

			</div>





			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>服务员</li>
					<li class="active">买单界面</li>
				</ol>



				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">最新公告消息</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 10px">

						<h4>
							<i class=" icon-envelope icon-large" style="color:orange;"></i>&nbsp;<span
								id="msg"></span>
						</h4>

					</div>
				</div>


				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">顾客点餐列表</h3>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>桌号</th>
										<th>服务员</th>
										<th>开餐时间</th>
										<th>操作</th>
									</tr>




								</thead>
								<tbody id="orderTable">

									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td><a class='btn btn-danger' style='width:350px'>买单</a></td>
									</tr>


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
		</div>
	</div>

	<br>






	<!-- Modal -->















</body>
</html>
