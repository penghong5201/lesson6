package com.biz.lesson.util;

import java.math.BigDecimal;

public class RoundRole
{
  public RoundRole()
  {
  }

  public static BigDecimal round5(BigDecimal price)
  {
    int i = price.movePointRight(2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    if (i % 100 != 0)
    {
      i = (i / 100) * 100 + (i % 100 <= 50 ? 50 : 100);
    }
    return (new BigDecimal(Integer.toString(i))).movePointLeft(2);
  }


  public static void main(String[] args)
  {
    System.out.println(round5(new BigDecimal("11.999")));
    System.out.println(round5(new BigDecimal("12.00")));
    System.out.println(round5(new BigDecimal("12.001")));
    System.out.println(round5(new BigDecimal("12.34")));
    System.out.println(round5(new BigDecimal("12.50")));
    System.out.println(round5(new BigDecimal("12.501")));
    System.out.println(round5(new BigDecimal("12.601")));
    System.out.println(round5(new BigDecimal("12.99")));
    System.out.println(round5(new BigDecimal("12.992")));
  }



}
