(function() {
	//全局变量定义：
	var form;
	layui.use(["form",], function() {
		layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
		layer = layui.layer; //获取当前窗口的layer对象
		form = layui.form;
		layer.ready(function() {
			iEvent.init();
			iView.init();
		});
	});
	//view方法：
	var iView = {
		init: function() {
			iEvent.getAllUser();
			form.on('submit(addHotel)', function(data) {
				console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
				iEvent.addHotel(data.field);
				return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
			});
			$("#hotelID").blur(function(){
				iView.verifyHotelID();
			});
		},
		verifyHotelID:function(){
			console.log("验证")
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//添加酒店
		addHotel: function(hotelData) {
			ZY.ajax({
				"url": "hotel/addHotel",
				"type": "POST",
				"data": JSON.stringify(hotelData),
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						console.log(data.msg)
						layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
			$('#resetBtn').click();//重置表单
		},
		//渲染
		initUserSelect: function(userData) {
			$(userData).each(function(i) {
				$("#accountSelect").append('<option value="' + userData[i].idUserList + '">' + userData[i].account + '</option>')
			});
			form.render('select')
		},
		getAllUser: function() {
			ZY.ajax({
				"url": "user/getAllUser",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					iEvent.initUserSelect(data.result);
					if(data && data.success) { //如果登录成功
					} else {
						layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
	};
}());