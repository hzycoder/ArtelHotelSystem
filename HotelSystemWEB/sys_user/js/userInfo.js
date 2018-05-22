(function() {
	//全局变量定义：
	var layer,
		form;
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
			iEvent.getUser();
			$("#modifyBtn").on("click", function() {
				$this = $(this);
				if($this.text() == "开启修改") {
					$this.css("background-color", "#FF5722")
					$this.text("关闭修改");
					$(".zyDIsable").removeAttr("disabled");
					$(".zyDIsable").css({
						"cursor": "text",
						"color": "#FF5722"
					});
				} else {
					$this.css("background-color", "#009688");
					$this.text("开启修改");
					$(".zyDIsable").attr("disabled", 'disabled');
					$(".zyDIsable").css({
						"cursor": "text",
						"color": "rgb(84, 84, 84)"
					});
				}
			});
			$("#saveBtn").on("click", function() {
				iEvent.modifyUserInfo();
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//修改用户信息
		modifyUserInfo: function() {
			var userData = JSON.parse(sessionStorage.getItem("user"));
			var index = layer.load(2);
			ZY.ajax({
				"url": "user/modifyUserInfo",
				"type": "GET",
				"data": {
					"userName": $("#userName").val(),
					"userPhone": $("#userPhone").val(),
					"position": $("#position").val(),
					"userID": userData.idUserList
				},
				"success": function(data) {
					layer.close(index);
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg(data.error, { //显示失败信息
							icon: 2,
						});
					}
					$("#resetBtn").click()
				},
			});
		},
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
					$("#hotelCount").val(data.count);
					layer.close(index);
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
		switchUnixTime: function(unixTime) {
			if(typeof unixTime != "number") {
				return unixTime;
			}
			var newDate = new Date();
			newDate.setTime(unixTime);
			return newDate.Format("yyyy-MM-dd");
		},
		//获取当前用户信息
		getUser: function() {
			iEvent.getAllHotel();
			var userData = JSON.parse(sessionStorage.getItem("user"));
			var index = layer.load(2);
			ZY.ajax({
				"url": "user/getUserByID",
				"type": "GET",
				"data": {
					"userID": userData.idUserList
				},
				"success": function(data) {
					layer.close(index);
					iEvent.renderUserInfo(data.data)
				},
			});
		},
		//渲染表格
		renderUserInfo: function(userData) {
			$("#account").val(userData.account);
			$("#userName").val(userData.userName);
			$("#userPhone").val(userData.userPhone);
			$("#createTime").val(iEvent.switchUnixTime(userData.createTime));
			$("#position").val(userData.position);
			$("#permission").val(GlobalUserData.permission[userData.permission]);
		},
	};
}());