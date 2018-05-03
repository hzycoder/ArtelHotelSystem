package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.common.util.CharacterUtils;
import com.tcp.MsgQueue;
import com.tcp.jsonMsg.JsonMsg;
import com.tcp.netty.ClientBootstrap;

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
		testTimestamp();
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
		System.out.println(DigestUtils.md5Hex("break"+param));
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
