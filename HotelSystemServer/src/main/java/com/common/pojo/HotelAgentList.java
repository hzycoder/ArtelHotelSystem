package com.common.pojo;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;

/**
 * HotelAgentList entity. @author MyEclipse Persistence Tools
 */

public class HotelAgentList implements java.io.Serializable {

	// Fields

	private Integer idHotelAgentList;
	private Integer idAgentList;
	private Integer idHotelList;

	public HotelAgentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdHotelAgentList() {
		return this.idHotelAgentList;
	}

	public void setIdHotelAgentList(Integer idHotelAgentList) {
		this.idHotelAgentList = idHotelAgentList;
	}

	public Integer getIdAgentList() {
		return idAgentList;
	}

	public void setIdAgentList(Integer idAgentList) {
		this.idAgentList = idAgentList;
	}

	public Integer getIdHotelList() {
		return idHotelList;
	}

	public void setIdHotelList(Integer idHotelList) {
		this.idHotelList = idHotelList;
	}
	

}