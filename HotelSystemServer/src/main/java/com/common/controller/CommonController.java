package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.base.BaseController;


@Controller
@RequestMapping("/base")
public class CommonController extends BaseController{
	public boolean hotelIDIsNot(String hotelID) {

		return false;
	}

}
