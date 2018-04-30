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
		init: function() {},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.verifyForm()
		},
		verifyForm: function() {
			var validator = $("#modifyPassForm").validate({
				rules: {
					obsoletePass: {
						required: true,
						minlength: 6,
						maxlength: 16,
					},
					newPass: {
						required: true,
						minlength: 6,
						maxlength: 16,
					},
					newPass2: {
						equalTo: "#newPassInput",
						required: true,
						minlength: 6,
						maxlength: 16,
					},
				},
				messages: {
					obsoletePass: {
						required: "请输入初始密码",
						minlength: "密码长度不少于6位",
						maxlength: "密码长度不多于16位",
					},
					newPass: {
						minlength: "密码长度不于6位",
						maxlength: "密码长度不多于16位",
					},
					newPass2: {
						minlength: "密码长度不少于6位",
						maxlength: "密码长度不多于16位",
					},
				},
				submitHandler: function(form) {
					console.log(8888)
					iEvent.modifyPass();
				},
				highlight: function(element, errorClass) { //设置错误提示样式
					$(element).addClass(errorClass);
					$(element.form).find("label[for=" + element.id + "]").addClass(errorClass);
				},
				unhighlight: function(element, errorClass) {
					$(element).removeClass(errorClass);
					$(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
				},
				errorPlacement: function(error, element) {
					error.appendTo(element.parent());
				},
			});
			//添加特殊字符验证
			$.validator.addMethod("specialCharFilter", function(value, element) {
				var pattern = new RegExp("[`~!@#$^&*()=|{}':;,.<>/?~！@#￥……&*（）——|【】‘；：”“'。，、？%+ 　\"\\\\]");
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
		modifyPass: function() {
			var index = layer.load(1, {
				shade: 0.01,
			})
			ZY.ajax({
				"url": "user/modifyPass",
				"type": "GET",
				"data": {
					"id": JSON.parse(sessionStorage.getItem("user")).idUserList,
					"newPass": hex_md5($("#newPassInput").val()),
					"obsoletePass": hex_md5($("#obsoletePassInput").val()),
				},
				"success": function(data) {
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
			});
		},
	};
}());