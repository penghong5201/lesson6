package com.biz.lesson.vo.search;

import java.sql.*;

public class SearchTripGuide
{
  private Long cityId;

  private Long beginCityId;
  private Long endCityId;
  private Date fromDateBegin;
  private Date fromDateEnd;
  private Integer minDays;
  private Integer maxDays;
  private Integer minPersonCount;
  private Integer maxPersonCount;
  private String orderId;
  private Boolean isDriverGuide;


  public SearchTripGuide()
  {
  }

  public Long getBeginCityId()
  {
    return beginCityId;
  }

  public Long getCityId()
  {
    return cityId;
  }

  public Long getEndCityId()
  {
    return endCityId;
  }

  public Date getFromDateBegin()
  {
    return fromDateBegin;
  }

  public Date getFromDateEnd()
  {
    return fromDateEnd;
  }

  public Boolean getIsDriverGuide()
  {
    return isDriverGuide;
  }

  public Integer getMaxDays()
  {
    return maxDays;
  }

  public Integer getMaxPersonCount()
  {
    return maxPersonCount;
  }

  public Integer getMinDays()
  {
    return minDays;
  }

  public Integer getMinPersonCount()
  {
    return minPersonCount;
  }

  public String getOrderId()
  {
    return orderId;
  }

  public void setOrderId(String orderId)
  {
    this.orderId = orderId;
  }

  public void setMinPersonCount(Integer minPersonCount)
  {
    this.minPersonCount = minPersonCount;
  }

  public void setMinDays(Integer minDays)
  {
    this.minDays = minDays;
  }

  public void setMaxPersonCount(Integer maxPersonCount)
  {
    this.maxPersonCount = maxPersonCount;
  }

  public void setMaxDays(Integer maxDays)
  {
    this.maxDays = maxDays;
  }

  public void setIsDriverGuide(Boolean isDriverGuide)
  {
    this.isDriverGuide = isDriverGuide;
  }

  public void setFromDateEnd(Date fromDateEnd)
  {
    this.fromDateEnd = fromDateEnd;
  }

  public void setFromDateBegin(Date fromDateBegin)
  {
    this.fromDateBegin = fromDateBegin;
  }

  public void setEndCityId(Long endCityId)
  {
    this.endCityId = endCityId;
  }

  public void setCityId(Long cityId)
  {
    this.cityId = cityId;
  }

  public void setBeginCityId(Long beginCityId)
  {
    this.beginCityId = beginCityId;
  }

}
