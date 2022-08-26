//用于获取XMLHttpRequest对象的函数
function getajaxHttp() {
	// 定义保存XMLHttpRequest对象的变量
	var xmlHttp;
	try {
		// 尝试利用标准方式获取XMLHttpRequest对象
		// 适用于Firefox, Opera 8.0+, Safari等浏览器
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		// 尝试利用微软自定义方式获取XMLHttpRequest对象，适用于Internet Explorer特定版本
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				// 尝试利用微软自定义方式二获取XMLHttpRequest对象，适用于Internet Explorer特定版本
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	// 返回获取到的对象
	return xmlHttp;
}
// Ajax请求函数，该请求将服务器返回的结果当成普通文本交由回调函数处理
// 参数url:需要请求的URL地址，由于不同浏览器对缓存的处理方式不同，建议本地址在末尾添加一个随机值参数，保证每次请求都能发送到服务器
// 参数methodtype:发送请求时用的请求方法，常用的有get和post，以字符串方式发送
// 参数contype:连接类型，true表示异步，false表示同步（在动态加载JS代码等功能时需要同步请求）
// 参数parameter:需要在请求体中发送给服务器的附件参数，以"参数名=参数值&参数名=参数值..."的形式体现，在使用post方式请求时使用
// 参数functionName：请求成功后调用的回调函数
// 参数obj:传递给回调函数的附带参数
// 参数errorFunctionName：如果请求没有成功执行的情况自动回调的函数，可以传递null
function txtAjaxRequest(url, methodtype, contype, parameter, functionName, obj,
		errorFunctionName) {
	// 获取XmlHttpRequest对象用以发送请求并获取结果
	var xmlhttp = getajaxHttp();
	// 请求回调
	xmlhttp.onreadystatechange = function() {
		// 在请求已经成功返回的情况下执行
		if (xmlhttp.readyState == 4) {
			// 如果请求返回的状态码为200，表示请求成功执行
			if (xmlhttp.status == 200) {
				// 调用传递过来的回调函数，并将返回结果当成普通文本传递给回调函数
				functionName(xmlhttp.responseText, obj);
			} else {
				// 如果请求没有顺利执行，在异常处理回调函数不为空的情况下，调用该回调
				if (errorFunctionName != null) {
					errorFunctionName(url);
				}
			}
		}
	};
	// 创建http请求
	xmlhttp.open(methodtype, url, contype);
	// 发送请求
	xmlhttp.send(parameter);
}

// Ajax请求函数，该请求将服务器返回的结果当成xml dom交由回调函数处理
// 参数url:需要请求的URL地址，由于不同浏览器对缓存的处理方式不同，建议本地址在末尾添加一个随机值参数，保证每次请求都能发送到服务器
// 参数methodtype:发送请求时用的请求方法，常用的有get和post，以字符串方式发送
// 参数contype:连接类型，true表示异步，false表示同步（在动态加载JS代码等功能时需要同步请求）
// 参数parameter:需要在请求体中发送给服务器的附件参数，以"参数名=参数值&参数名=参数值..."的形式体现，在使用post方式请求时使用
// 参数functionName：请求成功后调用的回调函数
// 参数obj:传递给回调函数的附带参数
// 参数errorFunctionName：如果请求没有成功执行的情况自动回调的函数，可以传递null
function xmlAjaxRequest(url, methodtype, contype, parameter, functionName, obj,
		errorFunctionName) {
	// 获取XmlHttpRequest对象用以发送请求并获取结果
	var xmlhttp = getajaxHttp();
	// 请求回调
	xmlhttp.onreadystatechange = function() {
		// 在请求已经成功返回的情况下执行
		if (xmlhttp.readyState == 4) {
			// 如果请求返回的状态码为200，表示请求成功执行
			if (xmlhttp.status == 200) {
				// 调用传递过来的回调函数，并将返回结果当成普通文本传递给回调函数
				functionName(xmlhttp.responseXML, obj);
			} else {
				// 如果请求没有顺利执行，在异常处理回调函数不为空的情况下，调用该回调
				if (errorFunctionName != null) {
					errorFunctionName(url);
				}
			}
		}
	};
	// 创建http请求
	xmlhttp.open(methodtype, url, contype);
	// 发送请求
	xmlhttp.send(parameter);
}
