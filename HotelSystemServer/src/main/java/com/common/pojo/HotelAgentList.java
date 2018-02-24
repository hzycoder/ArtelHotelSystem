package com.common.pojo;

/**
 * HotelAgentList entity. @author MyEclipse Persistence Tools
 */

public class HotelAgentList implements java.io.Serializable {

	// Fields

	private Integer idHotelAgentList;
	private AgentList agentList;
	private HotelList hotelList;

	// Constructors

	/** default constructor */
	public HotelAgentList() {
	}

	/** full constructor */
	public HotelAgentList(AgentList agentList, HotelList hotelList) {
		this.agentList = agentList;
		this.hotelList = hotelList;
	}

	// Property accessors

	public Integer getIdHotelAgentList() {
		return this.idHotelAgentList;
	}

	public void setIdHotelAgentList(Integer idHotelAgentList) {
		this.idHotelAgentList = idHotelAgentList;
	}

	public AgentList getAgentList() {
		return this.agentList;
	}

	public void setAgentList(AgentList agentList) {
		this.agentList = agentList;
	}

	public HotelList getHotelList() {
		return this.hotelList;
	}

	public void setHotelList(HotelList hotelList) {
		this.hotelList = hotelList;
	}

}