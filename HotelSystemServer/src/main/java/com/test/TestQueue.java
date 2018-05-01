package com.test;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.alibaba.fastjson.JSONObject;
import com.tcp.MsgQueue;
import com.tcp.jsonMsg.JsonMsg;

public class TestQueue {
	public static MsgQueue<JsonMsg> msgQueue = new MsgQueue<JsonMsg>();

	public static void main(String[] args) {
		PushThread pushThread = new PushThread();
		Thread thread1 = new Thread(pushThread, "push进程");
		thread1.start();

		ErgodicThread ergodicThread = new ErgodicThread();
		Thread thread2 = new Thread(ergodicThread, "遍历进程");
		thread2.start();
	}

}

class PushThread implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				JsonMsg jsonMsg = null;
				long l = new Date().getTime();
				switch ((int) l % 4) {
				case 0:
					jsonMsg = new JsonMsg("a");
					TestQueue.msgQueue.push(jsonMsg);
					break;
				case 1:
					jsonMsg = new JsonMsg("b");
					TestQueue.msgQueue.push(jsonMsg);
					break;
				case 2:
					jsonMsg = new JsonMsg("c");
					TestQueue.msgQueue.push(jsonMsg);
					break;
				case 3:
					jsonMsg = new JsonMsg("d");
					TestQueue.msgQueue.push(jsonMsg);
					break;
				default:
					jsonMsg = new JsonMsg("a");
					TestQueue.msgQueue.push(jsonMsg);
					break;
				}
				System.out.println("push一个,当前个数：" + TestQueue.msgQueue.getStorage().size());
				TestQueue.msgQueue.print();
				Thread.sleep(1500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class ErgodicThread implements Runnable {

	@Override
	public void run() {
		long time = new Date().getTime();
		boolean flag = true;
		int count = 0;
		try {
			while (flag) {
				ConcurrentLinkedQueue<JsonMsg> queue = TestQueue.msgQueue.getStorage();
				Iterator iter = queue.iterator();
				System.out.println("-----------开始遍历-----------");
				while (iter.hasNext()) {
					count++;
					JsonMsg jsonMsg = (JsonMsg) iter.next();
					JSONObject jsonObject = jsonMsg.getJsonObject();
					String jsonContent = jsonObject.toJSONString();
					if ("10010".equals(jsonObject.getString("卡号"))) {
						System.out.println("##找到一个叛徒:" + jsonContent);
						continue;
					}
					System.out.println(jsonContent);
				}
				System.out.println("--遍历结束，一共遍历个数：" + TestQueue.msgQueue.getStorage().size() + "休息两秒--");
				System.out.println("当前时间"+(new Date().getTime()-time));
				Thread.sleep(3000);
				if (new Date().getTime() - time > 10000) {
					System.out.println("✘✘✘✘✘✘✘✘用户停止了监听");
					TestQueue.msgQueue = new MsgQueue<JsonMsg>();
					TestQueue.msgQueue.print();
					flag = false;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}