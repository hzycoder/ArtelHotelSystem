package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.util.CharacterUtils;
import com.mchange.v1.lang.GentleThread;
import com.tcp.MsgQueue;
import com.tcp.dto.NewBatchRoomMsg;
import com.tcp.dto.NewHotelMsg;
import com.tcp.dto.NewRoomMsg;
import com.tcp.dto.Msg;
import com.tcp.frameStruct.ConstantValue;
import com.tcp.netty.ClientBootstrap;

import io.netty.handler.codec.json.JsonObjectDecoder;

public class Test {
	public static void main(String[] args) {
		// Test.nettyTest();
		// handlerQueue();
		// Test.md5();
		// Test.time();
		// Test.testString2Int("H999999");
		// Test.testID();
		// Test.testGenerateId(String.valueOf(123456));
		// Test.generateCode();
		// JSONTest();
		// testTCPSend();
		// testTimestamp();
		// GeneratedMsg gentleThread = new GeneratedMsg();
		// Thread thread = new Thread(gentleThread, "生成msg");
		// thread.start();
		// testBeginEnd();
		// testsqlbuffer();
		// StringBuffer sb = new StringBuffer();
		// sb.append("00000000000000");
		// testFastjson();
		// string2Hex();
		// generateRoomNum();
		newHotelAndRoomMsg();
	}

	public static void newHotelAndRoomMsg() {
		Msg transpond = new Msg();
		NewHotelMsg newHotelMsg = new NewHotelMsg();
		newHotelMsg.setHOTEL_LIST("H000001");
//		transpond.setPARM(newHotelMsg);
//		String str = JSON.toJSONString(transpond, false);
//		System.out.println(str);
		
//		NewRoomMsg newRoomMsg = new NewRoomMsg("H00001","R000010000001");
//		transpond.setPARM(newRoomMsg);
//		String str = JSON.toJSONString(transpond);
//		System.out.println(str);
		
		String[] strs = {"R000010000001","R000010000001","R000010000001"};
		NewBatchRoomMsg newBatchRoomMsg = new NewBatchRoomMsg("H00001",strs);
		transpond.setPARM(newBatchRoomMsg);
		String str = JSON.toJSONString(transpond);
		System.out.println(str);
	}

	public static void generateRoomNum() {
		int roomCount = 8000;
		String str = String.format("%08d", roomCount);
		System.out.println(str);
	}

	public static void string2Hex() {
		String jsonContent = "{\"METHOD\":\"SOLT_STATUS\",\"SOLT_ID\":\"6\",\"TYPE\":\"SOLT_STATUS\",\"PARM\":\"1234567890123456\",\"STATUS\":\"CARD_IN\",\"TIME\":\""
				+ "2018-06-05 17:00:35" + "\"}";

	}

	public static void testFastjson() {
		String upgradeJson = "{\"type\":\"upgrade\",\"data\":{\"hotelId\":\"" + "111" + "\",\"hotelName\":\""
				+ "AThotel" + "\"," + "\"macAddress\":\"" + "123123,13123124,14124" + "\"},\"time\":\""
				+ new Date().getTime() + "\"}";
		JSONObject jsonObject = JSONObject.parseObject(upgradeJson);
		JSONObject data = (JSONObject) jsonObject.get("data");
		System.out.println(data.get("hotelName"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date().getTime()));
	}

