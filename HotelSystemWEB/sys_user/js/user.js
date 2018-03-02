(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: '../js/'
	});
	var paging,
		layerTips,
		layer,
		form,
		table;
	//view方法：
	var iView = {
		init: function() {
			iView.renderTableSelected();
			//			$("#research").on("click", function() {
			//				iEvent.getAllUser();
			//			});
		},
		renderTableSelected: function() {
			//			table.on
		}
	};
	//event方法：
	var iEvent = {
		init: function() {
			layui.use(["paging", "form", ], function() {
				paging = layui.paging();
				layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
				layer = layui.layer; //获取当前窗口的layer对象
				form = layui.form;
			});
			iEvent.userPaging(); //分页初始化
		},
		/*
		 * 获取用户列表(酒店列表)
		 */
		getAllUser: function() {
			var index = layer.load(2);
			$.ajax({
				type: "GET",
				url: CONFIG.URL + 'HotelSystemServer/getHotels',
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				async: false,
				success: function(data) {
					layer.close(index);
					userData = JSON.stringify(data);
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("获取数据失败！", { //显示失败信息
							icon: 2,
						});
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					layer.close(index);
					console.log(JSON.stringify(jqXHR));
				}
			});
			return userData;
		},
		/*
		 * 表格重载分页
		 */
		userPaging: function() {
			layui.use('table', function() {
				var table = layui.table;
				table.render({
					id: "userTable",
					elem: '#userTable',
					height: 380,
					data: JSON.parse(iEvent.getAllUser()).data,
					page: {
						limit: 5,
						layout: ['count', 'prev', 'page', 'next', 'skip'],
						theme: 'zypage',
					},
					cols: [
						[ //表头
							{
								field: 'hotelName',
								title: '客户名称',
								width: 80,
								sort: true
							}, {
								field: 'hotelId',
								title: '编号',
								width: 80
							}, {
								field: 'address',
								title: '地址',
								width: 80,
								sort: true
							}, {
								field: 'repeatersCount',
								title: '中继',
								width: 135,
								sort: true
							}, {
								field: 'devicesCount',
								title: '设备',
								width: 135,
								sort: true
							}, {
								field: 'status',
								title: '状态',
								width: 80,
								sort: true
							}, {
								field: 'createTime',
								title: '时间',
								width: 135,
								sort: true
							}, {
								field: '',
								title: '操作',
								width: 135,
								toolbar: "#userTableBar",
							}
							//			      ,{field: 'hotelManager', title: '职业', width: 80}
						]
					]
				});
				//监听工具条操作
				table.on('tool(userTableFilter)', function(obj) {
					var data = obj.data;
					layer.confirm('确定要删除编号为：' + obj.data.hotelId + "的酒店吗？", {
						btn: ['确定', '取消'],
						yes: function(index, layero) {
							//确定
							iEvent.delUser(obj.data);
						},
						btn2: function(index, layero) {
							//取消
						}
					});
				});
			});
		},
		/*
		 * 删除删除酒店
		 */
		delUser: function(hotel) {
			var index = layer.msg('删除中',{
				icon: 16,
				shade:0.01,
				time: false,
			});
			var jsonData  = {	
				"hotelID" : hotel.idHotelList
			};
			console.log(hotel.idHotelList);
			$.ajax({
				type: "POST",
				url: CONFIG.URL + 'HotelSystemServer/delHotel',
//				contentType: "application/json;charset=UTF-8",	//单个参数 不需要这个
				dataType: "json",
				data:{	
					"hotelID" : hotel.idHotelList
				},
				success: function(data) {
					layer.close(index);
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg(data.msg, { //显示失败信息
							icon: 2,
						});
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					layer.close(index);
					console.log(JSON.stringify(jqXHR));
				}
			});
		},
		/*
		 * 使用laypage分页 
		 */
		laypage: function(data) {
			layui.config({
				base: 'js/'
			}).use(['paging', 'code'], function() {
				layui.code();
				var $ = layui.jquery,
					paging = layui.paging();
				paging.init({
					url: CONFIG.URL + 'HotelSystemServer/getHotels', //地址
					type: 'GET',
					elem: '#content', //内容容器
					params: { //发送到服务端的参数

					},
					tempElem: '#conTemp', //模块容器
					pageConfig: { //分页参数配置
						elem: '#paged', //分页容器
						pageSize: 3 //分页大小
					},
					success: function() { //渲染成功的回调
						//alert('渲染成功');
					},
					fail: function(msg) { //获取数据失败的回调
						//alert('获取数据失败')
					},
					complate: function() { //完成的回调
						//alert('处理完成');
					},
				});
			});
		},

	};
	window.onload = function() {
		iView.init();
		iEvent.init();
	};
}());