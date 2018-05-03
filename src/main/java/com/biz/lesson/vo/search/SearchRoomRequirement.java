package com.biz.lesson.vo.search;

import java.sql.*;

public class SearchRoomRequirement
{

  private Long cityId;
  private String name;
  private String teamCode;
  private String orginalTeamCode;
  private Integer[] levels;
  private String tel;
  private String fax;
  private String address;
  private String orderId;
  private String email;
  private Date arrivalFrom;
  private Date arrivalTo;
  private Integer nights;
  private Integer[] results;

  public String getAddress()
  {
    return address;
  }

  public String getEmail()
  {
    return email;
  }

  public String getFax()
  {
    return fax;
  }

  public String getName()
  {
    return name;
  }

  public Integer getNights()
  {
    return nights;
  }

  public String getOrderId()
  {
    return orderId;
  }

  public String getTel()
  {
    return tel;
  }

  public Long getCityId()
  {
    return cityId;
  }

  public Date getArrivalFrom()
  {
    return arrivalFrom;
  }

  public Date getArrivalTo()
  {
    return arrivalTo;
  }

  public Integer[] getLevels()
  {
    return levels;
  }

  public Integer[] getResults()
  {
    return results;
  }

    public String getOrginalTeamCode() {
        return orginalTeamCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTel(String tel)
  {
    this.tel = tel;
  }

  public void setOrderId(String orderId)
  {
    this.orderId = orderId;
  }

  public void setNights(Integer nights)
  {
    this.nights = nights;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setFax(String fax)
  {
    this.fax = fax;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public void setCityId(Long cityId)
  {
    this.cityId = cityId;
  }

  public void setArrivalFrom(Date arrivalFrom)
  {
    this.arrivalFrom = arrivalFrom;
  }

  public void setArrivalTo(Date arrivalTo)
  {
    this.arrivalTo = arrivalTo;
  }

  public void setLevels(Integer[] levels)
  {
    this.levels = levels;
  }

  public void setResults(Integer[] results)
  {
    this.results = results;
  }

    public void setOrginalTeamCode(String orginalTeamCode) {
        this.orginalTeamCode = orginalTeamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }


}
