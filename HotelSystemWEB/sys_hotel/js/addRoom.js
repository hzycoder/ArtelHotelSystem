(function() {
	//全局变量定义：
	var form;
	layui.use(["form", ], function() {
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
			iEvent.getAllHotel();
			form.on('select(hotelSelect)', function(data) {
				if(data.value == "") {
					return;
				}
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
				var hotelNum = (data.value).split(":"); //赋值hotelNum
				$("#hotelID").val(hotelNum[0])
				$("#hotelNum").val(hotelNum[1])
			});
			form.on('submit(addRoom)', function(data) {
				if($("#hotelSelect").val() == "") {
					layer.msg("请先选择酒店", {
						icon: 2,
						time: 2000,
						anim: 6
					});
					return;
				}
				console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
				iEvent.addRoom(data.field);
				return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//		添加酒店
		addRoom: function(roomData) {
			ZY.ajax({
				"url": "hotel/addRoom",
				"type": "POST",
				"data": JSON.stringify(roomData),
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value=' + hotelData[i].idHotelList + ':' + hotelData[i].hotelId + '>酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select');
		},
		//获取用户列表(酒店列表)
		getAllHotel: function() {
			var userData = JSON.parse(sessionStorage.getItem("user"));
			layer.msg('加载数据中...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "hotel/getHotels",
				"type": "GET",
				"data": {
					"userID": userData.idUserList,
					"permission": userData.permission
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("获取的酒店" + JSON.stringify(data));
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
	};
}());