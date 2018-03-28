(function() {
	//全局变量定义：
	var layer,
		form,
		table;
	var btnArray = new Array();
	var hotelList = null;
	btnArray.push('<span style="color: #ffffff;transition:color 1s linear;">确定</span>');
	btnArray.push('<span>取消<span>');
	layui.use(["form", ], function() {
		layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
		layer = layui.layer; //获取当前窗口的layer对象
		form = layui.form;
		layer.ready(function() {
			iEvent.init();
			iView.init();
		})
	});
	//view方法：
	var iView = {
		init: function() {
			iEvent.getAllHotel();
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				iEvent.getDeviceCount(data.value);
				$.each(hotelList, function(index, item) {
					if(data.value == item.idHotelList) {
						$("#hotelName").val(item.hotelName);
						$("#hotelId").val(item.hotelId);
						$("#address").val(item.address);
					}
				});
				if(data.value == "") {
					$("#resetFrom").click()
				}
			});
			form.on('submit(upgrade)', function(data) {
				iEvent.upgrade();
				return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//提交升级
		upgrade: function() {
			if($("#hotelSelect").val() == "") {
				layer.msg("请先选择酒店", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			if($("#agentCount").val() == 0) {
				layer.msg("该酒店无设备可升级", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			iEvent.openPassVerfiy();
		},
		//打开验证框
		openPassVerfiy: function() {
			layer.open({
				type: 2,
				title: "验证密码",
				area: ["400px", "150px"],
				content: ["verifypass.html"],
				btn: btnArray,
				btnAlign: "c",
				success: function(layero, index) {

				},
				yes: function(index, layero) {
					var $body = layer.getChildFrame("body", index);
					$body.find("#commit").click();
				},
			});
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value="' + hotelData[i].idHotelList + '">酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
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
					hotelList = data.data;
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
		//获取设备数量
		getDeviceCount: function(hotelId) {
			var index = layer.msg('加载数据中...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/getDeviceCountByHotelId",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log(JSON.stringify(data))
					$("#deviceCount").val(data.data.deviceCount);
					$("#agentCount").val(data.data.agentCount);
					layer.close(index);
				}
			})
		}
	};

	window.onload = function() {};
}());