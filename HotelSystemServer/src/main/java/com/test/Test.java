package com.test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.PatternMatchUtils;

import com.common.util.CharacterUtils;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.tcp.netty.ClientBootstrap;

public class Test {
	public static void main(String[] args) {
		// Test.nettyTest();
		// Test.md5();
		// Test.time();
		// Test.testString2Int("H999999");
		// Test.testID();
		// Test.testGenerateId(String.valueOf(123456));
		Test.generateCode();
	}

	private static void generateCode() {
		CharacterUtils characterUtils = new CharacterUtils();
		System.out.println(characterUtils.getRandomString(5));
	}

	private static void time() {
		System.out.println(new Date().getTime());
	}

	public static void nettyTest() {
		ClientBootstrap.connectServer("192.168.0.110", 9909);
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
