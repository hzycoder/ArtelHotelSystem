//使用匿名函数
(function() {
	//全局变量定义：
	var layer, form;
	//view方法：
	var iView = {
		init: function() {
			$(".login").on("click", function() {
				iEvent.login();
			});
		},

	};
	//event方法：
	var iEvent = {
		init: function() {
			layui.use(["layer", "form"], function() {
				layer = layui.layer;
				form = layui.form();
			});
		},
		//登录
		login: function() {
			//			手动验证
			//			if(!$("input[name='imgCode']").val()) {
			//				layer.msg('请输入验证码', {
			//					time: 1000,
			//					icon: 5,
			//					shade: .5,
			//					anim: 6
			//				});
			//				return false;
			//			}
			var userName = $("#userName").val();
			var password = $("#password").val()
			var json = {
				"account": userName,
				"password": password
			}
			layer.msg("登录成功！",{
				time:1500,
				icon:1,
				end:function(){
					window.location.href = "../index.html";
				},
			})
//			$.ajax({
//				type: "POST",
//				url: "http://localhost:8080/HotelSystemServer/login",
//				contentType: "application/json;charset=UTF-8",
//				dataType: "json",
//				data: JSON.stringify(json),
//				success: function(data) {
//					console.log("======AjaxSUCCESS======");
//					console.log("返回数据:"+JSON.stringify(data));
//					if(data && data.success) {//如果登录成功
//						layer.msg(data.msg, {//显示成功信息
//							icon: 1,
//						});
//						//跳转页面
//						sessionStorage.setItem("user",JSON.stringify(data.result));
//						setTimeout(function() {
//							window.location.href = "../index.html";
//						}, 1000);
//					} else {
//						layer.msg(data.error, {//显示失败信息
//							icon: 2,
//						});
//					}
//				},
//				error: function(jqXHR, textStatus, errorThrown) {
//					console.log("======AjaxERROR======");
//					//					console.log("readyState:"+jqXHR.readyState);
//					//					console.log("status:"+jqXHR.status);
//					//					console.log("statusText:"+jqXHR.statusText);
//					//					console.log("responseText:"+jqXHR.responseText);
//					//					console.log("======AjaxERROR end======");
//
//				}
//			});
		},
	};

	window.onload = function() {
		//		alert("onload init")
		//		var w = document.body.scrollWidth;
		//		var h = document.body.scrollHeight;
		iView.init();
		iEvent.init();
	};

}());