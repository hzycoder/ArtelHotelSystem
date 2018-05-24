package com.tcp.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.common.pojo.HotelList;
import com.tcp.ChannelSession;
import com.tcp.dao.TcpDao;
import com.tcp.newStruct.JsonStruct;
import com.tcp.services.TcpService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

@Transactional
@Service
public class TcpServiceImpl implements TcpService{
	@Autowired
	TcpDao tcpDao;
	private static final Logger logger = Logger.getLogger(TcpServiceImpl.class);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void upgrade(String hotelId) {
		List<String> macList = tcpDao.getMacAddress(hotelId);
		HotelList hotel = tcpDao.getHotel(hotelId);
		Map<String, Channel> channelMap = ChannelSession.getChannels();
		Iterator<String> it = channelMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Channel channel = channelMap.get(key);
			String upgradeJson = "{\"type\":\"upgrade\",\"data\":{\"hotelId\":\""+hotelId+"\",\"hotelName\":\""+hotel.getHotelName()+"\","
					+ "\"macAddress\":\""+StringUtils.strip(macList.toString(),"[]")+"\"},\"time\":\""+simpleDateFormat.format(new Date().getTime())+"\"}";
			JsonStruct json = new JsonStruct(JSONObject.parseObject(upgradeJson));
			channel.writeAndFlush(json);
		}
	}

	@Override
	public boolean roomOperation(String json) {
		Channel channel = null;
		ChannelFuture channelFuture = null;
		try {
			Map<String, Channel> channelMap = ChannelSession.getChannels();
			Iterator<String> it = channelMap.keySet().iterator();
			JsonStruct jsonStruct = new JsonStruct(JSONObject.parseObject(json));
			while (it.hasNext()) {
				String key = it.next();
				channel = channelMap.get(key);
				System.out.println("key:"+key);
			}
			channelFuture = channel.writeAndFlush(jsonStruct);
			channelFuture.await();
			System.out.println("是否发送成功："+channelFuture.isSuccess());
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return channelFuture.isSuccess();
	}

}
