package com.common.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.BaseController;
import com.common.services.CommonService;

/**
 * @author huangzhenyang
 * 
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	@Autowired
	CommonService commonService;
	private static final Logger logger = Logger.getLogger(CommonController.class);

	public boolean hotelIDIsNot(String hotelID) {
		return false;
	}

	/**
	 * 验证账号是否唯一
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "verifyAccountUnique", method = RequestMethod.GET)
	public boolean verifyAccountUnique(String account) throws Exception {
		boolean b = false;
		try {
			b = commonService.verifyAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 验证酒店Id
	 * 
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "verifyHotelIdUnique", method = RequestMethod.POST)
	public boolean verifyHotelIdUnique(String hotelId) throws Exception {
		boolean b = false;
		try {
			b = commonService.verifyHotelId(hotelId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@ResponseBody
	@RequestMapping(value = "export", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> export() throws Exception {
		Map<String, Object> map = null;
		try {
			map = commonService.export();
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误!";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

}