	public static void testsqlbuffer() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "A.deviceCount " + "FROM " + "AgentList A " + "WHERE " + "A.idAgentList " + "IN "
				+ "(SELECT " + "HA.AgentList_idAgentList " + "FROM " + "HotelAgentList HA " + "WHERE "
				+ "HA.HotelList_idHotelList " + "=" + "'')");
		System.out.println(sql.toString());
	}

	public static void testBeginEnd() {
		int begin = 1;
		int end = 7;
		StringBuffer idBuf = new StringBuffer();
		for (int i = begin; i < end + 1; i++) {
			idBuf.append("'" + i + "',");
		}
		idBuf.deleteCharAt(idBuf.length() - 1);
		System.out.println(idBuf.toString());
	}

	public static void handlerQueue() {
		// MsgQueue<JsonMsg> msgQueue = new MsgQueue<JsonMsg>();
		// JSONObject jsonObject = new JSONObject();
		// jsonObject.put("卡号", "8888");
		// JsonMsg jsonMsg = new JsonMsg(jsonObject);
		// msgQueue.push(jsonMsg);
		// msgQueue.print();
		// LinkedList<JsonMsg> queue = msgQueue.getStorage();
		// Iterator iter = queue.iterator();

	}

	public static void testTimestamp() {
		String param = "";
		System.out.println(DigestUtils.md5Hex("break" + param));
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long timestamp = new Date().getTime();
			String time1 = simpleDateFormat.format(timestamp);
			System.out.println("===========");
			System.out.println(time1);
			String time = time1;
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			date = df.parse(time);
			System.out.println(date.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void nettyTest() {
		ClientBootstrap.connectServer("192.168.0.110", 7777);
	}

	private static void testTCPSend() {
		System.out.println(new Date().getTime() % 3);
		long l = new Date().getTime();
		String msg = "";
		switch ((int) l % 3) {
		case 1:
			msg = "a";
			break;
		case 2:
			msg = "b";
			break;
		case 3:
			msg = "c";
			break;
		default:
			break;
		}
		System.out.println(msg);
	}

	private static void JSONTest() {
		// JSONObject jsonObject = new JSONObject();
		// jsonObject.put("学号", "1234");
		// jsonObject.put("学号1", "1234a");
		// jsonObject.put("学号2", "1234b");
		// jsonObject.put("学号3", "1234c");
		// jsonObject.put("学号4", "1234d");
		// JsonMsg jsonMsg = new JsonMsg(jsonObject);
		// System.out.println(jsonMsg.toString());
		// byte[] b = Util.object2Bytes(jsonMsg);
		// System.out.println(Arrays.toString(b));
		// Object obj = Util.bytes2Object(b);
		// ((JsonMsg) obj).print();
	}

	private static void generateCode() {
		CharacterUtils characterUtils = new CharacterUtils();
		System.out.println(characterUtils.getRandomString(5));
	}

	private static void time() {
		System.out.println(new Date().getTime());
	}

	public static void testID() {
		String id = "H000001";
		System.out.println(Pattern.matches("H\\d{6}", id));
	}

	public static void testString2Int(String formatHotelID) {
		int tempId = Integer.valueOf(formatHotelID.substring(2, formatHotelID.length()));// 截取integer
		String tempId2 = String.valueOf(++tempId);// id加一
		StringBuffer sb = new StringBuffer(tempId2);
		while (sb.toString().length() != 6) {
			sb.insert(0, "0");
		}
		sb.insert(0, "H");
		System.out.println(sb.toString());
	}

	public static void testGenerateId(String id) {
		StringBuffer sb = new StringBuffer(id);
		while (sb.toString().length() != 6) {
			sb.insert(0, "0");
		}
		sb.insert(0, "H");
		System.out.println(sb.toString());
	}

	public static void md5() {
		long g = System.currentTimeMillis();
		String solt = String.valueOf(g);
		String pass = "admin";
		String soltMd5 = DigestUtils.md5Hex(solt);
		String passMd5 = DigestUtils.md5Hex(pass);
		System.out.println();
		System.out.println("盐：" + solt);
		System.out.println("MD5后的盐啊：" + soltMd5);
		System.out.println("密码：" + pass);
		System.out.println("MD5后的密码：" + passMd5);

		StringBuffer a = new StringBuffer();
		a.append(soltMd5).append(passMd5);
		System.out.println(a.toString());
		System.out.println("合并后的盐：" + DigestUtils.md5Hex(a.toString()));
	}
}

class GeneratedMsg implements Runnable {
	@Override
	public void run() {
		try {
			Random random = new Random();
			StringBuffer bf;
			while (true) {
				bf = new StringBuffer();
				for (int i = 0; i < 3; i++) {
					bf.append(String.valueOf(random.nextInt(2)));
				}
				System.out.println(bf.toString());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
