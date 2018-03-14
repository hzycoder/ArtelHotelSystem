package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/base")
public class CommonController {

	public boolean hotelIDIsNot(String hotelID) {
		
		return false;
	}
}
