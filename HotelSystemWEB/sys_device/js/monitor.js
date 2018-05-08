(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: '../js/'
	});
	var paging,
		layerTips,
		layer,
		form,
		ws,
		SELECTED_HOTELID;
	var hotelData;
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
			//三个发送指令按钮
			$("body").on("click",".lockerBtn",function(e){
				$this = $(this);
				iEvent.sendInstructions("lock",$this.parent().parent().attr("id"));
			});
			$("body").on("click",".remoteBtn",function(){
				iEvent.sendInstructions("remote",$this.parent().parent().attr("id"));
			});
			$("body").on("click",".infraredBtn",function(){
				iEvent.sendInstructions("infrare",$this.parent().parent().attr("id"));
			});

//			$(".deviceStatusExplainIcon").on("click",function(){
//				iEvent.changeRoomStatus();
//			});
			//鼠标停留中继事件
			$("#repeatersContent").on("mouseover", ".repeatersList_repeater", function(e) {
				$this = $(this);
				layer.open({
					type: 4,
					content: [$this.text(), e.target], //数组第二项即吸附元素选择器或者DOM
					shade: 0,
				});
			}).on("mouseout", function(e) {
				layer.closeAll('tips');
			}).on("click", function() { //点击选中中继事件
				if($this.text() == "无设备") {
					layer.msg("该酒店下无中继，请先添加中继", {
						icon: 2,
						time: 2000,
						shade: 0.01,
					});
					return;
				}
				iView.repeaterListClick($this.attr("id"));
			});
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				$("#main").empty();
				if(data.value == "") { //取消选择酒店就取消选择中继
					$("#repeatersContent").empty();
					form.render("select", "monitorFormFilter");
					return;
				}
				SELECTED_HOTELID = data.value;
				iEvent.getAgent(data.value);
				iEvent.startWebSocket(data.value); //启动websocket
				iEvent.getTypeOfHotel(data.value);
			});
		},
		//渲染酒店select
		initHotelSelect: function(data) {
			$(data).each(function(i) {
				$("#hotelSelect").append('<option value="' + data[i].idHotelList + '">酒店名称：' + data[i].hotelName + ' #酒店编号：' + data[i].hotelId + ' #酒店地址：' + data[i].address + '</option>')
			});
			form.render('select');
		},
		//监听点击中继列表
		repeaterListClick: function(id) {
			$("#main").empty();
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
			iEvent.getAgentAndRoomRelations(id);
		},
		//渲染中继列表
		generateAgentList: function(deviceData) {
			$("#repeatersContent").empty();
			if(deviceData.length == 0) {
				$("#repeatersContent").append('<div class="repeatersList_repeater">' + "无设备" + '</div>');
				return;
			}
			$(deviceData).each(function(i) {
				$("#repeatersContent").append('<div class="repeatersList_repeater slotId' + deviceData[i].idAgentList + '"  id = "slotId' + deviceData[i].idAgentList + '">' + deviceData[i].macAddress + '</div>');
			});
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
					var thisHotel = iEvent.getHotelDataFromArray(hotelId);
					var html = '<div id="repeaters">' +
						'<div class="repeater">' +
						'<span class="agentNum">' + thisHotel.hotelName + '</span>' +
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
						var soltHtml = '<div class="solt" id=soltId>' +
							'<div class="upLine"></div>' +
							'<div class="soltHead slotId'+ item.idSoltList+'">' +
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
					iView.renderDeviceStatus();
				}
			});
		},
		//生成中继视图
		generateAgentView: function(type, agentData) {
			if(type == 0) {
				console.log("是否是零" + agentData)
				var slotId = iEvent.getslotIdByAgentId(agentData);
				//没有绑定房间的中继视图
				var html = '<div class="solt"  id="soltId">' +
					'<div class="soltHead slotId' + slotId + '">' +
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
					'<div class="soltHead slotId' +  agentData[0].idsoltList + '">' +
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
		//渲染取电开关（不包括中继）状态
		renderDeviceStatus: function() {
			$.each(deviceStatus, function(index, item) {
				var tempItem = iEvent.analyzeItem(item);
				//添加mouseover和mouseout效果
				var slotId = "soltId" + item.slotId;
				$("#" + slotId + " > .soltHead").on("mouseover", function(e) {
					layer.open({
						type: 4,
						content: ['当前时间：' + new Date() 
						+ '</br>房灯状态：' + tempItem.isRoomLightOn 
						+ '</br>门锁状态：' + tempItem.lockStatus.isLock
						+',&nbsp;'+tempItem.lockStatus.isOnline
						+',&nbsp;'+tempItem.lockStatus.lockInside
						+',&nbsp;'+tempItem.lockStatus.unlockType
						+ '</br>子设备注册状态：' + tempItem.isChildDeviceRegister 
						+ '</br>子设备在线状态：' + tempItem.isChildDeviceOnline, e.target], //数组第二项即吸附元素选择器或者DOM
						shade: 0,
					});
				}).on("mouseout", function(e) {
					layer.closeAll('tips');
				});
			});
			layer.closeAll("dialog");
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.getAllHotel();
		},
		//获取用户列表(酒店列表)
		getAllHotel: function() {
			var userData = JSON.parse(sessionStorage.getItem("user"));
			var index = layer.msg('加载数据中...', {
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
					hotelData = data.data;
					layer.close(index);
					iView.initHotelSelect(data.data);
				}
			});
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
						console.log("组网类型：" + type + "=====" + hotelId)
						iView.generateInnView(hotelId)
					}
				}
			});
		},
		//判断中继是否和房间绑定
		getAgentAndRoomRelations: function(agentId) {
			var agentId = agentId.replace("slotId", "");
			layer.msg('加载数据中,请稍等...', {
				icon: 16,
				shade: 0.01,
			});
			ZY.ajax({
				"url": "device/getAgentAndRoomRelations",
				"type": "GET",
				"data": {
					"agentId": agentId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("判断绑定数据：" + JSON.stringify(data));
					if(data.data.length == 0) {
						iView.generateAgentView(0, agentId);
					} else {
						iView.generateAgentView(1, data.data);
					}
				}
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
					console.log("++++++++++++++++++")
					console.log(JSON.stringify(data))
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
							'<div class="soltHead slotId' + item.idSoltList + '">' +
							'<span class="roomNum">' +
							item.roomNum +
							'</span>' +
							'<div class="serverLights"><div class="serverLight" ></div><div class="serverLight"></div><div class="serverLight"></div><div class="serverLight"></div></div>' +
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
					iView.renderDeviceStatus();
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
					iView.generateAgentList(data.data);
				}
			});
			layer.close(index);
		},
		//websocket连接服务端
		startWebSocket: function(hotelId) {
			var url = 'ws://' + CONFIG.WS_URL + '?hotelId=' + hotelId;
			if('WebSocket' in window) {
				ws = new ReconnectingWebSocket(url, null, {
					debug: true,
					maxReconnectAttempts: 3,
				});
			} else if('MozWebSocket' in window) { //兼容火狐
				ws = new MozWebSocket(url);
			} else { //兼容其他
				ws = new SockJS(url);
			}
			ws.onopen = function(evnt) {
				console.log("websocket连接上");
			};
			ws.onmessage = function(evnt) {
				console.log("[接收来自服务端的数据]:" + event.data);
				//接收数据做页面渲染处理
				iEvent.changeRoomStatus(event.data);
			};
			ws.onerror = function(evnt) {
				layer.msg("尝试连接...");
				console.log("websocket错误");
			};
			ws.onclose = function(evnt) {
				console.log("websocket关闭");
			}
		},
		//根据agentId获取slotId
		getslotIdByAgentId: function(agentId) {
			var agentId = agentId.replace("slotId", "");
			var index = layer.msg("加载数据中，请稍等...", {
				icon: 16,
				shade: 0.01
			});
			var slotId;
			ZY.ajax({
				"url": "device/getslotIdByAgentId",
				"type": "GET",
				//				"async": false,
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
		getHotelDataFromArray: function(hotelId) {
			console.log("hotelID:" + hotelId)
			var thisHotel;
			$.each(hotelData, function(index, item) {
				if(item.idHotelList == hotelId) {
					thisHotel = item;
					return false; //在each中跳出整个循环相当于break
				}
			});
			return thisHotel;
		},
		changeRoomStatus: function(statusData) {
			var statusData = JSON.parse(statusData);
			if (deviceStatus["slotId"+statusData.slotId] == undefined) {//当前监测没有该卡槽
				return ;
			}
			//更新deviceStatus
			deviceStatus["slotId"+statusData.slotId]["isRoomLightOn"] = statusData.isRoomLightOn;
			deviceStatus["slotId"+statusData.slotId]["lockStatus"] = statusData.lockStatus;
			deviceStatus["slotId"+statusData.slotId]["isChildDeviceRegister"] = statusData.isChildDeviceRegister;
			deviceStatus["slotId"+statusData.slotId]["isChildDeviceOnline"] = statusData.isChildDeviceOnline;
			if(!statusData.isDeviceOnline) {//设备是否在线
				$(".slotId" + statusData.slotId).css("background-color", "darkgray");
				return false;
			} else {
				$(".slotId" + statusData.slotId).css("background-color", "#007cc2");
			}
			if(!statusData.slotIllegal) {
				$(".slotId" + statusData.slotId).css("background-color", "#ff5722");
			} else {
				$(".slotId" + statusData.slotId).css("background-color", "#007cc2");
			}
			//设置四个服务灯
			var serverLightArray = new Array();
			var serverLightData = statusData.isServiceLightOn;
			serverLightArray = serverLightData.split("");
			$.each(serverLightArray, function(index,item) {
				if (item=="1") {
						var index = Number(index)+1
					$(".slotId" + statusData.slotId +"> .serverLights div:nth-child("+ index +")" ).addClass("layui-anim layui-anim-fadein layui-anim-loop").css("background-color", "#ff5722");
				} else{
					$(".slotId" + statusData.slotId +"> .serverLights div:nth-child("+ index +")" ).removeClass("layui-anim layui-anim-fadein layui-anim-loop").css("background-color", "white");
				}
			});
		},
		analyzeItem:function(item){
			var array = new Array();
			var tempData;
			var tempItem = {
				"isRoomLightOn": "",
				"lockStatus": {
					"isLock":"",
					"unlockType":"",
					"lockInside":"",
					"isOnline":"",
				},
				"isChildDeviceRegister": "",
				"isChildDeviceOnline": ""
			};
			//是否开灯
			if (item["isRoomLightOn"]) {
				tempItem.isRoomLightOn = "开灯";
			} else{
				tempItem.isRoomLightOn = "关灯";
			}
			//门锁状态
			tempData = item.lockStatus;
			array = tempData.split("");
			if (array[0]=="1") {
				tempItem.lockStatus["isLock"] = "开门";
			}else{
				tempItem.lockStatus["isLock"] = "关门";
			}
			if (array[1]=="1") {
				tempItem.lockStatus["unlockType"] = "密码";
			}else{
				tempItem.lockStatus["unlockType"] = "房卡";
			}
			if (array[2]=="1") {
				tempItem.lockStatus["lockInside"] = "已反锁";
			}else{
				tempItem.lockStatus["lockInside"] = "未反锁";
			}
			if (array[3]=="1") {
				tempItem.lockStatus["isOnline"] = "在线";
			}else{
				tempItem.lockStatus["isOnline"] = "离线";
			}
			//子设备注册状态
			//子设备在线状态
			tempItem.isChildDeviceRegister = item.isChildDeviceRegister;
			tempItem.isChildDeviceOnline = item.isChildDeviceOnline;
			return tempItem;
		},
		//发送指令
		sendInstructions:function(type,slotId){
			console.log("当前酒店:"+SELECTED_HOTELID);
			var json = {
				"hotelId":SELECTED_HOTELID,
				"type":type,
				"slotId":slotId,
			};
			ws.send(JSON.stringify(json))
		}
	};
}());