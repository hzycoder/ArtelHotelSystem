package com.tcp.frameStruct;

public class ConstantValue {
	public static final int HEAD_DATA = 0xffaaffbb;
	public static final int IS_AES = 0;
	public static final int TEMP_PMS_ID = 1;
	public static final int TEMP_SESSION_ID = 1;
	
	//ServerHnadle Result
	public static final int SH_RESULT_FAIL = 0;
	public static final int SH_RESULT_SUCCESS = 1;
	public static final int SH_RESULT_OTDER = 2;
	
	//data length
	public static final int VS_CMDTYPE_LENGTH = 1;
	public static final int VS_SUB_DEV_LENGTH = 1;
	public static final int VS_GSMID_LENGTH = 12;
	public static final int VS_TOKEN_ID_LENGTH = 32;
	
	//token id list
	public static final String HW_TOKEN_ID = "01bc156ac796828d0d08625f86f6dc55";
	public static final String WEB_TOKEN_ID = "02bc156ac796828d0d08625f86f6dc55";
	public static final String PMS_TOKEN_ID = "03bc156ac796828d0d08625f86f6dc55";
	
	
	//the cmd type of Hardware to Agent
	public static final int H_A_CMD_VERIFY = 0x00;
	public static final int H_A_CMD_ONLINE = 0x01;
	public static final int H_A_CMD_REQUEST_COMMAND = 0x02;
	public static final int H_A_CMD_SOLT_STATUS = 0x03;
	public static final int H_A_CMD_GET_VERSION = 0x04;
	public static final int H_A_CMD_GET_SOFTWARE = 0x05;
	
	/*
	public static final int H_A_CMD_SERVICE_KEY = 0x04;
	public static final int H_A_CMD_SENSOR_STATUS = 0x05;
	public static final int H_A_CMD_EXECUTE_STATUS = 0x06;
	public static final int H_A_CMD_DOORLOCK_OP = 0x07;		
	public static final int H_A_CMD_LIGHT_STATUS = 0x0A;
	public static final int H_A_CMD_GET_CONFIGURE = 0x0B;
	*/
	
	//the cmd type of Agent to HareWare
	public static final int A_W_CMD_GET_STATUS = 0x41;
	public static final int A_W_CMD_POWER_OP = 0x43;
	public static final int A_W_CMD_SERVICE_KEY = 0x44;
	public static final int A_W_CMD_DOORLOCK_OP = 0x47;
	public static final int A_W_CMD_SEND_VERSION = 0x48;
	public static final int A_W_CMD_SEND_SOFTWARE = 0x49;
	public static final int A_W_CMD_LIGHT_OP = 0x4A;
	public static final int A_W_CMD_SEND_CONFIGURE = 0x4B;
	public static final int A_W_CMD_HW_NETWORK = 0x4C;
	public static final int A_W_CMD_COMMAND_READY = 0x42;
	
}
