(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: '../js/'
	});
	var paging,
		layerTips,
		layer,
		form;
	layui.use(["paging", "form"], function() {
		paging = layui.paging();
		layerTips = parent.layer === undefined ? layui.layer : parent.layer; //获取父窗口的layer对象
		layer = layui.layer; //获取当前窗口的layer对象
		form = layui.form();
		layer.ready(function() {
			iEvent.init();
			iView.init();
		});
	});
	//view方法：
	var iView = {
		init: function() {
			iEvent.getAllHotel();
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				$("#main").empty();
				iEvent.getAgent(data.value);
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
			});
			//监听中继选择下拉框
			form.on('select(agentSelect)', function(data) {
				$("#main").empty();
				var html = '<div id="repeaters">' +
					'<div class="repeater">' +
					'<span class="agentNum">' + "当前中继" + '</span>' +
					'</div>' +
					'</div>' +
					'<div class="connect">' +
					'<div id="toRepeaterLine"></div>' +
					'<div id="mainLine"></div>' +
					'<div id="soltList">' +
					'</div>' +
					'</div>';
				var $html = $(html);
				$("#main").append($html);
				iEvent.getSolt(data.value);
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
			});
			$(".elementContent").on("mouseover", function(e) {
				iEvent.roomElementTips(e.target);
			}).on("mouseout", function(e) {
				layer.closeAll('tips');
			});
			$("#switchTemplate").on("click", function() {
				iEvent.switchTemplate();
			});
		},
		initLine: function() {

		}
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//获取用户列表(酒店列表)
		getAllHotel: function() {
			layer.msg('加载数据中...', {
				icon: 16,
				shade: 0.01
			});
			ZYAjax.ajax({
				"url": "HotelSystemServer/getHotels",
				"type": "GET",
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					console.log("获取的酒店" + JSON.stringify(data));
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value="' + hotelData[i].idHotelList + '">酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select');
		},
		//渲染中继select
		initAgentSelect: function(deviceData) {
			$("#agentSelect").empty();
			$("#agentSelect").append('<option value=""></option>');
			if(deviceData.length == 0) {
				console.log("=====0")
				$("#agentSelect").append('<option disabled>该酒店暂无设备，请先添加设备</option>');
				form.render('select');
				return;
			}
			$(deviceData).each(function(i) {
				$("#agentSelect").append('<option value="' + deviceData[i].idAgentList + '">设备地址：' + deviceData[i].macAddress + '</option>')
			});
			form.render('select');
		},
		//获取中继下的卡槽设备
		getSolt: function(agentId) {
			layer.msg('加载卡槽设备中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZYAjax.ajax({
				"url": "HotelSystemServer/getSoltByAgentId",
				"type": "GET",
				"data": {
					"agentId": agentId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					if (data.data.length == 0) {
						var zero = '<span id="zero">当前中继下无设备</span>'
						var $zero = $(zero);
						$("#soltList").append($zero);
						return;
					}
					$.each(data.data, function(index, item) {
						var soltHtml = '<div class="solt" id="' + item.idSoltList + '">' +
							'<div class="upLine"></div>' +
							'<div class="soltHead">' +
							'<span class="roomNum">' +
							item.roomId +
							'</span>' +
							'</div>' +
							'<div class="tridentLine">' +
							'<div class="downLine"></div>' +
							'<div class="threeLine">' +
							'<div class="innerLine"></div>' +
							'</div>' +
							'</div>' +
							'<div class="soltOperations">' +
							'<div class="lockerBtn">' +
							'<span> 门锁</span>' +
							'</div>' +
							'<div class="remoteBtn">' +
							'<span>遥控</span>' +
							'</div>' +
							'<div class="infraredBtn">' +
							'<span>红外</span>' +
							'</div>' +
							'</div>' +
							'</div>';
						var $soltHtml = $(soltHtml);
						$("#soltList").append($soltHtml);
					});
				}
			});
		},
		//获取指定酒店的中继
		getAgent: function(hotelId) {
			layer.msg('加载酒店中继中,请稍等...', {
				icon: 16,
				shade: 0.01
			});
			ZYAjax.ajax({
				"url": "HotelSystemServer/getAgentByHotelId",
				"type": "GET",
				"data": {
					"hotelId": hotelId
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					iEvent.initAgentSelect(data.data);
					console.log("获取的中继" + JSON.stringify(data));
				}
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
			layer.open({
				type: 4,
				content: ['当前时间：' + new Date() + '</br>地址：广州天河区</br>编号：#1236565</br>数据1：***</br>数据2：***', target], //数组第二项即吸附元素选择器或者DOM
				shade: 0,
			});
		},
		switchTemplate: function() {
			if($('#switchTemplate').text().indexOf("以中继形式显示") != -1) {
				$('#switchTemplate').text("以房间形式显示");
				$('#roomTemplate').hide();
				$('#repeaterTemplate').show();
			} else {
				$('#switchTemplate').text("以中继形式显示");
				$('#repeaterTemplate').hide();
				$('#roomTemplate').show();
			}

		},
	};
}());