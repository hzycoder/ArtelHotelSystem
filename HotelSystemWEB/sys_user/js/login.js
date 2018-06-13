//使用匿名函数
(function() {
	//全局变量定义：
	var layer, form;
	var verifyCodeIsRight = 0;
	layui.use(["layer", "form"], function() {
		layer = layui.layer;
		form = layui.form();
		layer.ready(function() {
			iEvent.init();
			iView.init();
		});
	});
	//view方法：
	var iView = {
		init: function() {
			iView.generateVerifyCode();
			$("input").keypress(function(e) {
				if(e.which == 13) {
					$(".login").focus()
					iEvent.beforeLogin()
				}
			});
			$(".login").on("click", function() {
				iEvent.beforeLogin();
			});
		},
		generateVerifyCode: function() {
			var inp = document.getElementById('verifyCodeInput');
			var code = document.getElementById('verifyCode');

			var c = new KinerCode({
				len: 4, //需要产生的验证码长度
				//        chars: ["1+2","3+15","6*8","8/4","22-15"],//问题模式:指定产生验证码的词典，若不给或数组长度为0则试用默认字典
				chars: [
					1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
					'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
				], //经典模式:指定产生验证码的词典，若不给或数组长度为0则试用默认字典
				question: false, //若给定词典为算数题，则此项必须选择true,程序将自动计算出结果进行校验【若选择此项，则可不配置len属性】,若选择经典模式，必须选择false
				copy: false, //是否允许复制产生的验证码
				bgColor: "white", //背景颜色[与背景图任选其一设置]
				//				bgImg: "bg.jpg", //若选择背景图片，则背景颜色失效
				randomBg: false, //若选true则采用随机背景颜色，此时设置的bgImg和bgColor将失效
				inputArea: inp, //输入验证码的input对象绑定【 HTMLInputElement 】
				codeArea: code, //验证码放置的区域【HTMLDivElement 】
				click2refresh: true, //是否点击验证码刷新验证码
				false2refresh: true, //在填错验证码后是否刷新验证码
				validateEven: "blur", //触发验证的方法名，如click，blur等
				validateFn: function(result, code) { //验证回调函数
					if(result) {
						verifyCodeIsRight = 1;
						$("#verifyCodeSymbol").empty();
						$("#verifyCodeSymbol").append('<i style="color:greenyellow;font-size:22px;position:absolute;top:-1px;left:-8px" class="layui-icon" >&#xe618;</i>')
					} else {
						$("#verifyCodeSymbol").empty();
						$("#verifyCodeSymbol").append('<i style="color:darkred;font-size:22px;position:absolute;top:-2px;left:-4px" class="layui-icon" >&#x1006;</i>')
					}
				}
			});
		}
	};
	//event方法：
	var iEvent = {
		init: function() {
			//已登录
			if(sessionStorage.getItem("user") != null) {
				setTimeout(function() {
					window.location.href = "main.html";
				}, 300);
			}
		},
		//登录
		beforeLogin: function() {
			//判断验证码
			if(!verifyCodeIsRight) {
				layer.msg("请输入正确验证码", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			if(ZY.ZYtrim($("#userName").val()) == "" || ZY.ZYtrim($("#password").val()) == "") {
				layer.msg("用户名和密码不能为空", {
					icon: 2,
					time: 2000,
					anim: 6
				});
				return;
			}
			var index = layer.msg('登录中...', {
				icon: 16,
				shade: 0.03,
			});
			ZY.ajax({
				"url": "user/requestCode",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"data": {
					"account": $("#userName").val()
				},
				"success": function(data) {
					if(data && data.success) { //如果登录成功
						console.log("返回的Code" + JSON.stringify(data));
						iEvent.login(data.data)
					} else {
						layer.msg("登录失败", { //显示失败信息
							icon: 2,
						});
					}
				},
			});
		},
		login: function(code) {
			var pass = code + hex_md5($("#password").val());
			var loginJson = {
				"account": $("#userName").val(),
				"password": hex_md5(pass)
			}
			ZY.ajax({
				"url": "user/login",
				"type": "POST",
				"contentType": "application/json;charset=UTF-8",
				"data": JSON.stringify(loginJson),
				"success": function(data) {
					layer.closeAll('loading');
					if(data && data.success) { //如果登录成功
						layer.msg("登录成功", { //显示成功信息
							icon: 1,
						});
						//跳转页面
						sessionStorage.setItem("user", JSON.stringify(data.data));
						setTimeout(function() {
							window.location.href = "main.html";
						}, 300);
					} else {
						layer.msg(data.error, { //显示失败信息
							icon: 2,
						});
					}
				},
			});
		},
	};

}());