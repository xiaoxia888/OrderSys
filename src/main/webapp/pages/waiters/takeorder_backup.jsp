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
	function deleteDishes(id, name, obj) {

		if (confirm("您真的要删除菜品【" + name + "】吗？")) {
			xmlAjaxRequest("deletedishes.order?dishesId=" + id + "&time="
					+ Math.random(), "get", true, null, deleteCallback, obj,
					null);

		}

	}
	function deleteCallback(responseTxt, obj) {

		alert("菜品删除成功！");
		obj.parentNode.parentNode.parentNode
				.removeChild(obj.parentNode.parentNode);
	}

	function getDishesList(page) {

		xmlAjaxRequest("getdishesbypage.order?page=" + page + "&time="
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
			
			
			var newLine="<div class='col-xs-6 col-sm-3 placeholder'>";
			newLine+="<a href='#'> <img class='img-thumbnail'	style='border-radius:20px' alt='Generic placeholder thumbnail' src='img/dishes/1.jpg'></a>";
			newLine+="<h4>鱼香肉丝</h4>";
			newLine+="<span class='text-muted'>色香味俱全，顾客都喜欢</span>";
			newLine+="<div class='class='form-group'>";
			newLine+="<form>";
			newLine+="<div style='width:120px;margin: 0px auto'>";
			newLine+="";
			
			

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
	function detail(name, disc, txt, price, commend, img) {

		var dishesName = document.getElementById("dishesName");
		var dishesDiscript = document.getElementById("dishesDiscript");
		var dishesTxt = document.getElementById("dishesTxt");
		txt = txt.replace(/ordersysspace/g, "&nbsp;");
		txt = txt.replace(/ordersysbreak/g, "<br>");

		var dishesPrice = document.getElementById("dishesPrice");
		var recommend = document.getElementById("recommend");
		var dishesImg = document.getElementById("dishesImg");
		dishesImg.src = "img/dishes/" + img;
		dishesName.innerHTML = name;
		dishesDiscript.innerHTML = disc;
		dishesTxt.innerHTML = txt;
		dishesPrice.innerHTML = price;
		if (commend == 1) {
			recommend.innerHTML = "<i class='icon-heart icon-large'></i>&nbsp;推荐菜品";
		} else {
			recommend.innerHTML = "";
		}
		$("#myModal").modal("show");
	}
</script>

</head>

<body style="font-family: 微软雅黑" onload="getDishesList(1)">
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
				<li><span class="navbar-brand">点单界面：当前桌号【】</span></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><img src="img/faces/default.jpg"
						width="24" height="24" class="img-circle"
						style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【】,身份为服务员 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img/faces/default.jpg" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="#">修改我的密码</a></li>



					</ul></li>
				<li><a href="javascript:newOrder()">退出登录</a></li>
			</ul>



			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="设定桌号"><input
					class="btn btn-default" type="button" value="确定" />
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
						class="icon-user icon-large"></i>&nbsp;服务员点餐功能</li>
					<li class="active"><a href="#"><i
							class="icon-glass icon-large"></i>&nbsp; 服务点餐界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>

			</div>





			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>服务员</li>
					<li class="active">服务员点餐界面</li>
				</ol>




				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">最新公告消息</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 10px">

						<h4>
							<i class=" icon-envelope icon-large" style="color:orange;"></i>&nbsp;6号桌菜品：川香回锅肉已经完成！
						</h4>

					</div>
				</div>



				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">菜品清单</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 0px">
						<div class="row placeholders" id="dishesbord">
							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>





							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>





							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>






							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>




							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>



							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>



							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
								<div class="class="form-group"">
									<form>
										<div style="width:120px;margin: 0px auto">


											<div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="subtract(this)">-</button>
												</span> <input type="text" class="form-control" value="1"
													disabled="disabled" name="num"
													style="text-align: center;padding: 0px;cursor: text;"><input
													type="hidden" name="dishes" value="6" /> <span
													class="input-group-btn">
													<button class="btn btn-default" type="button"
														onclick="add(this)">+</button>
												</span>
											</div>





											<!-- /input-group -->
										</div>
										<p>
											<input type="button" class="btn btn-danger"
												style="width:120px;margin-top: 5px" value="加入点餐车"></input>
										</p>

									</form>
								</div>
							</div>




						</div>
						<nav>
						<ul class="pager">
							<li><a href="javascript:getDishesList(1)">&larr;首页</a></li>
							<li><a href="#" id="pre">上一页</a></li>
							<li><a href="#" id="next">下一页</a></li>
							<li><a href="#" id="last">末页&rarr;</a></li>
						</ul>
						</nav>

						<div style="text-align: center;">
							<a href="" class="btn btn-danger"
								style="width: 40%;margin-bottom: 15px;font-size: 18;font-weight: bold;">确认订单</a>
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


</body>
</html>
