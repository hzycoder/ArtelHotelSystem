package com.test;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.tcp.QueueSession;
import com.tcp.newStruct.JsonStruct;

public class TestQueue {
	public static QueueSession<JsonStruct> msgQueue = new QueueSession<JsonStruct>();

	public static void main(String[] args) {
//		PushThread pushThread = new PushThread();
//		Thread thread1 = new Thread(pushThread, "push进程");
//		thread1.start();

//		ErgodicThread ergodicThread = new ErgodicThread();
//		Thread thread2 = new Thread(ergodicThread, "遍历进程");
//		thread2.start();
//
//		ScheduledExecutorService service = Executors.newScheduledThreadPool(8);// 先定义8个线程空间
//		service.scheduleAtFixedRate(new DelThread(), 0, 8, TimeUnit.SECONDS);
		// CrawlerTest()是一个实现Runnable接口的类，会自动运行里面的run()方法，0的意思就是启动等待时间，这里就是直接运行，
		// 10是10分钟，要是想小时，就把TimeUnit.MINUTES换成 TimeUnit.HOURS
	}
}

//class PushThread implements Runnable {
//	@Override
//	public void run() {
//		try {
//			while (true) {
//				JsonMsg jsonMsg = null;
//				long l = new Date().getTime();
//				switch ((int) l % 4) {
//				case 0:
//					jsonMsg = new JsonMsg("a");
//					TestQueue.msgQueue.push(jsonMsg);
//					break;
//				case 1:
//					jsonMsg = new JsonMsg("b");
//					TestQueue.msgQueue.push(jsonMsg);
//					break;
//				case 2:
//					jsonMsg = new JsonMsg("c");
//					TestQueue.msgQueue.push(jsonMsg);
//					break;
//				case 3:
//					jsonMsg = new JsonMsg("d");
//					TestQueue.msgQueue.push(jsonMsg);
//					break;
//				default:
//					jsonMsg = new JsonMsg("a");
//					TestQueue.msgQueue.push(jsonMsg);
//					break;
//				}
//				System.out.println("push一个,当前个数：" + TestQueue.msgQueue.getStorage().size());
//				TestQueue.msgQueue.print();
//				Thread.sleep(1500);
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}

//class ErgodicThread implements Runnable {
//	@Override
//	public void run() {
//		long time = new Date().getTime();
//		boolean flag = true;
//		int count = 0;
//		try {
//			while (flag) {
//				ConcurrentLinkedQueue<JsonStruct> queue = TestQueue.msgQueue.getStorage();
//				Iterator iter = queue.iterator();
//				System.out.println("-----------开始遍历-----------");
//				while (iter.hasNext()) {
//					count++;
//					JsonStruct jsonMsg = (JsonStruct) iter.next();
//					JSONObject jsonObject = jsonMsg.getContent();
//					String jsonContent = jsonObject.toJSONString();
//					if ("10010".equals(jsonObject.getString("卡号"))) {
//						System.out.println("##找到一个叛徒:" + jsonContent);
//						continue;
//					}
//					System.out.println(jsonContent);
//				}
//				System.out.println("--遍历结束，一共遍历个数：" + TestQueue.msgQueue.getStorage().size() + "休息两秒--");
//				System.out.println("当前时间" + (new Date().getTime() - time));
//				Thread.sleep(3000);
//				// if (new Date().getTime() - time > 10000) {
//				// System.out.println("✘✘✘✘✘✘✘✘用户停止了监听");
//				// TestQueue.msgQueue = new MsgQueue<JsonMsg>();
//				// TestQueue.msgQueue.print();
//				// flag = false;
//				// }
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
//
//class DelThread implements Runnable {
//
//	@Override
//	public void run() {
//		System.out.println("--------执行删除线程--------");
//		long time = new Date().getTime();
//		ConcurrentLinkedQueue<JsonStruct> queue = TestQueue.msgQueue.getStorage();
//		Iterator iter = queue.iterator();
//		while (iter.hasNext()) {
//			JsonStruct jsonStruct = (JsonStruct) iter.next();
//			JSONObject jsonObject = jsonStruct.getContent();
//			String jsonContent = jsonObject.toJSONString();
//			// System.out.println("发送的时间：" + jsonObject.getLong("发送时间") +
//			// ",time:" + time + ",已经存在:"
//			// + (time - jsonObject.getLong("发送时间")));
//			if (time - jsonObject.getLong("发送时间") > 3000) {
//				System.out.println("这个数据包已超时，将被删除：" + jsonContent);
//				continue;
//			}
//		}
//	}
//
//}