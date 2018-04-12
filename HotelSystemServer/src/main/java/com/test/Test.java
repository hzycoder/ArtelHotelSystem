package com.test;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.tcp.netty.ClientBootstrap;

public class Test {
	public static void main(String[] args) {
//		Test.nettyTest();
//		Test.md5();
		Test.time();
	}

	private static void time() {
		System.out.println(new Date().getTime());
	}

	public static void nettyTest() {
		ClientBootstrap.connectServer("192.168.0.110", 9909);
	}

	public static void md5(){
		long g= System.currentTimeMillis();
		String solt = String.valueOf(g);
		String pass = "admin";
		String soltMd5 = DigestUtils.md5Hex(solt);
		String passMd5 = DigestUtils.md5Hex(pass);
		System.out.println();
		System.out.println("盐："+solt);
		System.out.println("MD5后的盐啊："+soltMd5);
		System.out.println("密码："+pass);
		System.out.println("MD5后的密码："+passMd5);
		
		StringBuffer a = new StringBuffer();
		a.append(soltMd5).append(passMd5);
		System.out.println(a.toString());
		System.out.println("合并后的盐："+DigestUtils.md5Hex(a.toString()));
	}
}
