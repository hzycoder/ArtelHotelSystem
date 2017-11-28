//使用匿名函数
(function() {
	var layer, form;
	var iView = {
		init: function() {
			$(".login").on("click", function() {
				iEvent.login();
			});
		},

	};
	var iEvent = {
		init: function() {
			layui.use(["layer", "form"], function() {
				layer = layui.layer;
				form = layui.form();
			});
		},
		//登录
		login: function() {
			console.log("按了登录");
			$.ajax({
				type:"post",
				url:"http://localhost:8080/HotelSystemServer/login",
				contentType:"application/json",
				dataType:"json",
				data:'{"account":"admin","password":"admin"}',
				async:false,
				success:function(data){
					console.log("回调成功");
					console.log(JSON.stringify(data));
				},
			});
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