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
			$("#batchAdd").on("click", function() {
				$this = $(this);
				iEvent.swtichBatchAdd($this);
			});
			form.on('select(hotelSelect)', function(data) {
				if(data.value == "") {
					return;
				}
				console.log(data.elem); //得到select原始DOM对象
				console.log(data.value); //得到被选中的值
				console.log(data.othis); //得到美化后的DOM对象
				var hotelNum = (data.value).split(":"); //赋值hotelNum
				$("#hotelID").val(hotelNum[0])
				$("#hotelNum").val(hotelNum[1])
			});
			form.on('submit(addRoom)', function(data) {
				if($("#hotelSelect").val() == "") {
					layer.msg("请先选择酒店", {
						icon: 2,
						time: 2000,
						anim: 6
					});
					return;
				}
				iEvent.addRoom(data.field);
				return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
			});

		},
	};
	//event方法：
	var iEvent = {
		init: function() {},
		//添加酒店
		addRoom: function(roomData) {
			//单项增加
			if($("#batchAdd").text() == "开启批量增加") {
				if($("#roomNumInput").val() == "") {
					layer.msg("必填项不能为空", {
						icon: 2,
						time: 2000,
						anim: 6
					});
					$("#roomNumInput").focus();
					return;
				}
				roomData.roomNum = $("#roomNumInput").val();
				roomData.roomNumList = null;
				iEvent.addRoomAjax(roomData,"addRoom");
			} else {
				//批量增加
				if($("#roomNumTextarea").val() == "") {
					layer.msg("必填项不能为空", {
						icon: 2,
						time: 2000,
						anim: 6
					});
					$("#roomNumTextarea").focus();
					return;
				}
				roomData.roomNumList = iEvent.verifyRoomNumData($("#roomNumTextarea").val());
				roomData.roomNum = null;
				var index = layer.open({
					id:"roomDatalListShow",
					type:1,
					title:"请确认以下信息",
					skin:"layui-layer-open",
					shadeClose:true,
					btn: btnArray,
					content:"",
					fixed:true,
					area:["400px","300px"],
					success:function(){
						var html = '<div class="floorElement">即将在楼层'+roomData.floor+'添加以下房间</div>';
						var $html = $(html);
						$("#roomDatalListShow").append($html);
						$.each(roomData.roomNumList, function(index,item) {
							var html = '<div class="roomElement">'+
									'<span class="roomElementNum">'+item+'</span>'+
									'</div>';
									var $html = $(html);
									$("#roomDatalListShow").append($html);
						});
					},
					yes:function(){
						iEvent.addRoomAjax(roomData,"batchAddRoom");
						layer.close(index);
					},
				});
			}
		},
		addRoomAjax:function(roomData,type){
			console.log(JSON.stringify(roomData));
			var index = layer.msg('添加房间中...', {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "hotel/"+type,
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
					console.log("获取的酒店" + JSON.stringify(data));
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
				$("#roomNumInput").hide();
				$("#roomNumTextarea").show();
				$("#roomNumPrompt").show();
			} else {
				$this.css("background-color", "#009688");
				$this.text("开启批量增加");
				$("#roomNumInput").show();
				$("#roomNumTextarea").hide();
				$("#roomNumPrompt").hide();
			}
		},
		//处理批量增加的房间号数据
		verifyRoomNumData:function(roomNumData){
			var tempData = roomNumData.split(/[，,]/);//以逗号分割
			if (tempData[tempData.length-1] =="") {
				tempData.splice(tempData.length-1,1);
			}
			return tempData;
		},
	};
}());