(function() {
	//全局变量定义：
	var form,
	table;
	layui.use(["table","form", ], function() {
		layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
		layer = layui.layer; //获取当前窗口的layer对象
		form = layui.form;
		table = layui.table;
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
				if (data.value == "") {//取消选择酒店就取消选择中继
					$("#agentSelect").val("");
					form.render("select","deviceFormFilter");
					return;
				}
				iEvent.getAgent(data.value);
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象

			});
			//监听选择中继
			form.on('select(agentSelect)', function(data) {
				if(data.value == "") {
					console.log(JSON.stringify(table))
					table.reload("deviceTable",{
						data: null
					});
					return;
				}
				iEvent.getSolt(data.value);
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//表格重载分页
		devicePaging: function(deviceData) {
			layui.use('table', function() {
				var table = layui.table;
				var deviceTableID = table.render({
					id: "deviceTable",
					elem: '#deviceTable',
					height: 380,
					data: deviceData,
					page: {
						limit: 5,
						layout: ['count', 'prev', 'page', 'next', 'skip'],
						theme: 'zypage',
					},
					cols: [
						[ //表头
							{
								field: 'subNetNum',
								title: '子网段',
								width: 135,
							}, {
								field: 'roomNum',
								title: '所属房间',
								width: 80
							}, {
								field: 'floor',
								title: '楼层',
								width: 80,
							},
						]
					]
				});
				//监听工具条操作
				table.on('tool(hotelTableFilter)', function(obj) {
					var layEvent = obj.event; //获取当前点击的事件
					var data = obj.data; //获取当行数据
					console.log("======" + JSON.stringify(data));
					if(layEvent == "del") {
						layer.confirm('确定要删除编号为：' + obj.data.hotelId + "的酒店吗？", {
							btn: ['确定', '取消'],
							yes: function(index, layero) { //确定
								iEvent.delHotel(obj.data);
							},
							btn2: function(index, layero) {
								//取消
							}
						});
					} else if(layEvent == "mod") {
						layer.open({
							type: 2,
							title: "修改酒店信息",
							area: ["900px", "550px"],
							content: ["modifyHotel.html"],
							btn: btnArray,
							btnAlign: "c",
							success: function(layero, index) {
								console.log("SUCCESS!!!!")
								var $body = layer.getChildFrame("body", index);
								$body.find("#hotelId").val(data.hotelId);
								$body.find("#hotelName").val(data.hotelName);
								$body.find("#idHotelList").val(data.idHotelList);
								$body.find("#address").val(data.address);
								$body.find("#hotelPhone").val(data.hotelPhone);
							},
							yes: function(index, layero) {
								var $body = layer.getChildFrame("body", index);
								$body.find("#modifyHotelCommit").click();
							},
						});
					}
				});
			});
		},
		//获取中继下的卡槽设备
		getSolt: function(agentId) {
			var index = layer.msg('加载卡槽设备中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/getSoltByAgentId",
				"type": "GET",
				"data": {
					"agentId": agentId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					if(data && data.success) {
						layer.close(index);
						console.log('获取中继下的设备' + JSON.stringify(data));
						iEvent.devicePaging(data.data);
					} else {
					}
				}
			});
			layer.close(index);
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value="' + hotelData[i].idHotelList + '">酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select')
		},
		//渲染中继select
		initAgentSelect: function(deviceData) {
			$("#agentSelect").empty();
			$("#agentSelect").append('<option value=""></option>');
			if(deviceData.length == 0) {
				console.log("=====0")
				$("#agentSelect").append('<option disabled>该酒店暂无设备，请先添加设备</option>');
				form.render('select');
				return;
			}
			$(deviceData).each(function(i) {
				$("#agentSelect").append('<option value="' + deviceData[i].idAgentList + '">设备地址：' + deviceData[i].macAddress + '</option>')
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
		//获取指定酒店的中继
		getAgent: function(hotelId) {
			var index = layer.msg('加载酒店中继中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/getAgentByHotelId",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
					iEvent.initAgentSelect(data.data);
					console.log("获取的中继" + JSON.stringify(data));
				}
			});
			layer.close(index);
		},
	};
}());