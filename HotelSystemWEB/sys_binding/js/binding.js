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
			$("#listeningBtn").on("click", function() {
				iEvent.listening($("#cardNum").val());
			});
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				//				$("#layui-elem-field").empty();
				//				if(data.value == "") {
				//					table.reload("roomTable", {
				//						data: null,
				//					})
				//					return;
				//				}
				iEvent.getRoom(data.value);
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
					console.log("获取的酒店" + JSON.stringify(data));
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
				"url": "hotel/getRooms",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("获取的房间" + JSON.stringify(data))
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
					item.floor + '</td><td>' +
					item.idRoomList + '</td></tr>'

				var $roomListElement = $(roomListElement);
				$("#roomListTable").append($roomListElement);
			});
		},
		//发送监听的卡号
		listening: function() {
			ZY.ajax({
				"url": "hotel/getRooms",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("获取的房间" + JSON.stringify(data))
					iEvent.generatedRommList(data.data);
					layer.close(index);
				}
			});
		},
		//连接websocket
		listening: function(cardNum) {
			var url = 'ws://' + CONFIG.WS_URL + '?cardNum=' + cardNum;
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
				ws.send("我是客户端")
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
	};
}());