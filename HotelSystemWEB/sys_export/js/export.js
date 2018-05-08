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
			iView.alertTips();
			$("tbody").on("click", "tr", function(e) {
				$this = $(this);
				layer.open({
					type: 4,
					content: ["酒店名：" + $this.children().eq(0).text() +
						"</br>酒店编号：" + $this.children().eq(1).text() +
						"</br>地址：" + $this.children().eq(2).text() +
						"</br>酒店电话：" + $this.children().eq(3).text() +
						"</br>房间数量：" + $this.children().eq(4).text() +
						"</br>设备数量：" + $this.children().eq(5).text() +
						"</br>管理者：" + $this.children().eq(6).text() +
						"</br>管理者账号：" + $this.children().eq(7).text() +
						"</br>管理者电话：" + $this.children().eq(8).text() +
						"</br>PMS：" + $this.children().eq(9).text() +
						"</br>酒店创建时间：" + $this.children().eq(10).text() +
						"</br>使用情况：" + $this.children().eq(11).text(), e.target
					], //数组第二项即吸附元素选择器或者DOM
					shade: 0,
				});
			}).on("mouseout", function(e) {
				layer.closeAll('tips');
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
		alertTips:function(){
			layer.tips("点击此处可以导出Excel表","#jjjj",{
				tips:[1,"#000"],
				time:3000,
			});
		},
	};
	//event方法：
	var iEvent = {
		init: function() {
			iEvent.getData();
		},
		getData: function() {
			var index = layer.msg("导出数据量大，请耐心等候...", {
				icon: 16,
				shade: 0.01
			});
			ZY.ajax({
				"url": "common/export",
				"type": "GET",
				"data": {},
				"contentType": "application/json;charset=UTF-8",
				"success": function(data) {
					layer.close(index);
					iEvent.generateTable(data.data);
				}
			});
		},
		generateTable: function(data) {
			$.each(data, function(index, item) {
				var trData = '<tr>' +
					'<td>' + item.hotelName + '</td>' +
					'<td>' + item.hotelCode + '</td>' +
					'<td>' + item.hotelAddress + '</td>' +
					'<td>' + item.hotelPhone + '</td>' +
					'<td>' + item.roomCount + '</td>' +
					'<td>' + item.deviceCount + '</td>' +
					'<td>' + item.userName + '</td>' +
					'<td>' + item.userAccount + '</td>' +
					'<td>' + item.userPhone + '</td>' +
					'<td>' + item.pmsName + '</td>' +
					'<td>' + iEvent.switchUnixTime(item.hotelCreateTime) + '</td>' +
					'<td>' + item.hotelStatus + '</td>';
				var $trData = $(trData);
				var $trData1 = $(trData);
				var $trData2 = $(trData);
				var $trData3 = $(trData);
				$("#allDataTable").append($trData);
				$("#allDataTable").append($trData1);
				$("#allDataTable").append($trData2);
				$("#allDataTable").append($trData3);
			});
			$("table").tableExport({
				headings: true,
				footers: true,
				formats: ["xlsx"],
				fileName: "id",
				bootstrap: false,
				position: "bottom",
				ignoreRows: null,
				ignoreCols: null
			});
		},
		switchUnixTime: function(unixTime) {
			if(typeof unixTime != "number") {
				return unixTime;
			}
			var newDate = new Date();
			newDate.setTime(unixTime);
			return newDate.Format("yyyy-MM-dd");
		},
	};
}());