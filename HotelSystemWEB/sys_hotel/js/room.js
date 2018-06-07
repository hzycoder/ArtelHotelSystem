(function() {
	//全局变量定义：
	var layer,
		form,
		table;
	var btnArray = new Array();
	btnArray.push('<span style="color: #ffffff;transition:color 1s linear;">确定</span>');
	btnArray.push('<span>取消<span>');
	layui.use(["form", "table"], function() {
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
			$("#refresh").on("click",function(){
				iEvent.refreshRoom();
			});
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				$("#layui-elem-field").empty();
				if (data.value == "") {
					table.reload("roomTable",{
						data:null,
					})
					return;
				}
				iEvent.getRoom(data.value);
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
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
					console.log("获取的房间"+JSON.stringify(data))
					layer.close(index);
					iEvent.roomPaging(data.data);
				}
			});
		},
		/*
		 * 表格重载分页
		 * @param roomData 房间列表JSON
		 */
		roomPaging: function(roomData) {
//			layui.use('table', function() {
//				var table = layui.table;
				table.render({
					id: "roomTable",
					elem: '#roomTable',
					height: 490,
					data: roomData,
					page: {
						limit: 8,
						layout: ['count', 'prev', 'page', 'next', 'skip'],
					},
					cols: [
						[ //表头
							{
								field: 'roomNum',
								title: '房间名称',
								width: 200,
							},
							{
								field: 'soltNum',
								title: '设备子网段',
								width: 240,
							}, {
								field: 'floor',
								title: '层数',
								width: 180,
							}, {
								field: '',
								title: '操作',
								width: 200,
								toolbar: "#roomTableBar",
							}
						]
					],
					done:function(){
						console.log("表格渲染完成!")
					},
				});
				//监听工具条操作
				table.on('tool(roomTableFilter)', function(obj) {
					var layEvent = obj.event; //获取当前点击的事件
					var data = obj.data; //获取当行数据
					if(layEvent == "del") { //删除房间
						layer.confirm('确定要删除房间号为：' + data.roomNum + "的房间吗？", {
							btn: ['确定', '取消'],
							yes: function(index, layero) {
//								obj.del();
								//确定
								iEvent.delRoom(data);
							},
							btn2: function(index, layero) {
								//取消
							}
						});
					} else if(layEvent == "mod") { //修改房间
						layer.open({
							type: 2,
							title: "修改房间信息",
							area:["600px","250px"],
							content: ["modifyRoom.html"],
							btn: btnArray,
							btnAlign: "c",
							success:function(layero,index){
								var $body = layer.getChildFrame("body",index);
								$body.find("#idRoomList").val(data.idRoomList);
								$body.find("#hotelID").val(data.hotelId);
								$body.find("#roomId").val(data.roomId);
								$body.find("#roomNum").val(data.roomNum);
								$body.find("#soltNum").val(data.soltNum);
								$body.find("#floor").val(data.floor);
							},
							yes:function(index,layero){
//								var iframeWin = window[layero.find("iframe")[0]["name"]];
//								iframeWin.document.getElementById("modifyRoomCommit").click();
								var $body = layer.getChildFrame("body",index);
								$body.find("#modifyRoomCommit").click();
							},
							end:function(){
								iEvent.getRoom($("#hotelSelect").val());
							}
						});
					}
				});
//			});
			
		},
		//删除房间
		delRoom: function(roomData) {
			var index = layer.msg('删除中', {
				icon: 16,
				shade: 0.01,
				time: false,
			});
			ZY.ajax({
				"url": "hotel/delRoom",
				"type": "POST",
				"async":false,	//不同步的话 重新渲染表格的时候有可能还没删除该列数据
				"data": {
					"roomID": roomData.idRoomList,
				},
				//"contentType": "application/json;charset=UTF-8",//单个参数 不需要这个
				"success": function(data) {
					layer.close(index);
					if(data && data.success) {
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg(data.msg, { //显示失败信息
							icon: 2,
						});
					}
				}
			});
			iEvent.getRoom($("#hotelSelect").val());
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
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
		refreshRoom:function(){
			if ($("#hotelSelect").val()=="") {
				return;
			} 
			iEvent.getRoom($("#hotelSelect").val());
		},
	};

	window.onload = function() {};
}());