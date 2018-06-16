package com.device.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.common.pojo.RoomSoltList;
import com.common.pojo.SoltList;
import com.common.pojo.UpgradeFile;
import com.device.controller.DeviceController;
import com.device.dao.DeviceDao;
import com.device.dto.AgentDto;
import com.device.dto.BindingAgentDto;
import com.device.dto.BindingHotelDto;
import com.device.dto.BindingRoomDto;
import com.device.dto.DeviceCountDto;
import com.device.dto.DeviceDto;
import com.device.services.DeviceService;

@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {
	private static final Logger logger = Logger.getLogger(DeviceController.class);
	@Autowired
	DeviceDao deviceDao;

	@Override
	public Map<String, Object> getAgentByHotelId(String hotelId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<AgentList> agentList = deviceDao.getAgentListByHotelId(hotelId);
		map.put("data", agentList);
		return map;
	}
	
	@Override
	public Map<String, Object> getSoltByAgentId(String agentId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<DeviceDto> list = deviceDao.getSoltByAgentId(agentId);
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}

	@Override
	public Map<String, Object> getDeviceCountByHotelId(String hotelId) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		int agentCount = deviceDao.getAgentCount(hotelId);
		int deviceCount =deviceDao.getDeviceCount(hotelId);
		map.put("data", new DeviceCountDto(hotelId,deviceCount,agentCount));
		return map;
	}

	@Override
	public Map<String, Object> getAgentByInn(String hotelId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<DeviceDto> agentList = deviceDao.getAgentByInn(hotelId);
		map.put("data", agentList);
		return map;
	}

	@Override
	public Map<String, Object> getAgentAndRoomRelations(String agentId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<AgentDto> list = deviceDao.getAgentAndRoomRelations(agentId);
		map.put("data", list);
		return map;
	}

	@Override
	public Map<String, Object> getslotIdByAgentId(String agentId) {
		Map<String, Object> map = new HashMap<String,Object>();
		String slotId = deviceDao.getslotIdByAgentId(agentId).toString();
		map.put("data", slotId);
		return map;
	}

	@Override
	public void saveFile(CommonsMultipartFile commonsMultipartFile) {
		try {
			UpgradeFile upgradeFile = new UpgradeFile();
			String fileName = commonsMultipartFile.getOriginalFilename();
			byte[] bytes = commonsMultipartFile.getBytes();
			upgradeFile.setFileContent(bytes);
			upgradeFile.setUploadTime(new Timestamp(new Date().getTime()));
			logger.info("尝试输出内容1："+new String(bytes));
			logger.info("上传文件名:" + fileName);
			deviceDao.saveFile(upgradeFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void binding(Integer roomId, Integer slotId,Integer hotelId) {
		Map<String,Object> map = new HashMap<String,Object>();
		RoomSoltList roomSoltList = deviceDao.getRoomSoltListBySlotId(slotId);
		SoltList soltList = deviceDao.getSlotListBySlotId(slotId);
		HotelAgentList hotelAgentList = deviceDao.getHotelAgentListByAgentId(soltList.getAgentId());
		if (null == roomSoltList) {//未绑定
			roomSoltList = new RoomSoltList();
			roomSoltList.setIdRoomList(roomId);
			roomSoltList.setIdsoltList(slotId);
			deviceDao.saveInstance(roomSoltList);
		}else{//已绑定 更改绑定信息
			roomSoltList.setIdRoomList(roomId);
			roomSoltList.setIdsoltList(slotId);
			deviceDao.updateRoomSlotList(roomSoltList);
		}
		if(null == hotelAgentList){//已绑定
			hotelAgentList = new HotelAgentList();
			hotelAgentList.setIdAgentList(soltList.getAgentId());
			hotelAgentList.setIdHotelList(hotelId);
			deviceDao.saveInstance(hotelAgentList);
		}else{//已绑定 更改绑定信息
			hotelAgentList.setIdAgentList(soltList.getAgentId());
			hotelAgentList.setIdHotelList(hotelId);
			deviceDao.updateHotelAgentList(hotelAgentList);
		}
	}

	@Override
	public Map<String, Object> checkBinding(Integer slotId, Integer hotelId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		BindingAgentDto bindingAgentDto = deviceDao.getAgentBySlotId(String.valueOf(slotId));
		map.put("agent", bindingAgentDto);
		if (null == bindingAgentDto) {
			map.put("hotel", null);
		}else{ 
			BindingHotelDto bindingHotelDto = deviceDao.getHotelByAgentId(String.valueOf(bindingAgentDto.getAgentId()));
			map.put("hotel", bindingHotelDto);
		}
		BindingRoomDto bindingRoomDto = deviceDao.getRoomBySlotId(String.valueOf(slotId));
		map.put("room", bindingRoomDto);
		return map;
	}

}
