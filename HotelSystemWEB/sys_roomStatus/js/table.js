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
			$(".elementContent").on("mouseover",function(e){
//				alert(this+"\n"+e.target);
//				alert(e.target.className.indexOf('roomElement'))
					iEvent.roomElementTips(e.target);
			}).on("mouseout",function(e){
				layer.closeAll('tips');
			});
			$("#switchTemplate").on("click",function(){
				iEvent.switchTemplate();
			});
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
//				iEvent.initPage();//可以这样写
			});
		},
		//初始化表格
		initPage: function() {
			paging.init({
				openWait: true,
				url: '../datas/laytpl_laypage_data.json?v=' + new Date().getTime(), //地址
				elem: '#content', //内容容器
				params: { //发送到服务端的参数
				},
				type: 'GET',
				tempElem: '#tpl', //模块容器
				pageConfig: { //分页参数配置
					elem: '#paged', //分页容器
					pageSize: 3 //分页大小
				},
				success: function() { //渲染成功的回调
					//alert('渲染成功');
				},
				fail: function(msg) { //获取数据失败的回调
					//alert('获取数据失败')
				},
				complate: function() { //完成的回调
					//alert('处理完成');
					//重新渲染复选框
					form.render('checkbox');
					form.on('checkbox(allselector)', function(data) {
						var elem = data.elem;
						$('#content').children('tr').each(function() {
							var $that = $(this);
							//全选或反选
							$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
							form.render('checkbox');
						});
					});
					//绑定所有编辑按钮事件						
					$('#content').children('tr').each(function() {
						var $that = $(this);
						$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
							layer.msg($(this).data('name'));
						});
					});
				},
			});
		},
		roomElementTips: function(target) {
//			target.stopPropagation();
//			alert(target)
			layer.open({
			  type: 4,
			  content: ['当前时间：'+new Date()+'</br>地址：广州天河区</br>编号：#1236565</br>数据1：***</br>数据2：***', target], //数组第二项即吸附元素选择器或者DOM
			  shade: 0,
			});  
		},
		switchTemplate:function(){
//			alert($('#switchTemplate').text());
			if($('#switchTemplate').text().indexOf("以中继形式显示")!=-1){
				$('#switchTemplate').text("以房间形式显示");
				$('#roomTemplate').hide();
				$('#repeaterTemplate').show();
			}else{
				$('#switchTemplate').text("以中继形式显示");
				$('#repeaterTemplate').hide();
				$('#roomTemplate').show();
			}
			
		},
	};

	window.onload = function() {
		iView.init();
		iEvent.init();
	};
}());