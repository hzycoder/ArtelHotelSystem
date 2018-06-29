package com.hotel.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseException;
import com.common.dao.CommonDao;
import com.common.pojo.HotelList;
import com.common.pojo.LoginUserList;
import com.common.pojo.RoomList;
import com.hotel.dao.HotelDao;
import com.hotel.dto.HotelDto;
import com.hotel.dto.RoomDto;
import com.hotel.services.HotelService;
import com.tcp.ChannelSession;
import com.tcp.dto.NewBatchRoomMsg;
import com.tcp.dto.NewHotelMsg;
import com.tcp.dto.NewRoomMsg;
import com.tcp.dto.Msg;
import com.tcp.frameStruct.FrameStruct;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDao hotelDao;
	@Autowired
	LoginUserList user;
	@Autowired
	CommonDao commonDao;

	// 酒店查询
	@Override
	public Map<String, Object> getHotels(String userID, String permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HotelDto> hotelList = null;
		StringBuffer sql = new StringBuffer();
		if (Integer.valueOf(permission) == 0) {
			hotelList = hotelDao.gethotels();
			sql.append("SELECT " + "COUNT(*) " + "FROM " + "HotelList");
		} else {
			hotelList = hotelDao.gethotels(userID);
			sql.append("SELECT" + " COUNT(*)" + " FROM" + " HotelList" + " WHERE" + " hotelManager" + " = " + "'"
					+ userID + "'");
		}
		int count = commonDao.getCount(sql.toString());
		map.put("data", hotelList);
		map.put("count", count);
		return map;
	}

	// 酒店条件查询
	@Override
	public Map<String, Object> getHotelsByConditions(String hotelName, String address, String userID,
			String permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HotelList> hotelList;
		StringBuffer sql = new StringBuffer();
		if (Integer.valueOf(permission) == 0) {
			hotelList = hotelDao.gethotelsByConditions(hotelName, address);
			sql.append("SELECT" + " COUNT(*)" + " FROM" + " HotelList" + " WHERE" + " hotelName" + " like" + " '%"
					+ hotelName + "%'" + " and" + " address" + " like " + "'%" + address + "%'");
		} else {
			hotelList = hotelDao.gethotelsByConditions(hotelName, address, userID);
			sql.append("SELECT" + " COUNT(*)" + " FROM" + " HotelList" + " WHERE" + " hotelName" + " like" + " '%"
					+ hotelName + "%'" + " and" + " address" + " like" + " '%" + address + "%'" + " and"
					+ " hotelManager" + " = " + "'" + userID + "'");
		}
		int count = commonDao.getCount(sql.toString());
		map.put("data", hotelList);
		map.put("count", count);
		return map;
	}

	@Override
	public Map<String, Object> delHotel(String HotelID) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = hotelDao.delHotel(HotelID);
		if (result == 0) {
			map.put("msg", "删除失败！");
			map.put("success", false);
		} else {
			map.put("msg", "删除成功！");
			map.put("success", true);
		}
		return map;
	}

	@Override
	public void addHotel(HotelDto hotelDto) throws Exception {
		HotelList hotelList = new HotelList();
		hotelList.setHotelName(hotelDto.getHotelName());
		// hotelList.setCountry(hotelDto.getCountry());
		// hotelList.setCity(hotelDto.getCity());
		// hotelList.setProvince(hotelDto.getProvince());
		if (null != hotelDto.getIdHotelList()) {
			hotelList.setIdHotelList(Integer.valueOf(hotelDto.getIdHotelList()));
		}
		if (StringUtils.isNotBlank(hotelDto.getAddress())) {
			hotelList.setAddress(hotelDto.getAddress());

		}
		if (StringUtils.isNotBlank(hotelDto.getHotelPhone())) {
			hotelList.setHotelPhone(hotelDto.getHotelPhone());

		}
		if (StringUtils.isNotBlank(hotelDto.getStatus())) {
			hotelList.setStatus(hotelDto.getStatus());

		}
		if (null == hotelDto.getCreateTime()) {
			hotelList.setCreateTime(new Timestamp(new Date().getTime()));
		} else {
			hotelList.setCreateTime(hotelDto.getCreateTime());
		}
		hotelList.setHotelManager(hotelDto.getHotelManager());
		if (null != hotelDto.getIdHotelList()) {// 修改酒店
			hotelList.setHotelId(hotelDto.getHotelId());
		} else {// 新增酒店
			Integer formatId = hotelDao.getMaxHotelId();// 获取当前最大ID值
			if(null == formatId) formatId = 0;
			String hotelId = generateId(String.valueOf(++formatId));//
			boolean b = testHotelID(hotelId);
			if (!b) {
				throw new BaseException("Generating ID failure");
			}
			hotelList.setHotelId(hotelId);
		}
		hotelDao.addHotel(hotelList);
		//新增酒店发送消息
		Channel channel = ChannelSession.getChannelById("channel");
		String json = JSON.toJSONString(new Msg("TRANSPOND",new NewHotelMsg(hotelList.getHotelId())));
		System.out.println("tcp发送消息"+json);
		FrameStruct frameStruct = new FrameStruct(json.length(), json.getBytes());
		ChannelFuture channelFuture = channel.writeAndFlush(frameStruct);
		channelFuture.await();
		if (!channelFuture.isSuccess()) {
			throw new BaseException("newHotelMsg send failure");
		}
	}

	@Override
	public void modifyHotel(HotelDto hotelDto) {
		HotelList hotelList = new HotelList();
		hotelList.setIdHotelList(Integer.valueOf(hotelDto.getIdHotelList()));
		hotelList.setHotelId(hotelDto.getHotelId());
		hotelList.setHotelName(hotelDto.getHotelName());
		// hotelList.setCountry(hotelDto.getCountry());
		// hotelList.setCity(hotelDto.getCity());
		// hotelList.setProvince(hotelDto.getProvince());
		hotelList.setAddress(hotelDto.getAddress());
		hotelList.setHotelPhone(hotelDto.getHotelPhone());
		hotelList.setHotelManager(hotelDto.getHotelManager());
		hotelList.setStatus(hotelDto.getStatus());
		hotelList.setCreateTime(hotelDto.getCreateTime());
		hotelDao.addHotel(hotelList);
	}

	@Override
	public void modifyRoom(RoomDto roomDto) {
		RoomList roomList = new RoomList();
		roomList.setIdRoomList(Integer.valueOf(roomDto.getIdRoomList()));
		roomList.setRoomId(roomDto.getRoomId());
		roomList.setRoomNum(roomDto.getRoomNum());
		roomList.setFloor(roomDto.getFloor());
		roomList.setHotelId(roomDto.getHotelId());
		hotelDao.addRoom(roomList);
	}

	/**
	 * @param id
	 * @return 生成酒店编号
	 */
	public String generateId(String id) {
//		StringBuffer sb = new StringBuffer(id);
//		while (sb.toString().length() != 6) {
//			sb.insert(0, "0");
//		}
//		sb.insert(0, "H");
//		System.out.println("生成的主键" + sb.toString());
//		return sb.toString();
		String str = String.format("%06d", Integer.valueOf(id));
		StringBuffer sb = new StringBuffer(str);
		sb.insert(0, "H");
		return sb.toString();
	}

	/**
	 * @param id
	 * @return 测试是否为酒店ID
	 */
	public boolean testHotelID(String id) {
		return Pattern.matches("H\\d{6}", id);
	}

	@Override
	public void addRoom(RoomDto roomDto) {
		RoomList roomList = new RoomList();
		int roomCount = hotelDao.getRoomCount();
		roomList.setRoomId(roomDto.getHotelNum().replace("H", "R") + String.format("%08d", roomCount));
		roomList.setHotelId(roomDto.getHotelId());
		roomList.setFloor(roomDto.getFloor());
		roomList.setRoomNum(roomDto.getRoomNum());
		hotelDao.addRoom(roomList);
		Channel channel = ChannelSession.getChannelById("channel");
		String json = JSON.toJSONString(new Msg("TRANSPOND",new NewRoomMsg(roomDto.getHotelNum(),roomDto.getRoomId())));
		System.out.println("tcp发送消息"+json);
		FrameStruct frameStruct = new FrameStruct(json.length(), json.getBytes());
		ChannelFuture channelFuture = channel.writeAndFlush(frameStruct);
		try {
			channelFuture.await();
			if (!channelFuture.isSuccess()) {
				throw new BaseException("newRoomMsg send failure");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void batchAddRoom(RoomDto roomDto) {
		int roomCount = hotelDao.getRoomCount();
		int roomNumSize = roomDto.getRoomNumList().size();
		String[] roomStr = new String[roomNumSize];
		for (int i = 0; i < roomNumSize; i++) {
			RoomList roomList = new RoomList();
			roomList.setRoomId(roomDto.getHotelNum().replace("H", "R") + String.format("%08d", roomCount));
			roomCount++;
			roomList.setHotelId(roomDto.getHotelId());
			roomList.setFloor(roomDto.getFloor());
			roomList.setRoomNum(roomDto.getRoomNumList().get(i));
			roomStr[i] = roomList.getRoomId();
			hotelDao.addRoom(roomList);
		}
		Channel channel = ChannelSession.getChannelById("channel");
		String json = JSON.toJSONString(new Msg("TRANSPOND",new NewBatchRoomMsg(roomDto.getHotelNum(),roomStr)));
		System.out.println("tcp发送消息"+json);
		FrameStruct frameStruct = new FrameStruct(json.length(), json.getBytes());
		ChannelFuture channelFuture = channel.writeAndFlush(frameStruct);
		try {
			channelFuture.await();
			if (!channelFuture.isSuccess()) {
				throw new BaseException("newBatchRoomMsg send failure");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getRooms(String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoomList> roomList = hotelDao.getRooms(hotelId);
		int count = hotelDao.getRoomsCountByHotelID(hotelId);
		map.put("data", roomList);
		map.put("count", count);
		return map;
	}
	
	@Override
	public Map<String, Object> getUnbindedRooms(String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoomList> roomList = hotelDao.getUnbindedRooms(hotelId);
		int count = hotelDao.getRoomsCountByHotelID(hotelId);
		map.put("data", roomList);
		map.put("count", count);
		return map;
	}

	@Override
	public Map<String, Object> delRoom(String roomID) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = hotelDao.delRoom(roomID);
		if (result == 0) {
			map.put("msg", "删除失败！");
			map.put("success", false);
		} else {
			map.put("msg", "删除成功！");
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> getTypeOfHotel(String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> deviceCountList = hotelDao.getTypeOfHotel(hotelId);
		map.put("data", deviceCountList);
		return map;
	}

	@Override
	public boolean verifyHotelName(String hotelName) {
		List<HotelList> list = hotelDao.getHotelByHotelName(hotelName);
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verifyRoomNum(String roomNum, Integer hotelId) {
		List<RoomList> list = hotelDao.verifyRoomNum(roomNum,hotelId);
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}
