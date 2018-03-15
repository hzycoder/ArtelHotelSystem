(function(win, doc, $) {
	var ZYAjax = function() {
		this.url = CONFIG.URL;
	}
	/**
	 * @param contentType 设置发送给服务器的格式
	 * @param dataType 设置收到服务器数据的格式
	 */
	ZYAjax.prototype = {
		constructor: ZYAjax,
		ajax: function(option) {
			var url = this.url + "/" + option.url;
			$.ajax(url, {
				type: option.type, //HTTP请求类型
				data: option.data || {},
				dataType: 'json' || option.dataType, //服务器返回数据格式数据
				contentType: option.contentType || 'application/x-www-form-urlencoded',
				timeout: option.timeout || 10000, //超时时间设置为60秒;
				async: option.async,
				success: function(data, status, xmlHttpRequest) {
					//服务器返回响应，根据响应结果，分析是否登录成功；
					//动态创建列表信息
					option.success && option.success(data);
//					setTimeout(function() {
//						layer.closeAll(); //1.5s关闭layer等待框等等
//					}, 1500)
				},
				/**
				 * 
				 * @param {Object} xhr	xhr.status, xhr.readyState
				 * @param {Object} type 可能值: null, timeout, error, notmodified, parsererror
				 * @param {Object} errorThrown
				 */
				error: function(xhr, type, errorThrown) {
					if(option.error) {
						option.error(xhr, type, errorThrown);
						return;
					}
					//异常处理
					layer.closeAll(); //关闭layer等待框等等
					layer.msg("系统内部错误！", { //显示失败信息
						icon: 2,
					});
				}
			});
		}
	}

	win.ZYAjax = new ZYAjax();
})(window, document, jQuery);