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
			$("#accountInput").on("input propertychange keyup blur change", function() {
				if($("#accountInput-error").attr("class") != "error") {
					$("#accountInput-error").remove()
				}
			});
			//重置按钮 重置检测按钮
			$("#resetBtn").on("click", function() {
				$("#checkBtn").attr("class", "layui-btn");
				$("#checkIcon").attr("class", "fa fa-refresh");
				$("#checkFont").text("检测账号");
			});
			$("#checkBtn").on("click", function() {
				iEvent.checkAccount();
			});
			$("#accountInput").bind("input propertychange", function() {
				$("#checkBtn").attr("class", "layui-btn");
				$("#checkIcon").attr("class", "fa fa-refresh");
				$("#checkFont").text("检测账号");
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
						//						remote: { //ajax判断账户是否唯一
						//							type: "POST",
						//							url: CONFIG.URL + "/" + "common/verifyAccountUnique",
						//							data: {
						//								account: function() {
						//									return $("#accountInput").val();
						//								}
						//							}
						//						},
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
						required: true,
					},
				},
				messages: {
				},
				//				onfocusout: function(element) { //设置onblur验证表单
				//					$(element).valid();
				//				},
				submitHandler: function(form) {
					if($("#checkFont").text() == "检测账号") {
						layer.msg("请先检测账号使用情况", {
							icon: 2,
							shade: 0.01,
							time: 2000,
						});
						return;
					}
					if($("#checkFont").text() == "检测中...") {
						layer.msg("请等待账号检测", {
							icon: 2,
							shade: 0.01,
							time: 2000,
						});
						return;
					}
					if($("#checkFont").text() == "该账号已被使用") {
						layer.msg("当前账号已被使用", {
							icon: 2,
							shade: 0.01,
							time: 3000,
						});
						$("#accountInput").focus();
						return;
					}
					var registerJson = {
						"account": form["account"].value,
						"userName": form["userName"].value,
						"password": hex_md5(form["password"].value),
						"creator": JSON.parse(sessionStorage.getItem("user")).idUserList,
					}
					iEvent.register(registerJson);
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
		register: function(data) {
			var index = layer.msg("提交申请中，请稍等...", {
				icon: 16,
				shade: 0.1,
			});
			ZY.ajax({
				"url": "user/register",
				"type": "POST",
				"contentType": "application/json;charset=UTF-8",
				"data": JSON.stringify(data),
				"success": function(data) {
					$("#resetBtn").click();
					layer.close(index);
					if(data && data.success) { //如果登录成功
						layer.msg(data.msg, { //显示成功信息
							icon: 1,
						});
					} else {
						layer.msg("注册失败", { //显示失败信息
							icon: 2,
						});
					}
				},
			});
			
		},
		checkAccount: function() {
			$("#checkIcon").attr("class", "fa fa-refresh fa-spin");
			$("#checkFont").text("检测中...");
			ZY.ajax({
				"url": "common/verifyAccountUnique",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"data": {
					"account": $("#accountInput").val(),
				},
				"success": function(data) {
					if(data) {
						$("#checkIcon").attr("class", "fa fa-check");
						$("#checkFont").text("该账号可以使用");
					} else {
						$("#checkIcon").attr("class", "fa fa-close");
						$("#checkFont").text("该账号已被使用");
						$("#checkBtn").attr("class", "layui-btn layui-bg-red");
					}
				},
			});
		},
	};
}());