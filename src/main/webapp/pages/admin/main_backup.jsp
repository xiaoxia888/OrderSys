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
<script type="text/javascript">
	function cancel(obj) {

		if (confirm("此订单将不结账而直接作废？")) {
			obj.parentNode.parentNode.parentNode
					.removeChild(obj.parentNode.parentNode);
		}
	}
	function newOrder() {
	var table=document.getElementById("orderTable");
	var newLine="<tr><td>1,001</td><td>Lorem</td><td>ipsum</td><td>dolor</td>";
	newLine+="<td>";
	newLine+="<i style='cursor: pointer; font-size: 14;'";
	newLine+="onmouseover='this.style.color=\"orange\"'";
	newLine+="onmouseout='this.style.color=\"black\"'";
	newLine+="class='icon-credit-card icon-large' title='确认结账'></i>&nbsp;&nbsp;";
	
	newLine+="<i style='cursor: pointer; font-size: 14;'";
	newLine+="onmouseover='this.style.color=\"orange\"'";
	newLine+="onmouseout='this.style.color=\"black\"'";
	newLine+="class='icon-sitemap icon-large' title='查看订单详情'></i>&nbsp;&nbsp;";
	
	newLine+="<i style='cursor: pointer; font-size: 14;'";
	newLine+="onmouseover='this.style.color=\"orange\"'";
	newLine+="onmouseout='this.style.color=\"black\"'";
	newLine+="class='icon-remove-sign icon-large' title='订单作废' onclick='cancel(this)'></i>";
	
	newLine+="</td></tr>";
	
	table.innerHTML+=newLine;
	
	
	}
</script>
</head>

<body style="font-family: 微软雅黑">
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
					aria-expanded="false"><img src="img/faces/default.jpg"
						width="24" height="24" class="img-circle"
						style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【】,身份为管理员 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img/faces/default.jpg" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="#">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">查看当前在线的厨师 <span class="badge"
								style="background-color: orange;">4</span></a></li>
						<li><a href="#">查看当前在线的点餐员 <span class="badge"
								style="background-color: orange;">4</span></a></li>


					</ul></li>
				<li><a href="javascript:newOrder()">退出登录</a></li>
			</ul>



			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="搜索点餐员">
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
					<li class="active"><a href="#"><i
							class="icon-money icon-large"></i>&nbsp; 顾客结账界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-cog icon-large"></i>&nbsp;餐厅管理</li>
					<li><a href="#"><i class="icon-group icon-large"></i>&nbsp;员工管理</a></li>
					<li><a href="#"><i class="icon-coffee icon-large"></i>&nbsp;菜品管理</a></li>
					<li><a href=""><i class="icon-bar-chart icon-large"></i>&nbsp;查看经营数据
					</a></li>

				</ul>
			</div>





			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>管理员</li>
					<li class="active">管理管理界面</li>
				</ol>





				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">今日特色推荐菜品</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 0px">
						<div class="row placeholders">
							<div class="col-xs-6 col-sm-3 placeholder">
								<a href="#"> <img class="img-thumbnail"
									style="border-radius:20px" alt="Generic placeholder thumbnail"
									src="img/dishes/1.jpg"></a>
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
							</div>
							<div class="col-xs-6 col-sm-3 placeholder">
								<img data-src="holder.js/200x200/auto/vine"
									class="img-thumbnail" alt="Generic placeholder thumbnail"
									style="border-radius:20px" src="img/dishes/1.jpg">
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
							</div>
							<div class="col-xs-6 col-sm-3 placeholder">
								<img data-src="holder.js/200x200/auto/sky" class="img-thumbnail"
									alt="Generic placeholder thumbnail" style="border-radius:20px"
									src="img/dishes/1.jpg">
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
							</div>
							<div class="col-xs-6 col-sm-3 placeholder">
								<img data-src="holder.js/200x200/auto/vine"
									class="img-thumbnail" alt="Generic placeholder thumbnail"
									style="border-radius:20px" src="img/dishes/1.jpg">
								<h4>鱼香肉丝</h4>
								<span class="text-muted">色香味俱全，顾客都喜欢</span>
							</div>


							<nav>
							<ul class="pager" style="margin-right: 20px">

								<li class="next"><a href="#">更多特色菜品 <span
										aria-hidden="true">&rarr;</span></a></li>
							</ul>
							</nav>
						</div>
					</div>
				</div>






				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">待结账餐桌信息</h3>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped" >
								<thead>
									<tr>
										<th>桌号</th>
										<th>开餐时间</th>
										<th>结账时间</th>
										<th>总价</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="orderTable">
									<tr>
										<td>1,001</td>
										<td>Lorem</td>
										<td>ipsum</td>
										<td>dolor</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情" onclick="$('#myModal').modal('show')"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"
											onclick="cancel(this)"></i></td>
									</tr>
									<tr>
										<td>1,002</td>
										<td>amet</td>
										<td>consectetur</td>
										<td>adipiscing</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,003</td>
										<td>Integer</td>
										<td>nec</td>
										<td>odio</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,003</td>
										<td>libero</td>
										<td>Sed</td>
										<td>cursus</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,004</td>
										<td>dapibus</td>
										<td>diam</td>
										<td>Sed</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,005</td>
										<td>Nulla</td>
										<td>quis</td>
										<td>sem</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,006</td>
										<td>nibh</td>
										<td>elementum</td>
										<td>imperdiet</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,007</td>
										<td>sagittis</td>
										<td>ipsum</td>
										<td>Praesent</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,008</td>
										<td>Fusce</td>
										<td>nec</td>
										<td>tellus</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,009</td>
										<td>augue</td>
										<td>semper</td>
										<td>porta</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,010</td>
										<td>massa</td>
										<td>Vestibulum</td>
										<td>lacinia</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>
									<tr>
										<td>1,011</td>
										<td>eget</td>
										<td>nulla</td>
										<td>Class</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"></i>
											&nbsp;&nbsp;<i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"></i></td>
									</tr>

								</tbody>
							</table>
							<!-- 
							<nav> 
							<ul class="pager">
								<li><a href="#">上一页</a></li>
								<li><a href="#">下一页</a></li>
							</ul>
							</nav>
							 -->
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">订单详情</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        
      </div>
    </div>
  </div>
</div>
	
	
	
	
	
	
	
</body>
</html>
