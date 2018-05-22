(function() {
	//全局变量定义：
	var layer,
		form,
		table;
	layui.use(["form", "table"], function() {
		layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
		layer = layui.layer; //获取当前窗口的layer对象
		form = layui.form;
		table = layui.table;
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
			iEvent.getAllUser()
		},
		getAllUser: function() {
			ZY.ajax({
				"url": "user/getAllUser",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log( JSON.stringify(data));
					$.each(data.data, function(index, item) {
						item.createTime = iEvent.switchUnixTime(item.createTime);
					});
					iEvent.userPaging(data.data);
					if(data && data.success) { //如果登录成功
					} else {
						layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		userPaging: function(userData) {
			layui.use('table', function() {
				var table = layui.table;
				var userTableID = table.render({
					id: "userTable",
					elem: '#userTable',
					height: 380,
					data: userData,
					page: {
						limit: 5,
						layout: ['count', 'prev', 'page', 'next', 'skip'],
						theme: 'zypage',
					},
					cols: [
						[ //表头
							{
								field: 'account',
								title: '账户',
								width: 120,
							}, {
								field: 'userName',
								title: '昵称',
								width: 120
							}, {
								field: 'createTime',
								title: '申请时间',
								width: 140,
							}, {
								field: 'permission',
								title: '权限',
								width: 60,
							}, {
								field: 'position',
								title: '地区',
								width: 135,
							}, {
								field: 'userPhone',
								title: '联系电话',
								width: 150,
							}, {
								field: '',
								title: '操作',
								width: 150,
								toolbar: "#userTableBar",
							}
						]
					]
				});
				//监听工具条操作
				table.on('tool(userTableFilter)', function(obj) {
					var layEvent = obj.event; //获取当前点击的事件
					var data = obj.data; //获取当行数据
					console.log("======" + JSON.stringify(data));
					if(layEvent == "del") {
						layer.confirm('', {
							title: "确定要删除" + data.account + "用户吗？",
							content: "删除用户后，用户的所有数据都会删除(包括酒店等)",
							btn: ['确定', '取消'],
							yes: function(index, layero) { //确定
								layer.msg("暂不支持删除", {
									icon: 2,
									time: 2000,
									anim: 6
								});
							},
							btn2: function(index, layero) {
								//取消
							}
						});
					} else if(layEvent == "mod") {
						layer.msg("暂不支持修改", {
							icon: 2,
							time: 2000,
							anim: 6
						});
					}
				});
			});
		},
		switchUnixTime: function(unixTime) {
			if(typeof unixTime != "number") {
				return unixTime;
			}
			var newDate = new Date();
			newDate.setTime(unixTime);
			return newDate.Format("yyyy-MM-dd hh:mm:ss");
		},
	};
}());