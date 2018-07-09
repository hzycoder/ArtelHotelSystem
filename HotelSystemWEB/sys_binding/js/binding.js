(function() {
	//全局变量定义：
	var form;
	var ws;
	var unbindedRoom, unbindedSolt,unbindeRoomNum,subNet;
	var curHotelId = undefined;
	var hotelData;
	var btnArray = new Array();
		btnArray.push('<span style="color: #ffffff;transition:color 1s linear;">确定</span>');
		btnArray.push('<span>取消<span>');
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
			$("#bindingBtn").on("click", function() {
				iEvent.checkBinding();
//				iEvent.binding();
			});
			$("#listenBtn").on("click", function() {
				$("#cardData").empty();
				iEvent.listening($("#cardNum").val());
			});
			$("#stopListenBtn").on("click", function() {
				unbindedSolt = undefined;
				$("#cardData").empty();
				ws.close(); //停止监听
			});
			$("body").on("click", "#roomData>tr", function(e) {
				$this = $(this);
				$this.addClass("trSelected");
				//				console.log($this.children()[2].innerText);
				unbindedRoom = $this.children()[2].innerText;
				unbindeRoomNum = $this.children()[0].innerText;
				$this.siblings().removeClass("trSelected");
			});
			$("body").on("click", "#cardData>tr", function() {
				$this = $(this);
				//				console.log($this.children()[1].innerText)
				unbindedSolt = $this.children()[1].innerText;
				subNet = $this.children()[2].innerText;
				$this.addClass("trSelected");
				$this.siblings().removeClass("trSelected");
			});
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				$("#roomData").empty();
				curHotelId  = undefined;
				console.log(ws);
				if(ws != undefined) {
					ws.close();
				}
				console.log("选择酒店" + data.value)
				if(data.value != "") {
					curHotelId = data.value;
					iEvent.getRoom(data.value);
				}
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//渲染
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value="' + hotelData[i].idHotelList + '">酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select')
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
					hotelData = data.data;
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
		//获取酒店房间
		getRoom: function(hotelId) {
			var index = layer.msg('加载房间中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "hotel/getUnbindedRooms",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					iEvent.generatedRommList(data.data);
					layer.close(index);
				}
			});
		},
		//生成房间表格
		generatedRommList: function(roomData) {
			$.each(roomData, function(index, item) {
				var roomListElement = '<tr><td>' +
					item.roomNum + '</td><td>' +
					item.floor + '</td><td style="display:none">' +
					item.idRoomList + '</td>';
				//					+item.idRoomList + '</td></tr>'

				var $roomListElement = $(roomListElement);
				$("#roomData").append($roomListElement);
			});
		},
		//发送监听的卡号
		//连接websocket
		listening: function(cardNum) {
			cardNum = cardNum.toUpperCase();
			$("#cardNum").val(cardNum);
			if($("#hotelSelect").val() == "") {
				layer.msg("请先选择酒店", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			var url = 'ws://' + CONFIG.WS_URL + '?cardNum=' + cardNum;
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
				$("#listenBtn").hide();
				$("#stopListenBtn").show();
				$("#cardNum").attr("disabled","disabled");
				ws.send("我是客户端")
			};
			ws.onmessage = function(evnt) {
				console.log("[接收来自服务端的数据]:" + event.data)
				iEvent.generatedReceviedList(event.data);
			};
			ws.onerror = function(evnt) {
				layer.msg("尝试连接...");
				console.log("websocket错误");
			};
			ws.onclose = function(evnt) {
				$("#listenBtn").show();
				$("#stopListenBtn").hide();
				$("#cardNum").removeAttr("disabled","disable");
				console.log("websocket关闭");
			}
		},
		generatedReceviedList: function(receviedData) {
			var receviedJson = JSON.parse(receviedData);
			if(receviedJson["STATUS"] == "CARD_OUT") {
//				console.log("拔卡动作")
				$("#cardData").find("tr").each(function(index, item) {
					if($(item).find("td").eq(1).text() == receviedJson["SLOT_ID"]) {
//						console.log("CARD_OUT_CARD_OUT_CARD_OUT_CARD_OUT_CARD_OUT_");
						item.remove()
						return false;
					}
//					console.log(item.childNodes[3].textContent)
				});
			} else {
//				console.log("插卡动作")
				var flag = 0;
//				console.log(receviedJson["SLOT_ID"])
				$("#cardData").find("tr").each(function(index, item) {
//					console.log(item);
//					console.log($(item).find("td").eq(1).text()+"   "+receviedJson["SLOT_ID"])
					if($(item).find("td").eq(1).text() == receviedJson["SLOT_ID"]) {
						console.log("已经 插卡");
						flag = 1;
						return false;
					}
				});
				console.log(flag)
				if (flag == 0) {
					var receviedListElement = '<tr><td class="cardNumTD">' +
					receviedJson["PARM"] + '</td><td class="slotIdTD">' +
					receviedJson["SLOT_ID"] + '</td><td class="timeTD">' +
					iEvent.switchUnixTime(receviedJson["TIME"]) + '</td></tr>';
				var $receviedListElement = $(receviedListElement);
				$("#cardData").append($receviedListElement);
				$('#receviedTableBody').scrollTop($('#receviedTableBody')[0].scrollHeight);
				}
			}
		},
		checkBinding:function(){
			console.log("房间号" + unbindedRoom + "======设备号：" + unbindedSolt+"=====酒店ID："+curHotelId);
			if (curHotelId == undefined) {
				layer.msg("请选择酒店", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			if(unbindedRoom == undefined || unbindedSolt == undefined) {
				layer.msg("请选择设备和房间", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			//获取当前选择酒店的数据
			var thisHotel;
			$.each(hotelData, function(index, item) {
				if(item.idHotelList == curHotelId) {
					thisHotel = item;
					return false; //在each中跳出整个循环相当于break
				}
			});
			console.log(JSON.stringify(thisHotel));
			var index = layer.msg('查询绑定信息中，请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/checkBinding",
				"type": "GET",
				"data": {
					"hotelId": curHotelId,
					"slotId": unbindedSolt,
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log(JSON.stringify(data.data));
					var datas = data.data;
					layer.close(index);
					if(data.success) {
						console.log(JSON.stringify(data));
						//未绑定设备
						if (datas["hotel"] == null || datas["room"] == null || datas["agent"] == null) {
						layer.open({
							title:"请核对以下绑定信息",
							area:["400px","200px"],
							content:
							"该设备未绑定"
							+ "</br>即将绑定酒店："+thisHotel.hotelName
							+ "</br>即将绑定房间："+unbindeRoomNum
							,
							btn:["确定","取消"],
							yes:function(index,layero){
								iEvent.binding(thisHotel.idHotelList,);
							},
						});
					}else{
						layer.open({
							title:"请核对以下绑定信息",
							area:["420px","300px"],
							content:
							"该设备已绑定酒店："+datas.hotel.hotelName
							+ "</br>该设备已绑定的中继："+datas.agent.macAddress
							+ "</br>该设备已绑定的房间："+datas.room.roomNum
							+ "</br>"
							+ "</br>即将绑定酒店："+thisHotel.hotelName
							+ "</br>即将绑定房间："+unbindeRoomNum
							,
							btn:["确定","取消"],
							yes:function(index,layero){
								iEvent.binding(thisHotel.idHotelList);
							},
						});
					}
					} else {
						layer.msg("查询信息失败！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		binding: function(hotelId) {
			console.log("房间号" + unbindedRoom + "======设备号：" + unbindedSolt);
			if(unbindedRoom == undefined || unbindedSolt == undefined) {
				layer.msg("请选择设备和房间", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			console.log("房间号" + unbindedRoom + "======设备号：" + unbindedSolt);
			var index = layer.msg('绑定中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "device/binding",
				"type": "POST",
				"data": {
					"roomId": unbindedRoom,
					"slotId": unbindedSolt,
					"hotelId":hotelId,
				},
				"success": function(data) {
					layer.close(index);
					if(data.success) {
						layer.msg("绑定成功！", { //显示失败信息
							icon: 1,
						});
						console.log(JSON.stringify(data));
						unbindedRoom = undefined;
						unbindedSolt = undefined;
						subNet = undefined;
						$("#cardData>tr").removeClass("trSelected");
						$("#roomData>tr").removeClass("trSelected");
					} else {
						layer.msg("绑定失败！", { //显示失败信息
							icon: 2,
						});
					}
					$("#roomData").empty();
					iEvent.getRoom($("#hotelSelect").val());
				}
			});
		},
		switchUnixTime: function(unixTime) {
			if(typeof unixTime != "number") {
				return unixTime;
			}
			var newDate = new Date();
			newDate.setTime(unixTime);
			return newDate.Format("hh:mm:ss");
		},
	};
}());