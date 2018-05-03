package com.biz.lesson.util;

import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

public class ShtmlCreator
{
  protected ShtmlCreator()
  {
  }

  public static void create(String url, String jsp, HttpServletRequest request)
  {
    jsp = "http://" + request.getServerName() + ":" + request.getServerPort() + jsp;
    ServletContext servletContext = request.getSession().getServletContext();
    String fileName = servletContext.getRealPath("/b2c"+url);
    try
    {
      java.io.InputStream l_urlStream;
      java.net.URL l_url = new java.net.URL(jsp);
      java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();
      l_connection.connect();
      l_urlStream = l_connection.getInputStream();

      File fileOut = new File(fileName);
      File parent = fileOut.getParentFile();
      if (!parent.exists())
        parent.mkdirs();
      FileOutputStream outStream = new FileOutputStream(fileOut);
      byte[] bytes = new byte[1024];
      int c;
      while ((c = l_urlStream.read(bytes)) != -1)
      {
        outStream.write(bytes, 0, c);
      }
      l_urlStream.close();
      outStream.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("not found " + jsp);
    }
  }

  public static void main(String[] args)
  {
    ShtmlCreator shtmlcreator = new ShtmlCreator();
  }
}
