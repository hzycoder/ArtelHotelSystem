(function() {
	//全局变量定义：
	//	layui.config({ //使用自定义模块paging
	//		base: '../js/'
	//	});
	var layerTips,
		layer,
		form,
		table;
	var btnArray = new Array();
	btnArray.push('<span style="color: #ffffff;transition:color 1s linear;">确定</span>');
	btnArray.push('<span>取消<span>');
	layui.use(["form", ], function() {
		//		paging = layui.paging();
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
			$("#research").on("click", function() {
				iEvent.getHotelsByConditions();
			});
			$("#refresh").on("click", function() {
				$("#hotelNameInput").val("");
				$("#addressInput").val("");
				iEvent.getAllHotel();
			});
			$("body").on("click", "td:not(tr td:eq(6))", function(e) {
				$this = $(this);
				layer.open({
					type: 4,
					content: [$this.children().text(), e.target], //数组第二项即吸附元素选择器或者DOM
					shade: 0,
				});
			}).on("mouseout", function(e) {
				layer.closeAll('tips');
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.getAllHotel(); //获取所有酒店并分页初始化
		},
		//条件搜索酒店
		getHotelsByConditions: function() {
			var userData = JSON.parse(sessionStorage.getItem("user"));
			var index = layer.load(2);
			ZY.ajax({
				"url": "hotel/getHotelsByConditions",
				"type": "GET",
				//				"contentType": "application/json;charset=UTF-8",
				"data": {
					"hotelName": $("#hotelNameInput").val(),
					"address": $("#addressInput").val(),
					"userID": userData.idUserList,
					"permission": userData.permission
				},
				"success": function(data) {
					console.log(JSON.stringify(data))
					layer.close(index);
					iEvent.hotelPaging(data.data);
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("获取数据失败！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		//获取用户列表(酒店列表)
		getAllHotel: function() {
			var userData = JSON.parse(sessionStorage.getItem("user"));
			var index = layer.load(2);
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
					iEvent.hotelPaging(data.data);
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
							time: 300
						});
					} else {
						layer.msg("获取数据失败！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		//表格重载分页
		hotelPaging: function(hotelData) {
						console.log(JSON.stringify(hotelData))
			layui.use('table', function() {
				var table = layui.table;
				var hotelTabelId = table.render({
					id: "hotelTable",
					elem: '#hotelTable',
					height: 380,
					data: hotelData,
					page: {
						limit: 8,
						layout: ['count', 'prev', 'page', 'next', 'skip'],
						theme: 'zypage',
					},
					cols: [
						[ //表头
							{
								field: 'hotelName',
								title: '酒店名称',
								width: 205,
							}, {
								field: 'hotelId',
								title: '编号',
								width: 80
							}, {
								field: 'address',
								title: '地址',
								width: 80,
							}, {
								field: 'status',
								title: '状态',
								width: 80,
							}, {
								field: 'createTime',
								title: '添加时间',
								width: 75,
							}, {
								field: 'account',
								title: '管理员',
								width: 127,
							},
							{
								field: 'button',
								title: '操作',
								width: 150,
								toolbar: "#hotelTableBar",
							}
						]
					],
					done: function(res, curr, count) {
//						$("body").on("click", "tr td:eq(5)", function(e) {
//							$this = $(this);
//							layer.open({
//								type: 4,
//								content: ["昵称："+hotelData.userName
//								+"</br>权限："+hotelData.permission
//								+"</br>联系方式："+hotelData.userPhone, e.target], //数组第二项即吸附元素选择器或者DOM
//								shade: 0,
//								tips:4,
//							});
//						}).on("mouseout", function(e) {
//							layer.closeAll('tips');
//						});
					}
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
							area: ["700px", "460px"],
							content: ["modifyHotel.html"],
							btn: btnArray,
							btnAlign: "c",
							success: function(layero, index) {
								var $body = layer.getChildFrame("body", index);
								$body.find("#address").val(data.address);
								$body.find("#createTime").val(data.createTime);
								$body.find("#hotelId").val(data.hotelId);
								$body.find("#hotelManager").val(data.hotelManager);
								$body.find("#hotelName").val(data.hotelName);
								$body.find("#defaultName").val(data.hotelName);
								$body.find("#hotelPhone").val(data.hotelPhone);
								$body.find("#idHotelList").val(data.idHotelList);
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
		/*
		 * 删除酒店
		 * 
		 */
		delHotel: function(hotel) {
			layer.msg('删除中', {
				icon: 16,
				shade: 0.01,
				time: false,
			});
			ZY.ajax({
				"url": "hotel/delHotel",
				"type": "POST",
				"data": {
					"hotelID": hotel.idHotelList
				},
				"success": function(data) {
					iEvent.getAllHotel();
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
	};
}());