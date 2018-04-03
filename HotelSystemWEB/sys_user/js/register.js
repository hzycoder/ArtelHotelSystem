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
	var iView = {
		init: function() {
			$("#accountInput").on("input propertychange keyup blur change",function(){
				console.log($("#accountInput-error").attr("class"))
				if ($("#accountInput-error").attr("class")!="error") {
					$("#accountInput-error").remove()
				}
			});
		},
	};
	var iEvent = {
		init: function() {
			iEvent.verifyForm();
		},
		verifyForm: function() {
			var validator = $("#registerForm").validate({
				rules: {
					account: {
						specialCharFilter: true,
						required: true,
						minlength: 6,
						maxlength: 32,
						remote: { //ajax判断账户是否唯一
							type: "POST",
							url: CONFIG.URL + "/" + "common/verifyAccountUnique",
							data: {
								account: function() {
									return $("#accountInput").val();
								}
							}
						},
					},
					userName: {
						specialCharFilter: true,
						required: true,
						minlength: 6,
						maxlength: 32,
					},
					password: {
						specialCharFilter: true,
						required: true,
						minlength: 6,
						maxlength: 16,
					},
					rePassword: {
						equalTo: "#passwordInput",
					},
				},
				messages: {
					account: {
						remote: "该账户已被占用",
					}
				},
				onfocusout: function(element) { //设置onblur验证表单
					$(element).valid();
				},
				submitHandler: function(form) {
					console.log("提交" + form);
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
		verfifyAccount: function(accouont) {
			ZY.ajax({
				"url": "common/verifyAccountUnique",
				"type": "POST",
				"contentType": "application/json;charset=UTF-8",
				"data": {
					"accouont": accouont,
				},
				"success": function(data) {
					console.log(JSON.stringify(data));
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg(data.error, { //显示失败信息
							icon: 2,
						});
					}
				},
			});
		}
	};
}());