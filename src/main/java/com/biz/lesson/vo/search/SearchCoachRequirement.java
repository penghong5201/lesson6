package com.biz.lesson.vo.search;

import java.sql.*;

public class SearchCoachRequirement {
	private Long beginCityId;
	private Long endCityId;
	private Date fromDateBegin;
	private Date fromDateEnd;
	private Integer minDays;
	private Integer maxDays;
	private Integer minSeats;
	private Integer maxSeats;
	private String orderId;
	private String carRenterId;

	public SearchCoachRequirement() {
	}

	public Long getBeginCityId() {
		return beginCityId;
	}

	public Long getEndCityId() {
		return endCityId;
	}

	public Date getFromDateBegin() {
		return fromDateBegin;
	}

	public Date getFromDateEnd() {
		return fromDateEnd;
	}

	public Integer getMaxDays() {
		return maxDays;
	}

	public Integer getMaxSeats() {
		return maxSeats;
	}

	public Integer getMinDays() {
		return minDays;
	}

	public Integer getMinSeats() {
		return minSeats;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setBeginCityId(Long beginCityId) {
		this.beginCityId = beginCityId;
	}

	public void setEndCityId(Long endCityId) {
		this.endCityId = endCityId;
	}

	public void setFromDateBegin(Date fromDateBegin) {
		this.fromDateBegin = fromDateBegin;
	}

	public void setFromDateEnd(Date fromDateEnd) {
		this.fromDateEnd = fromDateEnd;
	}

	public void setMaxDays(Integer maxDays) {
		this.maxDays = maxDays;
	}

	public void setMaxSeats(Integer maxSeats) {
		this.maxSeats = maxSeats;
	}

	public void setMinDays(Integer minDays) {
		this.minDays = minDays;
	}

	public void setMinSeats(Integer minSeats) {
		this.minSeats = minSeats;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCarRenterId() {
		return carRenterId;
	}

	public void setCarRenterId(String carRenterId) {
		this.carRenterId = carRenterId;
	}

}
