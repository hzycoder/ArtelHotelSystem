package com.tcp.thread;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.alibaba.fastjson.JSONObject;
import com.tcp.QueueSession;
import com.tcp.newStruct.JsonStruct;

/**
 * @author huangzhenyang 删除线程
 */
public class DelThread implements Runnable {

	@Override
	public void run() {
		ConcurrentLinkedQueue<JsonStruct> queue = QueueSession.getQueue();
		try {
			long time = new Date().getTime();
			JsonStruct jsonStruct = queue.peek();
			if (jsonStruct != null) {
				JSONObject json = jsonStruct.getContent();
				long l = new Date().getTime();
				// json.containsKey("TIME");
				if (json.getLong("TIME") == null) {
					queue.poll();
					// System.out.println("消息超时，1条消息被移除队列，当前队列数据量为：" +
					// queue.size());
				} else if (time - json.getLong("TIME") > 1000 * 10) {
					queue.poll();
					// System.out.println("消息超时，1条消息被移除队列，当前队列数据量为：" +
					// queue.size());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Iterator iter = queue.iterator();
		// while (iter.hasNext()) {
		// JsonStruct jsonStruct = (JsonStruct) iter.next();
		// JSONObject json = jsonStruct.getContent();
		// String jsonContent = json.toJSONString();
		// if (time - json.getLong("sendTime") > 1000*60) {
		//// System.out.println("这个数据包已超时，将被删除：" + jsonContent);
		// continue;
		// }
		// }
	}

}