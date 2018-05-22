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
		init: function() {
			//监听酒店选择下拉框
			form.on('select(hotelSelect)', function(data) {
				$("#layui-elem-field").empty();
				if(data.value == "") {
					table.reload("roomTable", {
						data: null,
					})
					return;
				}
				iEvent.getStatus(data.value);
			});
			$.fn.tableExport.xlsx = {
				defaultClass: "xlsx",
				buttonContent: "导出Excel",
				mimeType: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				fileExtension: ".xlsx"
			};

			$.fn.tableExport.charset = "charset=UTF-8";

			/* default filename if "id" attribute is set and undefined */
			$.fn.tableExport.defaultFileName = "myDownload";

			/* default class to style buttons when not using bootstrap  */
			$.fn.tableExport.defaultButton = "layui-btn exportBtn";

			/* bootstrap classes used to style and position the export buttons */
			$.fn.tableExport.bootstrap = ["btn", "btn-default", "btn-toolbar"];

			/* row delimeter used in all filetypes */
			$.fn.tableExport.rowDel = "\r\n";
		},
		alertTips: function() {
			layer.tips("点击此处可以导出Excel表", "#jjjj", {
				tips: [1, "#000"],
				time: 5000,
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.getAllHotel();
		},
		getStatus: function(hotelId) {
			var index = layer.msg("导出数据量大，请耐心等候...", {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "common/exportStatus",
				"type": "GET",
				"data": {
					"hotelId": hotelId,
				},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
					iEvent.generateTable(data.data);
				}
			});
		},
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
					iEvent.initHotelSelect(data.data);
					layer.closeAll();
				}
			});
		},
		generateTable: function(data) {
			$("tbody").empty();
			$.each(data, function(index, item) {
				var trData = '<tr>' +
					'<td>' + item.roomNum + '</td>' +
					'<td>' + item.isDeviceOnline + '</td>' +
					'<td>' + item.isRoomLightOn + '</td>' +
					'<td>' + item.isSlotIllegal + '</td>' +
					'<td>' + item.lockStatus + '</td>' +
					'<td>' + item.slotStatus + '</td>' +
					'<td>' + item.time + '</td>';
				var $trData = $(trData);
				$("#allDataTable").append($trData);
			});
			$("table").tableExport({
				headings: true,
				footers: true,
				formats: ["xlsx"],
				fileName: "数据",
				bootstrap: false,
				position: "bottom",
				ignoreRows: null,
				ignoreCols: null
			});
			iView.alertTips();
			iEvent.decorateTable();
		},
		decorateTable: function() {
			$("tbody").find("tr").each(function(index, item) {
				item.childNodes[0].ondblclick = function(e) {
					$this = $(this);
					layer.msg('只显示房号' + $this.text(), {
						time: 0, //不自动关闭,
						shade: 0.1,
						shadeClose: true,
						btn: ['确定'],
						btnAlign: 'c',
						yes: function(index) {
							layer.close(index);
							$("tbody").find("tr").each(function(index, item) {
								if(item.childNodes[0].innerHTML != $this.text()) {
									$("tbody tr:eq(" + index + ") ").css("display", "none");
								}
							});
						}
					});
				}
				if(item.childNodes[3].innerHTML == "非法用电") {
					$("tbody tr:eq(" + index + ") td:eq(3)").css("background-color", "#FF5722");
				}
				if(item.childNodes[1].innerHTML == "离线") {
					$("tbody tr:eq(" + index + ") td:eq(1)").css("background-color", "#FF5722");
				}
			});
			$("tbody tr> td:eq(1)").on("click", function(e) {
				console.log(11111111111);
				$this = $(this);
				layer.open({
					type: 4,
					content: [$this.text(), e.target], //数组第二项即吸附元素选择器或者DOM
					shade: 0,
					time: 2000,
				});
			});
		},
		//渲染酒店select
		initHotelSelect: function(hotelData) {
			$(hotelData).each(function(i) {
				$("#hotelSelect").append('<option value=' + hotelData[i].idHotelList + '>酒店名称：' + hotelData[i].hotelName + ' #酒店编号：' + hotelData[i].hotelId + ' #酒店地址：' + hotelData[i].address + '</option>')
			});
			form.render('select');
		},
	};
}());