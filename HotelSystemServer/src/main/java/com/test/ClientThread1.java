package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread1 extends Thread {
	Socket socket = null;

	public ClientThread1(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println("客户端开始连接");
			// 一直读取控制台
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//接收服务端的输入流
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			while (true) {
				//接收服务端
				byte[] inHead = new byte[4];
				bis.read(inHead);
				byte []  data = new byte [Tool.byteArrayToInt(inHead)];
				bis.read(data);
				System.out.println("从服务端接收的数据:"+new String(data).trim());
				//接收服务端 end
				
				// 包体
				byte[] content = br.readLine().getBytes();
				// 包头,固定4个字节,包含包体长度信息
				byte[] head = Tool.intToByteArray1(content.length);
				BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
				bos.write(head);
				bos.flush();
				bos.write(content);
				bos.flush();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
