package com.biz.lesson.vo.search;


public interface SearchTemplate
{
  public Long getVisaCountryId();
  public Integer getMinDays();
  public Integer getMaxDays();
  public String getName();
  public Long[] getCityIds();
  public Long[] getCountryIds();
  public Integer getCategory();

}
