package com.biz.lesson.vo.search;

import java.sql.*;

public class SearchRoomReservation {

    private Long cityId;
    private String name;
    private Integer[] levels;
    private String tel;
    private String fax;
    private String address;
    private String orderId;
    private String email;
    private Date arrivalFrom;
    private Date arrivalTo;
    private Integer nights;
    private Integer[] status;
    private String orderBy;
    private Integer[] tripStatus;
	private String confirmUserId;
	private String creatorUserId;
    private Date optionalFrom;
    private Date optionalTo;	
    private String agentId;
	
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public String getName() {
        return name;
    }

    public Integer getNights() {
        return nights;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTel() {
        return tel;
    }

    public Long getCityId() {
        return cityId;
    }

    public Date getArrivalFrom() {
        return arrivalFrom;
    }

    public Date getArrivalTo() {
        return arrivalTo;
    }

    public Integer[] getLevels() {
        return levels;
    }

    public Integer[] getStatus() {
        return status;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setArrivalFrom(Date arrivalFrom) {
        this.arrivalFrom = arrivalFrom;
    }

    public void setArrivalTo(Date arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public void setLevels(Integer[] levels) {
        this.levels = levels;
    }

    public void setStatus(Integer[] status) {
        this.status = status;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

	public Integer[] getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(Integer[] tripStatus) {
		this.tripStatus = tripStatus;
	}

	public String getConfirmUserId() {
		return confirmUserId;
	}

	public void setConfirmUserId(String confirmUserId) {
		this.confirmUserId = confirmUserId;
	}

	public Date getOptionalFrom() {
		return optionalFrom;
	}

	public void setOptionalFrom(Date optionalFrom) {
		this.optionalFrom = optionalFrom;
	}

	public Date getOptionalTo() {
		return optionalTo;
	}

	public void setOptionalTo(Date optionalTo) {
		this.optionalTo = optionalTo;
	}

	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}


}
