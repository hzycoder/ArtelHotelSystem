package com.tcp;

import java.util.LinkedList;

/*
 * 存放tcp接收消息的队列
 */
public class MsgQueue<T> {
	private LinkedList<T> storage = new LinkedList<T>();

	// 加入队列尾部
	public synchronized void push(T e) {
		storage.addLast(e);
	}

	// 获取队列头元素
	public T getFirst() {
		return storage.getFirst();
	}

	// 获取队列尾元素
	public T getLast() {
		return storage.getLast();
	}

	// 去除队列第一个元素
	public void removeFirst() {
		storage.removeFirst();
	}

	// 去除队列index元素
	public void remove(int index) {
		storage.remove(index);
	}

	// 判断队列是否为空
	public boolean empty() {
		return storage.isEmpty();
	}

	@Override
	public String toString() {
		return "MsgQueue [storage=" + storage.toString() + "]";
	}

}
