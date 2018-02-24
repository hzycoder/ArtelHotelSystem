(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: '../js/'
	});
	var paging,
		layerTips,
		layer,
		form;

	//view方法：
	var iView = {
		init: function() {
			$("#research").on("click",function(){
				iEvent.getAllUser();
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			layui.use(["paging", "form"], function() {
				paging = layui.paging();
				layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
				layer = layui.layer; //获取当前窗口的layer对象
				form = layui.form();
				//				iEvent.initPage(); //可以这样写
			});
		},
		getAllUser:function(){
			$.ajax({
				type: "GET",
				url: "http://localhost:8080/HotelSystemServer/getAllUser",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				success: function(data) {
					console.log("======AjaxSUCCESS======");
					console.log("返回数据:"+JSON.stringify(data));
					if(data && data.success) {//如果登录成功
						layer.msg(data.msg, {//显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("获取数据失败", {//显示失败信息
							icon: 2,
						});
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("======AjaxERROR======");
					console.log(JSON.stringify(jqXHR));
					console.log("======AjaxERROR end======");
				}
			});
		},
	};
	window.onload = function() {
		iView.init();
		iEvent.init();
	};
}());