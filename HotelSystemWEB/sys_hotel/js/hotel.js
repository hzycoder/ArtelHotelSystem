(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: '../js/'
	});
	var paging,
		layerTips,
		layer,
		form;

	//view方法：
	var iView = {
		init: function() {
			iEvent.getHotelList();
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			layui.use(["paging", "form"], function() {
				paging = layui.paging();
				layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
				layer = layui.layer; //获取当前窗口的layer对象
				form = layui.form();
				//				iEvent.initPage(); //可以这样写
			});
		},
		//初始化表格
		//		initPage: function() {
		//			user = sessionStorage.getItem("user");
		//			paging.init({
		//				openWait: true,
		//				url: 'http://localhost:8080/HotelSystemServer/getHotels', //地址
		//				elem: '#content', //内容容器
		//				params:user,//发送到服务端的参数
		//				type: 'POST',
		//				tempElem: '#tpl', //模块容器
		//				pageConfig: { //分页参数配置
		//					elem: '#paged', //分页容器
		//					pageSize: 2 //分页大小
		//				},
		//				success: function() { //渲染成功的回调
		//					//alert('渲染成功');
		//				},
		//				fail: function(msg) { //获取数据失败的回调
		//					//alert('获取数据失败')
		//				},
		//				complate: function() { //完成的回调
		//					//alert('处理完成');
		//					//重新渲染复选框
		//					form.render('checkbox');
		//					form.on('checkbox(allselector)', function(data) {
		//						var elem = data.elem;
		//						$('#content').children('tr').each(function() {
		//							var $that = $(this);
		//							//全选或反选
		//							$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
		//							form.render('checkbox');
		//						});
		//					});
		//					//绑定所有编辑按钮事件						
		//					$('#content').children('tr').each(function() {
		//						var $that = $(this);
		//						$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
		//							layer.msg($(this).data('name'));
		//						});
		//					});
		//					console.log("")
		//				},
		//			});
		//		},
		getHotelList: function() {
			var user = JSON.parse(sessionStorage.getItem("user"));
			console.log(user.id);
			//			var jsonData = {
			//				"id" : user.id,
			//			};
			$.ajax({
				type: "POST",
				url: "http://localhost:8080/HotelSystemServer/getHotels",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				data: JSON.stringify(user),
				success: function(data) {
					console.log("======AjaxSUCCESS======");
					console.log("返回数据:" + JSON.stringify(data));
					if(data && data.success) {
						console.log("111" + data.result);
					} else {}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("======AjaxERROR======");
				}
			});
		},
	};
	window.onload = function() {
		iView.init();
		iEvent.init();
	};
}());