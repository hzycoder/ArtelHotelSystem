(function() {
	//全局变量定义：
	layui.config({ //使用自定义模块paging
		base: 'js/',
		version: new Date().getTime()
	});
	var element,
		layer,
		navbar,
		tab;
	//手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'),
		shadeMobile = $('.site-mobile-shade');

	//view方法：
	var iView = {
		init: function() {
			//			console.log("init");
			iView.iframeResize(); //iframe自适应
			//			iView.initNavbar();
			$("#clearCached").on("click", function() {
				iEvent.clearCached();
			});
			$('.admin-side-toggle').on('click', function() {
				iEvent.toggleLeftMenu();
			});
			$('.admin-side-full').on('click', function() {
				iEvent.fullScreen();
			});
			$('#setting').on('click', function() {
				iEvent.setting();
			});
			treeMobile.on('click', function() {
				$('body').addClass('site-mobile');
			});
			shadeMobile.on('click', function() {
				$('body').removeClass('site-mobile');
			});
		},
		//iframe自适应
		iframeResize: function() {
			$(window).on('resize', function() {
				var $content = $('.admin-nav-card .layui-tab-content');
				$content.height($(this).height() - 147);
				$content.find('iframe').each(function() {
					$(this).height($content.height());
				});
			}).resize();
		},

	};
	//event方法：
	var iEvent = {
		init: function() {
			layui.use(["layer", "element", "navbar", "tab"], function() {
				element = layui.element(); //
				navbar = layui.navbar();
				layer = layui.layer;
				//				console.log("   " + JSON.stringify(navbar));
				//清除缓存
				$('#clearCached').on('click', function() {
					navbar.cleanCached();
					layer.alert('清除完成!', {
						icon: 1,
						title: '系统提示'
					}, function() {
						location.reload(); //刷新
					});
				});
				//tab 初始化
				tab = layui.tab({
					elem: '.admin-nav-card' //设置选项卡容器
						,
					//maxSetting: {
					//	max: 5,
					//	tipMsg: '只能开5个哇，不能再开了。真的。'
					//},
					contextMenu: true,
					onSwitch: function(data) {
						console.log(data.id); //当前Tab的Id
						console.log(data.index); //得到当前Tab的所在下标
						console.log(data.elem); //得到当前的Tab大容器

						console.log(tab.getCurrentTabId())
					},
					closeBefore: function(obj) { //tab 关闭之前触发的事件
						console.log(JSON.stringify(obj));
						//obj.title  -- 标题
						//obj.url    -- 链接地址
						//obj.id     -- id
						//obj.tabId  -- lay-id
						if(obj.title === 'BTable') {
							layer.confirm('确定要关闭' + obj.title + '吗?', {
								icon: 3,
								title: '系统提示'
							}, function(index) {
								//因为confirm是非阻塞的，所以这里关闭当前tab需要调用一下deleteTab方法
								tab.deleteTab(obj.tabId);
								layer.close(index);
							});
							//返回true会直接关闭当前tab
							return false;
						} else if(obj.title === '表单') {
							layer.confirm('未保存的数据可能会丢失哦，确定要关闭吗?', {
								icon: 3,
								title: '系统提示'
							}, function(index) {
								tab.deleteTab(obj.tabId);
								layer.close(index);
							});
							return false;
						}
						return true;
					}
				});
				//tab初始化 end
				//navbar初始化
				navbar.set({
					spreadOne: true,
					elem: '#admin-navbar-side',
					cached: true,
					data: navs //设置左侧导航的菜单内容
					/*cached:true,
					url: 'datas/nav.json'*/
				});
				//渲染navbar
				navbar.render();
				//监听点击事件
				navbar.on('click(side)', function(data) {
					console.log("tap!\n" + data.field.title);
					switch(data.field.title) {
						case "酒店查询":
						console.log(999)
//						tab.deleteTab(obj.tabId);
//						tab.tabAdd(data.filed);
//							iEvent.getAllUser();
							break;
						default:
							break;
					}
					
					tab.tabAdd(data.field);
				});
				//navbar初始化 end
			});
		},
		clearCached: function() {
			navbar.cleanCached();
			layer.alert('清除完成!', {
				icon: 1,
				title: '系统提示'
			}, function() {
				location.reload(); //刷新
			});
		},
		toggleLeftMenu: function() {
			var sideWidth = $('#admin-side').width();
			if(sideWidth === 200) {
				$('#admin-body').animate({
					left: '0'
				}); //admin-footer
				$('#admin-footer').animate({
					left: '0'
				});
				$('#admin-side').animate({
					width: '0'
				});
			} else {
				$('#admin-body').animate({
					left: '200px'
				});
				$('#admin-footer').animate({
					left: '200px'
				});
				$('#admin-side').animate({
					width: '200px'
				});
			}
		},
		fullScreen: function() {
			var docElm = document.documentElement;
			//W3C  
			if(docElm.requestFullscreen) {
				docElm.requestFullscreen();
			}
			//FireFox  
			else if(docElm.mozRequestFullScreen) {
				docElm.mozRequestFullScreen();
			}
			//Chrome等  
			else if(docElm.webkitRequestFullScreen) {
				docElm.webkitRequestFullScreen();
			}
			//IE11
			else if(elem.msRequestFullscreen) {
				elem.msRequestFullscreen();
			}
			layer.msg('按Esc即可退出全屏');
		},
		setting: function() {
			tab.tabAdd({
				href: '/Manage/Account/Setting/',
				icon: 'fa-gear',
				title: '设置'
			});
		},
	};

	window.onload = function() {

		iView.init();
		iEvent.init();
	};
}());