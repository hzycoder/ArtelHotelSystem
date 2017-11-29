package com.login.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.login.dto.LoginDto;


public interface LoginService {
	public Map<String, Object> login(LoginDto loginDto);

}
