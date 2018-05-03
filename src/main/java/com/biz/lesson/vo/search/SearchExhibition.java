package com.biz.lesson.vo.search;

import java.sql.*;

public class SearchExhibition
{
  private Long cityId = new Long(0);
  private String name;
  private Date from;
  private Date to;
  private Integer year;

  public SearchExhibition()
  {
  }

  public Long getCityId()
  {
    return cityId;
  }

  public Date getFrom()
  {
    return from;
  }

  public String getName()
  {
    return name;
  }

  public Date getTo()
  {
    return to;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setCityId(Long cityId)
  {
    this.cityId = cityId;
  }

  public void setFrom(Date from)
  {
    this.from = from;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setTo(Date to)
  {
    this.to = to;
  }

  public void setYear(Integer year)
  {
    this.year = year;
  }

}
