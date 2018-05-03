package com.biz.lesson.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * User: jerry
 * Date: 2004-11-21
 * Time: 13:19:33
 */
public class SequenceUtil
{
  private static Map map = new HashMap();

  public static synchronized int getSequence(String key)
  {
    int result = 0;
    Integer current = null;
    if(map.get(key)==null)
    {
      current = new Integer(1);
    }
    else
    {
      current = (Integer)map.get(key);
    }
    result = current.intValue();
    map.put(key,new Integer(result+1));
    return result;
  }

  public static void reset()
  {
    Iterator it = map.keySet().iterator();
    while(it.hasNext())
    {
      String key = (String)it.next();
      map.put(key,new Integer(1));
    }
  }

}
