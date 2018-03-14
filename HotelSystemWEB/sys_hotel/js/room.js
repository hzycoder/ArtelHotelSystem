(function() {
	//全局变量定义：
	var layer,
		form;
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
		})
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
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
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
					iEvent.roomPaging(data.data);
					console.log("获取的房间" + JSON.stringify(data));
				}
			});
		},
		/*
		 * 表格重载分页
		 * @param roomData 房间列表JSON
		 */
		roomPaging: function(roomData) {
			layui.use('table', function() {
				var table = layui.table;
				table.render({
					id: "roomTable",
					elem: '#roomTable',
					height: 380,
					data: roomData,
					page: {
						limit: 5,
						layout: ['count', 'prev', 'page', 'next', 'skip'],
						theme: 'zypage',
					},
					cols: [
						[ //表头
							{
								field: 'roomNum',
								title: '房间名称',
								width: 200,
								sort: true
							},
							{
								field: 'soltNum',
								title: '设备编号',
								width: 240,
								sort: true
							}, {
								field: 'floor',
								title: '层数',
								width: 180,
								sort: true
							}, {
								field: '',
								title: '操作',
								width: 200,
								toolbar: "#roomTableBar",
							}
						]
					]
				});
				//监听工具条操作
				table.on('tool(roomTableFilter)', function(obj) {
					var layEvent = obj.event; //获取当前点击的事件
					var data = obj.data; //获取当行数据
					console.log(JSON.stringify(data))
					if(layEvent == "del") { //删除房间
						layer.confirm('确定要删除房间号为：' + data.roomNum + "的房间吗？", {
							btn: ['确定', '取消'],
							yes: function(index, layero) {
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
							area:["600px","310px"],
							content: ["modifyRoom.html"],
							btn: btnArray,
							btnAlign: "c",
							success:function(layero,index){
								console.log("SUCCESS!!!!")
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
						});
					}
				});
			});
		},
		//删除房间
		delRoom: function(roomData) {
			console.log(roomData)
			layer.msg('删除中', {
				icon: 16,
				shade: 0.01,
				time: false,
			});
			ZYAjax.ajax({
				"url": "HotelSystemServer/delRoom",
				"type": "POST",
				"data": {
					"roomID": roomData.idRoomList,
				},
				//"contentType": "application/json;charset=UTF-8",//单个参数 不需要这个
				"success": function(data) {
					console.log(JSON.stringify(data));
					layer.closeAll();
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
	};

	window.onload = function() {};
}());