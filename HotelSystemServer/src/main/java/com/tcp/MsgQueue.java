package com.tcp;

import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * 存放tcp接收消息的队列
 */
public class MsgQueue<T> {
	private ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<T>();

	public ConcurrentLinkedQueue<T> getStorage() {
		return queue;
	}

	public ConcurrentLinkedQueue<T> getStorage(int index) {
		return queue;
	}

	public void setStorage(ConcurrentLinkedQueue<T> storage) {
		this.queue = storage;
	}

	public void push(T e) {
		queue.add(e);
	}

	// 加入队列尾部
	// public synchronized void push(T e) {
	// queue.
	// queue.addLast(e);
	// }
	//
	// // 获取队列头元素
	// public T getFirst() {
	// return queue.getFirst();
	// }
	//
	// // 获取队列尾元素
	// public T getLast() {
	// return queue.getLast();
	// }
	//
	// // 去除队列第一个元素
	// public void removeFirst() {
	// queue.removeFirst();
	// }

	// 去除队列index元素
	public void remove(int index) {
		queue.remove(index);
	}

	// 判断队列是否为空
	public boolean empty() {
		return queue.isEmpty();
	}

	public void print() {
		System.out.println("MsgQueue [storage=" + queue.toString() + "]");
	}

}
