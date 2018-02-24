package com.common.pojo;

/**
 * TokenIdList entity. @author MyEclipse Persistence Tools
 */

public class TokenIdList implements java.io.Serializable {

	// Fields

	private Integer idTokenIdLis;
	private String tokenId;
	private Short tokenIdType;

	// Constructors

	/** default constructor */
	public TokenIdList() {
	}

	/** full constructor */
	public TokenIdList(String tokenId, Short tokenIdType) {
		this.tokenId = tokenId;
		this.tokenIdType = tokenIdType;
	}

	// Property accessors

	public Integer getIdTokenIdLis() {
		return this.idTokenIdLis;
	}

	public void setIdTokenIdLis(Integer idTokenIdLis) {
		this.idTokenIdLis = idTokenIdLis;
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Short getTokenIdType() {
		return this.tokenIdType;
	}

	public void setTokenIdType(Short tokenIdType) {
		this.tokenIdType = tokenIdType;
	}

}