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
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				iEvent.getRoom(data.value);
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
				
			});
			//监听设备类型选择下拉框
			form.on('select(deviceTypeSelect)', function(data) {
				if (data.value == 0) {//选中中继设备
					$("#roomInput").hide();
					$("#macAddressInput").show();
				} else if(data.value == 1){
					$("#roomInput").show();
					$("#macAddressInput").hide();
				} else{
					console.log(0000)
					$("#roomInput").hide();
					$("#macAddressInput").hide();
				}
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//添加酒店
		addHotel: function(hotelData) {
			ZYAjax.ajax({
				"url": "HotelSystemServer/addHotel",
				"type": "POST",
				"data": JSON.stringify(hotelData),
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
		//渲染
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value="' + hotelData[i].idHotelList + '">酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select')
		},
		//获取用户列表(酒店列表)
		getAllHotel: function() {
			layer.msg('加载数据中...', {
				icon: 16,
				shade: 0.01
			});
			ZYAjax.ajax({
				"url": "HotelSystemServer/getHotels",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("获取的酒店" + JSON.stringify(data));
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
		//获取酒店房间
		getRoom: function(hotelId) {
			layer.msg('加载房间中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZYAjax.ajax({
				"url": "HotelSystemServer/getRooms",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					iEvent.initRoomSelect(data.data);
					console.log("获取的房间" + JSON.stringify(data));
				}
			});
		},
		initRoomSelect:function(roomData){
			$(roomData).each(function(i) {
				$("#roomSelect").append('<option value="' + roomData[i].idRoomList + '">房间号：' + roomData[i].roomId + '</option>')
			});
			form.render('select');
		},
	};
}());