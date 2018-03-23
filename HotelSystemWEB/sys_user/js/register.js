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
		init: function() {
			iView.verifyForm();
		},
		verifyForm: function() {
			var validator = $("#registerForm").validate({
				rules: {
					account: {
						required: true,
						minlength:5,
						maxlength:32,
					},
					userName: {
						required: true,
					},
					password: {
						required: true,
					},
					rePassword: {
						required: true,
					},
					userPhone: {
						required: true,
					},
					position: {
						required: true,
					},
				},
				messages: {

				},
				onfocusout: function(){
					
				},
				
				//				errorPlacement:function(error,element){
				//					error.appendTo(element.parent());
				//				},
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
	};
}());