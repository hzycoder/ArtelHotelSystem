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
			iEvent.getAllUser();
			//			form.on('submit(addHotel)', function(data) {
			//				console.log(JSON.stringify(data.field));
			//				iEvent.addHotel(data.field);
			//				return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
			//			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.verifyForm();
		},
		//验证表单
		verifyForm: function() {
			var validator = $("#addHotelForm").validate({
				rules: {
					hotelName: {
						required: true,
						specialCharFilter: true,
						minlength: 6,
						maxlength: 32,
						remote: { //ajax判断账户是否唯一
							type: "POST",
							url: CONFIG.URL + "/" + "hotel/verifyHotelName",
							data: {
								hotelId: function() {
									return $("#hotelNameInput").val();
								}
							}
						},
					},
					hotelId: {
						required: true,
						minlength: 6,
						maxlength: 32,
					},
					address: {
						maxlength: 180,
					},
				},
				messages: {
					hotelName: {
						remote: "该酒店名已存在",
					},
				},
				onfocusout: function(element) { //设置onblur验证表单
					$(element).valid();
				},
				onsubmit:function(element){
					$(element).valid();
				},
				submitHandler: function(form) {
					if(form["accountSelect"].value == -1) {
						layer.msg("请选择酒店所属账户", {
							icon: 2,
							time: 2000,
							anim: 6
						});
						$("#accountSelect").focus();
						return;
					}
					if(form["status"].value == -1) {
						layer.msg("请选择酒店使用情况", {
							icon: 2,
							time: 2000,
							anim: 6
						});
						return;
					}
					var hotelJSON = {
						"hotelName": form["hotelName"].value,
						"address": form["address"].value,
						"status": form["status"].value,
						"hotelPhone": form["hotelPhone"].value,
						"hotelManager": form["hotelManager"].value,
					}
					iEvent.addHotel(hotelJSON);
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
		//添加酒店
		addHotel: function(hotelData) {
			var index = layer.msg('添加酒店中...', {
				icon: 16,
				shade: 0.1,
				time: 2000
			});
			ZY.ajax({
				"url": "hotel/addHotel",
				"type": "POST",
				"data": JSON.stringify(hotelData),
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
//					$('#resetBtn').click(); //重置表单
					iEvent.getAllUser();
					if(data && data.success) { //如果登录成功
						setTimeout(function(){
							window.location.reload();
						},3000)
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("添加失败！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
		//渲染
		initUserSelect: function(userData) {
			$("#accountSelect").empty();
			$("#accountSelect").append('<option value="-1"></option>');
			$(userData).each(function(i) {
				$("#accountSelect").append('<option value="' + userData[i].idUserList + '">' + userData[i].account + '</option>')
			});
			form.render('select')
		},
		//获取没有绑定酒店的用户
		getAllUser: function() {
			ZY.ajax({
				"url": "user/getUnbindedUser",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					if(data && data.success) { //如果登录成功
						iEvent.initUserSelect(data.data);
					} else {
						layer.msg("系统内部错误！", { //显示失败信息
							icon: 2,
						});
					}
				}
			});
		},
	};
}());