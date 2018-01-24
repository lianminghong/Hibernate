String.prototype.trim = function() {
	var p = /^\s*/;
	var str = this.replace(p, "");
	p = /\s*$/;
	str = str.replace(p, "");
	return str;
};

function Ajax() {
	this.get = function(url, succ, failure) {
		// 1创建一个ajax对象
		var xhr = null;
		if (window.XMLHttpRequest) {// IE7之后,火狐,google
			xhr = new XMLHttpRequest();
		} else {// IE6及以下,其它大部分旧版本的浏览器
			xhr = new ActiveXObject("Microsoft.XMLHttp");
		}
		// 2 设置通讯方式和地址
		xhr.open("GET", url, true);// 异步--多线程
		// 3 设置访问成功后的 js对象(回调函数)
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {// 服务器的响应消息接收完毕
				if (xhr.status == 200) {// 服务器正常响应
					var txt = xhr.responseText;// 后台的响应信息
					succ(txt);
				} else {
					if (failure) {
						failure(xhr.status);
					}
				}
			}
		};
		// 4发送---Get方式，没有参数(请求体) ---数据在请求地址中
		xhr.send(null);
	};

	this.post = function(url, data, succ, failure) {
		// 1创建一个ajax对象
		var xhr = null;
		if (window.XMLHttpRequest) {// IE7之后,火狐,google
			// alert("XMLHttpRequest...");
			xhr = new XMLHttpRequest();
		} else {// IE6及以下,其它大部分旧版本的浏览器
			// alert("ActiveXObject...");
			xhr = new ActiveXObject("Microsoft.XMLHttp");
		}
		// 2 设置通讯方式和地址
		xhr.open("POST", url, true);// ※异步--多线程

		// 3 设置访问成功后的 js对象(回调函数)
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {// 服务器的响应消息接收完毕
				if (xhr.status == 200) {// 服务器正常响应
					var txt = xhr.responseText;// 后台的响应信息
					succ(txt);
				} else {
					if (failure) {
						failure(xhr.status);
					}
				}
			}
		};
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

		// 4发送---Post方式，有参数(请求体) <---数据 ※
		xhr.send(data);
	};

}