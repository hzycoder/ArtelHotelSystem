package com.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
	@Value("#{configProperties['server.ip']}")
	public String SERVER_IP;
	@Value("#{configProperties['server.netty.port']}")
	public int SERVER_PORT;

	@Value("#{configProperties['test.ip']}")
	public String SERVER_IP_TEST;
	@Value("#{configProperties['test.netty.port']}")
	public int SERVER_PORT_TEST;

}
