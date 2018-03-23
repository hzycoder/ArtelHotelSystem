package com.common.load;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;




@Component
public class LoadManager implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		System.out.println("启动netty客户端");
	}

}
