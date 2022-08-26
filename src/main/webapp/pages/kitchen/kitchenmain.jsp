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
	function createXmlDom(str) {
		if (document.all) {
			var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
			xmlDom.loadXML(str);
			return xmlDom;
		} else {
			return new DOMParser().parseFromString(str, "text/xml");
		}
	}

	function begin() {
		txtAjaxRequest("getrtorder.order?messageTitle=rtorder&time="
				+ Math.random(), "get", true, null, showOrder, null, null);
		txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
				+ Math.random(), "get", true, null, bordCallback, null, null);

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
	function showOrder(responseTxt, obj) {

		//alert(createXmlDom(responseTxt).getElementsByTagName("dishes").length);
		/*
		var showContent = document.getElementById("inputRate");
		showContent.value = responseTxt;
		if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {
			txtAjaxRequest("getrate?messageTitle=rtrate&time="+ Math.random(), "get", true, null, showRate, null, null);
		}
		 */

		if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {

			//--------------------------------

			var responseXml = createXmlDom(responseTxt);

			var table = document.getElementById("orderTable");
			table.innerHTML = "";
			var disheses = responseXml.getElementsByTagName("dishes");
			for ( var i = 0; i < disheses.length; i++) {
				var dishes = disheses[i];
				var attrs = dishes.childNodes;
				var tableId;
				var dishesName;
				var num;
				for ( var j = 0; j < attrs.length; j++) {
					var attr = attrs[j];
					if (attr.nodeName == "tableId") {
						tableId = attr.childNodes[0].nodeValue;
					}
					if (attr.nodeName == "dishesName") {
						dishesName = attr.childNodes[0].nodeValue;
					}
					if (attr.nodeName == "num") {
						num = attr.childNodes[0].nodeValue;
					}
				}

				var newLine = "<tr><td>" + tableId + "</td><td>" + dishesName
						+ "</td><td>" + num + "</td>";

				newLine += "<td><a href='javascript:void(0)' onclick='deal(this,\""
						+ tableId
						+ "\",\""
						+ dishesName
						+ "\")' class='btn btn-success'  style='width:350px'>开始烹饪</a>";

				newLine += "</td></tr>";

				table.innerHTML += newLine;

			}

			//--------------------------------

		}

		txtAjaxRequest("getrtorder.order?messageTitle=rtorder&time="
				+ Math.random(), "get", true, null, showOrder, null, null);
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
		var disheses = responseXml.getElementsByTagName("dishes");
		for ( var i = 0; i < disheses.length; i++) {
			var dishes = disheses[i];
			var attrs = dishes.childNodes;
			var dishesId;
			var dishesName;
			var dishesDiscript;
			var dishesImg;
			var dishesTxt;
			var recommend;
			var dishesPrice;
			for ( var j = 0; j < attrs.length; j++) {
				var attr = attrs[j];
				if (attr.nodeName == "dishesId") {
					dishesId = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesName") {
					dishesName = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesDiscript") {
					dishesDiscript = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesImg") {
					dishesImg = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesTxt") {
					dishesTxt = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "recommend") {
					recommend = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesPrice") {
					dishesPrice = attr.childNodes[0].nodeValue;
				}
			}

			var newLine = "<tr><td>" + dishesId + "</td><td>" + dishesName
					+ "</td><td>" + dishesDiscript + "</td>" + "</td><td>"
					+ dishesPrice + "</td>";
			var recommendString = recommend == "0" ? "非特色菜品" : "特色菜品";
			newLine += "<td>" + recommendString + "</td>";

			newLine += "<td>";
			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-cogs icon-large' title='编辑菜品信息' onclick='window.location=\"tomodifydishes.order?dishesId="
					+ dishesId + "\"'></i>&nbsp;&nbsp;";

			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-sitemap icon-large' title='查看菜品详情' onclick=detail(\""
					+ dishesName
					+ "\",\""
					+ dishesDiscript
					+ "\",\""
					+ dishesTxt
					+ "\","
					+ dishesPrice
					+ ","
					+ recommend
					+ ",\""
					+ dishesImg + "\")></i>&nbsp;&nbsp;";

			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-remove-sign icon-large' title='删除该菜品' onclick='deleteDishes("
					+ dishesId + ",\"" + dishesName + "\",this)'></i>";

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
		faceimg.src = "img/faces/" + img;
		userAccount.innerHTML = account;
		userRole.innerHTML = role;
		$("#myModal").modal("show");
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
					aria-expanded="false"><img src="img.order?path=D:/store/img/faces/${USER_INFO.faceimg }"
						width="24" height="24" class="img-circle"
						style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount}】,身份为后厨人员
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img.order?path=D:/store/img/faces/${USER_INFO.faceimg }" width="128" height="128"
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
						class="icon-coffee icon-large"></i>&nbsp;后厨</li>
					<li class="active"><a><i
							class="icon-food icon-large"></i>&nbsp; 备菜界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>

			</div>





			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>厨房</li>
					<li class="active">接受点餐信息界面</li>
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
										<th>菜品</th>
										<th>数量</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="orderTable">

								</tbody>
							</table>



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
