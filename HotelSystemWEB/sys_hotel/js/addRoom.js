(function() {
	//全局变量定义：
	var form;
	var btnArray = new Array();
	btnArray.push('<span style="color: #ffffff;transition:color 1s linear;">确定无误</span>');
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
			$(".layui-form-item:not(.layui-elem-quote .layui-form-item)").on("click", function() {
				if($("#hotelSelect").val() == "") {
					layer.msg("请先选择酒店", {
						icon: 2,
						time: 2000,
						anim: 6
					});
					return;
				}
			});
			$("#roomNumInput").on("input propertychange", function() {
				iEvent.verifyRoomNum()
			});
			$("#batchAdd").on("click", function() {
				$this = $(this);
				iEvent.swtichBatchAdd($this);
			});
			form.on('select(hotelSelect)', function(data) {
				if(data.value == "") {
					$("textarea").attr("disabled", "disabled");
					$("input").attr("disabled", "disabled");
					$(".error:odd").hide();
					return;
				}
				$("textarea").removeAttr("disabled", "disabled");
				$("input").removeAttr("disabled", "disabled");
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
				var hotelNum = (data.value).split(":"); //赋值hotelNum
				$("#hotelIDInput").val(hotelNum[0])
				$("#hotelNum").val(hotelNum[1])
				$("#hotelIDInput1").val(hotelNum[0])
				$("#hotelNum1").val(hotelNum[1])
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.verifyForm();
			iEvent.verifyForm1();
		},
		verifyRoomNum: function() {
			ZY.ajax({
				"url": "hotel/verifyRoomNum",
				"type": "POST",
				"data": {
					"roomNum": $("#roomNumInput").val(),
					"hotelId": $("#hotelIDInput").val()
				},
				"success": function(data) {
					console.log("验证：" + JSON.stringify(data));
					if(!data) {
						$("#warning").show();
					} else {
						$("#warning").hide();
					}
				}
			});
		},
		//验证表单
		verifyForm: function() {
			var validator = $("#addRoomForm").validate({
				rules: {
					roomNum: {
						required: true,
						specialCharFilter: true,
					},
					floor: {
						required: true,
						specialCharFilter: true,
					},
				},
				messages: {},
				onfocusout: function(element) { //设置onblur验证表单
					$(element).valid();
				},
				onsubmit: function(element) {
					$(element).valid();
				},
				submitHandler: function(form) {
					console.log("提交");
					if($("#hotelSelect").val() == "") {
						layer.msg("请先选择酒店", {
							icon: 2,
							time: 2000,
							anim: 6
						});
						return;
					}
					var roomDataJSON = {
						"hotelId": form["hotelID"].value,
						"hotelNum": form["hotelNum"].value,
						"roomNum": form["roomNum"].value,
						"floor": form["floor"].value,
					}
					iEvent.addRoom(roomDataJSON);
				},
				highlight: function(element, errorClass) { //设置错误提示样式
					$(element).addClass(errorClass);
				},
				unhighlight: function(element, errorClass) {
					$(element).removeClass(errorClass);
				},
				errorPlacement: function(error, element) {
					error.appendTo(element.parent());
				},
			});
			//添加特殊字符验证
			$.validator.addMethod("specialCharFilter", function(value, element) {
				var pattern = new RegExp("[`!#$^*=|':;,.<>/?~！@#￥……*——|‘；：”“'。，、？%+ 　\"\\\\]");
				var specialStr = "";
				for(var i = 0; i < value.length; i++) {
					specialStr += value.substr(i, 1).replace(pattern, '');
				}
				if(specialStr == value) {
					return true;
				}
				return false;
			}, "账号含有非法字符");
		},
		//验证批量增加表单
		verifyForm1: function() {
			var validator = $("#batchAddRoomForm").validate({
				rules: {
					roomNumList: {
						required: true,
						specialCharFilter: true,
					},
					floor: {
						required: true,
						specialCharFilter: true,
					},
				},
				messages: {},
				onfocusout: function(element) { //设置onblur验证表单
					$(element).valid();
				},
				onsubmit: function(element) {
					$(element).valid();
				},
				submitHandler: function(form) {
					console.log("提交");
					if($("#hotelSelect").val() == "") {
						layer.msg("请先选择酒店", {
							icon: 2,
							time: 2000,
							anim: 6
						});
						return;
					}
					var roomDataJSON = {
						"hotelId": form["hotelID1"].value,
						"hotelNum": form["hotelNum1"].value,
						"roomNumList": form["roomNumList"].value,
						"floor": form["floor"].value,
					}
					iEvent.addRoom(roomDataJSON);
				},
				highlight: function(element, errorClass) { //设置错误提示样式
					$(element).addClass(errorClass);
				},
				unhighlight: function(element, errorClass) {
					$(element).removeClass(errorClass);
				},
				errorPlacement: function(error, element) {
					error.appendTo(element.parent());
				},
			});
			//添加特殊字符验证
			$.validator.addMethod("specialCharFilter", function(value, element) {
				var pattern = new RegExp("[`!#$^*=|':;.<>/?~！@#￥……*——|‘；：”“'。、？%+ 　\"\\\\]");
				var specialStr = "";
				for(var i = 0; i < value.length; i++) {
					specialStr += value.substr(i, 1).replace(pattern, '');
				}
				if(specialStr == value) {
					return true;
				}
				return false;
			}, "账号含有非法字符");
		},
		//添加酒店
		addRoom: function(roomData) {
			//单项增加
			if($("#batchAdd").text() == "开启批量增加") {
				roomData.roomNumList = null;
				iEvent.addRoomAjax(roomData, "addRoom");
			} else {
				//批量增加
				roomData.roomNumList = iEvent.verifyRoomNumData($("#roomNumTextarea").val());
				roomData.roomNum = null;
				var index = layer.open({
					id: "roomDatalListShow",
					type: 1,
					title: "请确认以下信息",
					skin: "layui-layer-open",
					shadeClose: true,
					btn: btnArray,
					content: "",
					fixed: true,
					area: ["400px", "300px"],
					success: function() {
						var html = '<div class="floorElement">即将在楼层' + roomData.floor + '添加以下房间</div>';
						var $html = $(html);
						$("#roomDatalListShow").append($html);
						$.each(roomData.roomNumList, function(index, item) {
							var html = '<div class="roomElement">' +
								'<span class="roomElementNum">' + item + '</span>' +
								'</div>';
							var $html = $(html);
							$("#roomDatalListShow").append($html);
						});
					},
					yes: function() {
						iEvent.addRoomAjax(roomData, "batchAddRoom");
						layer.close(index);
					},
				});
			}
		},
		addRoomAjax: function(roomData, type) {
			console.log(JSON.stringify(roomData));
			var index = layer.msg('添加房间中...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "hotel/" + type,
				"type": "POST",
				"data": JSON.stringify(roomData),
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
					$("#roomNumInput").val("");
					$("#floorInput").val("");
					$("#roomNumTextarea").val("");
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value=' + hotelData[i].idHotelList + ':' + hotelData[i].hotelId + '>酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
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
		//批量增加
		swtichBatchAdd: function($this) {
			if($this.text() == "开启批量增加") {
				$this.css("background-color", "#FF5722")
				$this.text("关闭批量增加");
				$("#addRoomForm").hide();
				$("#batchAddRoomForm").show();
			} else {
				$this.css("background-color", "#009688");
				$this.text("开启批量增加");
				$("#addRoomForm").show();
				$("#batchAddRoomForm").hide();
			}
		},
		//处理批量增加的房间号数据
		verifyRoomNumData: function(roomNumData) {
			var tempData = roomNumData.split(/[，,]/); //以逗号分割
			if(tempData[tempData.length - 1] == "") {
				tempData.splice(tempData.length - 1, 1);
			}
			return tempData;
		},
	};
}());