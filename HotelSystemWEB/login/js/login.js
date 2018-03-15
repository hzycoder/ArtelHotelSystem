//使用匿名函数
(function() {
	//全局变量定义：
	var layer, form;
	layui.use(["layer", "form"], function() {
		layer = layui.layer;
		form = layui.form();
		layer.ready(function() {
			iEvent.init();
			iView.init();
		});
	});
	//view方法：
	var iView = {
		init: function() {
			$("#codeImg").attr("src","http://localhost:8080/HotelSystemServer/base/captcha");
			$(".login").on("click", function() {
				iEvent.login();
			});
		},

	};
	//event方法：
	var iEvent = {
		init: function() {},
		//登录
		login: function() {
			layer.msg('登录中...', {
				icon: 16,
				shade: 0.03,
			});
			var loginJson = {
				"account": $("#userName").val(),
				"password": $("#password").val()
			}
			ZYAjax.ajax({
				"url": "HotelSystemServer/login",
				"type": "POST",
				"contentType": "application/json;charset=UTF-8",
				"data": JSON.stringify(loginJson),
				"success": function(data) {
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
						//跳转页面
						sessionStorage.setItem("user", JSON.stringify(data.result));
						setTimeout(function() {
							window.location.href = "../index.html";
						}, 300);
					} else {
						layer.msg(data.error, { //显示失败信息
							icon: 2,
						});
					}
				},
			});
		},
	};

}());