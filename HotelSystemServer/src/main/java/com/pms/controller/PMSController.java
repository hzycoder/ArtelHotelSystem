package com.pms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseController;
import com.common.util.MyException;
import com.pms.services.PMSServices;

@Controller
@RequestMapping("/pms")
public class PMSController extends BaseController {
	@Autowired
	PMSServices pmsServices;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> getHotels(@RequestBody JSONObject requestDto) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String param = null;
			String sign = requestDto.get("sign").toString();
			String timestamp = requestDto.get("timestamp").toString();
			String requestType = requestDto.get("requestType").toString();
			if (null == requestType) {
				throw new MyException("请求方法错误");
			}
			if (null == timestamp) {
				throw new MyException("时间戳错误");
			}
			if (null == sign) {
				throw new MyException("验证错误");
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuffer bf = new StringBuffer();

			JSONObject data = (JSONObject) JSONObject.parse(requestDto.get("data").toString());

			switch (requestType) {
			case "getHotelList":
				bf.append(requestType).append(simpleDateFormat.parse(requestDto.get("timestamp").toString()).getTime());
				System.out.println(bf.toString());
				sign = DigestUtils.md5Hex(bf.toString());
				if (!requestDto.get("sign").toString().equals(sign)) {
					throw new MyException("验证失败");
				}
				bf.setLength(0);
				bf.append(requestType);
				map = pmsServices.getHotelList(1);
				break;
			case "getRoomStatusListByHotelCode":
				if (null == data.get("hotelCode")) {
					throw new MyException("参数错误");
				}
				param = data.get("hotelCode").toString();
				bf.append(param).append(requestType)
						.append(simpleDateFormat.parse(requestDto.get("timestamp").toString()).getTime());
				sign = DigestUtils.md5Hex(bf.toString());
				if (!requestDto.get("sign").toString().equals(sign)) {
					throw new MyException("验证失败");
				}
				bf.setLength(0);
				bf.append(data.get("hotelCode").toString()).append(requestType);
				map = pmsServices.getRommStatusListByHotelCode(param);
				break;
			case "getRoomStatusByRoomCode":
				if (null == data.get("roomCode")) {
					throw new MyException("参数错误");
				}
				param = data.get("roomCode").toString();
				bf.append(param).append(requestType)
						.append(simpleDateFormat.parse(requestDto.get("timestamp").toString()).getTime());
				sign = DigestUtils.md5Hex(bf.toString());
				if (!requestDto.get("sign").toString().equals(sign)) {
					throw new MyException("验证失败");
				}
				bf.setLength(0);
				bf.append(data.get("roomCode").toString()).append(requestType);
				map = pmsServices.getRoomStatusByRoomCode(param);
				break;
			case "getInstructionsHistory":
				if (null == data.get("beginId") && null == data.get("endId")) {
					throw new MyException("参数错误");
				}
				String beginId = data.get("beginId").toString();
				String endId = data.get("endId").toString();
				bf.append(beginId).append(requestType)
						.append(simpleDateFormat.parse(requestDto.get("timestamp").toString()).getTime());
				sign = DigestUtils.md5Hex(bf.toString());
				if (!requestDto.get("sign").toString().equals(sign)) {
					throw new MyException("验证失败");
				}
				bf.setLength(0);
				bf.append(beginId).append(requestType);
				map = pmsServices.getInstructionsHistory(beginId, endId);
				break;
			default:
				throw new MyException("请求方法错误");
			}
			long backTimestamp = new Date().getTime();
			String time = simpleDateFormat.format(backTimestamp);
			map.put("timestamp", time);
			bf.append(backTimestamp);
			System.out.println(bf.toString());
			sign = DigestUtils.md5Hex(bf.toString());
			map.put("sign", sign);
			return map;
		} catch (MyException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("failure", e.getMessage());
			return map;
		} catch (ParseException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("failure", "时间戳解析失败");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("failure", "系统内部错误");
			return map;
		}
	}

}
