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
	layui.use(["paging", "form", ], function() {
		paging = layui.paging();
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
		init: function() {},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.getAllHotel(); //获取所有酒店并分页初始化
		},
		/*
		 * 获取用户列表(酒店列表)
		 */
		getAllHotel: function() {
			layer.load(2);
			ZYAjax.ajax({
				"url": "HotelSystemServer/getHotels",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log(JSON.stringify(data))
					layer.closeAll();
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
		/*
		 * 表格重载分页
		 * @param hotelData 酒店列表JSON
		 */
		hotelPaging: function(hotelData) {
			layui.use('table', function() {
				var table = layui.table;
				var hotelTabelId = table.render({
					id: "hotelTable",
					elem: '#hotelTable',
					height: 380,
					data: hotelData,
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
								field: 'repeaterCount',
								title: '中继',
								width: 135,
								sort: true
							}, {
								field: 'deviceCount',
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
								toolbar: "#hotelTableBar",
							}
							//			      ,{field: 'hotelManager', title: '职业', width: 80}
						]
					]
				});
				//监听工具条操作
				table.on('tool(hotelTableFilter)', function(obj) {
					console.log(JSON.stringify(obj.data))
					var data = obj.data;
					layer.confirm('确定要删除编号为：' + obj.data.hotelId + "的酒店吗？", {
						btn: ['确定', '取消'],
						yes: function(index, layero) {//确定
//							for (var i = 0; i<hotelData.length;i++) {
//								if (hotelData[i]["idHotelList"] == obj.data.idHotelList) {
//									delete hotelData[i];
//								} 
//							}
//							hotelTabelId.reload({
//								data: hotelData,
//								page: {
//									curr: 1 //重新从第 1 页开始
//								}
//							});
//							obj.del();
							iEvent.delHotel(obj.data);
						},
						btn2: function(index, layero) {
							//取消
						}
					});
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
			ZYAjax.ajax({
				"url": "HotelSystemServer/delHotel",
				"type": "POST",
				"data": {
					"hotelID": hotel.idHotelList
				},
				//				"contentType": "application/json;charset=UTF-8",//单个参数 不需要这个
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
}());