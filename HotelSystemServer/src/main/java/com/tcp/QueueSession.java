package com.tcp;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

import com.tcp.newStruct.JsonStruct;

/*
 * 存放tcp接收消息的队列
 */
public class QueueSession<T> {
	private static ConcurrentLinkedQueue<JsonStruct> queue = new ConcurrentLinkedQueue<JsonStruct>();
	private static int hashCode;

	public static int getHashCode() {
		return hashCode;
	}

	public static void setHashCode(int hashCode) {
		QueueSession.hashCode = hashCode;
	}

	public static ConcurrentLinkedQueue<JsonStruct> getQueue() {
		return queue;
	}

	public static void setQueue(ConcurrentLinkedQueue<JsonStruct> queue) {
		QueueSession.queue = queue;
	}

//	public void push(JsonStruct e) {
//		queue.add(e);
//	}
//
//	// 加入队列尾部
//	// public synchronized void push(T e) {
//	// queue.
//	// queue.addLast(e);
//	// }
//	//
//	// // 获取队列头元素
//	// public T getFirst() {
//	// return queue.getFirst();
//	// }
//	//
//	// // 获取队列尾元素
//	// public T getLast() {
//	// return queue.getLast();
//	// }
//	//
//	// // 去除队列第一个元素
//	// public void removeFirst() {
//	// queue.removeFirst();
//	// }
//
//	// 去除队列index元素
//	public void remove(int index) {
//		queue.remove(index);
//	}
//
//	// 判断队列是否为空
//	public boolean empty() {
//		return queue.isEmpty();
//	}

	public void print() {
		System.out.println("MsgQueue [storage=" + queue.toString() + "]");
	}

}
