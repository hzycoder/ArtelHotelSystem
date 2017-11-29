package com.login.dto;

public class LoginDto {
	private String account;
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDto() {
		super();
	}

	public LoginDto(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", password=" + password + "]";
	}

}
