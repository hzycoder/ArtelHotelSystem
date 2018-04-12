(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: '../js/'
	});
	var paging,
		layerTips,
		layer,
		form;
	layui.use(["paging", "form"], function() {
		paging = layui.paging();
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
				$("#main").empty();
				if(data.value == "") { //取消选择酒店就取消选择中继
					$("#agentSelect").val("");
					form.render("select", "monitorFormFilter");
					return;
				}
				iEvent.getAgent(data.value);
				iEvent.getTypeOfHotel(data.value);
			});
			//监听中继选择下拉框
			form.on('select(agentSelect)', function(data) {
				$("#main").empty();
				if(data.value == "") {
					return;
				}
				var html = '<div id="repeaters">' +
					'</div>' +
					'<div class="connect">' +
					'<div id="toRepeaterLine"></div>' +
					'<div id="mainLine"></div>' +
					'<div id="soltList">' +
					'</div>' +
					'</div>';
				var $html = $(html);
				$("#main").append($html);
				iEvent.getAgentAndRoomRelations(data.value);
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//获取用户列表(酒店列表)
		getAllHotel: function() {
			var userData = JSON.parse(sessionStorage.getItem("user"));
			var index =layer.msg('加载数据中...', {
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
					layer.close(index);
					iEvent.initHotelSelect(data.data);
				}
			});
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value="' + hotelData[i].idHotelList + '">酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select');
		},
		//获取deviceCountList,判断是中继还是客栈
		getTypeOfHotel: function(hotelId) {
			var hotelType;
			var index = layer.msg('加载数据中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "hotel/getTypeOfHotel",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
					var type = "Inn";
					$.each(data.data, function(index, item) {
						if(item != 1) {
							type = "hotel";
						}
					});
					if(type == "Inn") {
						$("#agentSelectItem").hide();
						console.log("组网类型："+type +"====="+hotelId)
						iEvent.generateInnView(hotelId)
					}else{
						$("#agentSelectItem").show();
					}
				}
			});
		},
		//渲染中继select
		initAgentSelect: function(deviceData) {
			$("#agentSelect").empty();
			$("#agentSelect").append('<option value=""></option>');
			if(deviceData.length == 0) {
				$("#agentSelect").append('<option disabled>该酒店暂无设备，请先添加设备</option>');
				form.render('select');
				return;
			}
			$(deviceData).each(function(i) {
				$("#agentSelect").append('<option value="' + deviceData[i].idAgentList + '">设备地址：' + deviceData[i].macAddress + '</option>')
			});
			form.render('select');
		},
		//判断中继是否和房间绑定
		getAgentAndRoomRelations: function(agentId) {
			layer.load('加载数据中ff,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/getAgentAndRoomRelations",
				"type": "GET",
				"data": {
					"agentId": agentId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
//					layer.close(index);
					console.log("判断绑定数据：" + JSON.stringify(data));
					if(data.data.length == 0) {
						iEvent.generateAgentView(0, agentId);
					} else {
						iEvent.generateAgentView(1, data.data);
					}
				}
			});
		},
		//生成中继视图
		generateAgentView: function(type, agentData) {
			if(type == 0) {
				var slotId = iEvent.getslotIdByAgentId(agentData);
				//没有绑定房间的中继视图
				var html = '<div class="solt"  id="soltId' + slotId + '">' +
					'<div class="soltHead">' +
					'<span class="roomNum">' +
					"当前中继" +
					'</span>' +
					'</div>' +
					'</div>';
				var $html = $(html);
				$("#repeaters").append($html);
				iEvent.getSolt(agentData);
			} else {
				var html = '<div class="solt"  id="soltId' + agentData[0].idsoltList + '">' +
					'<div class="soltHead">' +
					'<span class="roomNum">' +
					agentData[0].roomNum +
					'</span>' +
					'</div>' +
					'</div>';
				var $html = $(html);
				$("#repeaters").append($html);
				//已经绑定房间的中继视图
				iEvent.getSolt(agentData[0].idAgentList);
			}
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
					layer.close(index);
					if(data.data.length == 0) {
						var zero = '<span id="zero">当前中继下无设备</span>'
						var $zero = $(zero);
						$("#soltList").append($zero);
						return;
					}
					$.each(data.data, function(index, item) {
						var soltHtml = '<div class="solt" id=soltId' + item.idSoltList + '>' +
							'<div class="upLine"></div>' +
							'<div class="soltHead">' +
							'<span class="roomNum">' +
							item.roomNum +
							'</span>' +
							'</div>' +
							'<div class="tridentLine">' +
							'<div class="downLine"></div>' +
							'<div class="threeLine">' +
							'<div class="innerLine"></div>' +
							'</div>' +
							'</div>' +
							'<div class="soltOperations">' +
							'<div class="lockerBtn">' +
							'<span> 门锁</span>' +
							'</div>' +
							'<div class="remoteBtn">' +
							'<span>遥控</span>' +
							'</div>' +
							'<div class="infraredBtn">' +
							'<span>红外</span>' +
							'</div>' +
							'</div>' +
							'</div>';
						var $soltHtml = $(soltHtml);
						$("#soltList").append($soltHtml);
					});
					iEvent.startWebSocket(data.value);
					//从数据库获取当前设备的状态
					//并把下面这句放在succes里面
					iEvent.renderDeviceStatus();
				}
			});
			layer.close(index);
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
				}
			});
			layer.close(index);
		},
		//生成公寓视图
		generateInnView: function(hotelId) {
			var index = layer.msg('加载客栈视图中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/getAgentByInn",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("客栈数据："+JSON.stringify(data))
					var html = '<div id="repeaters">' +
						'<div class="repeater">' +
						'<span class="agentNum">' + "客栈" + '</span>' +
						'</div>' +
						'</div>' +
						'<div class="connect">' +
						'<div id="toRepeaterLine"></div>' +
						'<div id="mainLine"></div>' +
						'<div id="soltList">' +
						'</div>' +
						'</div>';
					var $html = $(html);
					$("#main").append($html);
					$.each(data.data, function(index, item) {
						var soltHtml = '<div class="solt" id=soltId' + item.idSoltList + '>' +
							'<div class="upLine"></div>' +
							'<div class="soltHead">' +
							'<span class="roomNum">' +
							item.roomNum +
							'</span>' +
							'</div>' +
							'<div class="tridentLine">' +
							'<div class="downLine"></div>' +
							'<div class="threeLine">' +
							'<div class="innerLine"></div>' +
							'</div>' +
							'</div>' +
							'<div class="soltOperations">' +
							'<div class="lockerBtn">' +
							'<span> 门锁</span>' +
							'</div>' +
							'<div class="remoteBtn">' +
							'<span>遥控</span>' +
							'</div>' +
							'<div class="infraredBtn">' +
							'<span>红外</span>' +
							'</div>' +
							'</div>' +
							'</div>';
						var $soltHtml = $(soltHtml);
						$("#soltList").append($soltHtml);
					});
					iEvent.renderDeviceStatus();
				}
			});
		},
		//websocket连接服务端
		startWebSocket: function(agentId) {
			var url = 'ws://' + CONFIG.WS_URL + '?agentId=' + agentId;
			var ws;
			if('WebSocket' in window) {
				ws = new ReconnectingWebSocket(url, null, {
					debug: true,
					maxReconnectAttempts: 10,
				});
			} else if('MozWebSocket' in window) { //兼容火狐
				ws = new MozWebSocket(url);
			} else { //兼容其他
				ws = new SockJS(url);
			}
			ws.onopen = function(evnt) {
				console.log("websocket连接上");
				//				ws.send("我是客户端")
			};
			ws.onmessage = function(evnt) {
				console.log("[接收来自服务端的数据]:" + event.data)
			};
			ws.onerror = function(evnt) {
				console.log("websocket错误");
			};
			ws.onclose = function(evnt) {
				console.log("websocket关闭");
			}
		},
		//渲染取电开关（不包括中继）状态
		renderDeviceStatus: function() {
			$.each(deviceStatus, function(index, item) {
				//添加mouseover和mouseout效果
				var soltID = "soltId" + item.soltId;
				$("#" + soltID + " > .soltHead").on("mouseover", function(e) {
					layer.open({
						type: 4,
						content: ['当前时间：' + new Date() + '</br>设备状态：' + item.soltStatus + '</br>设备ID：' + item.soltId, e.target], //数组第二项即吸附元素选择器或者DOM
						shade: 0,
					});
				}).on("mouseout", function(e) {
					layer.closeAll('tips');
				});
			});
			layer.closeAll("loading");
		},
		//根据agentId获取slotId
		getslotIdByAgentId: function(agentId) {
			var index  = layer.msg("加载数据中，请稍等...",{
				icon:16,
				shade:0.01
			});
			var slotId;
			ZY.ajax({
				"url": "device/getslotIdByAgentId",
				"type": "GET",
				"async": false,
				"data": {
					"agentId": agentId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
					console.log(JSON.stringify(data));
					slotId = data.data;
				}
			});
			return slotId;
		},
	};
}());