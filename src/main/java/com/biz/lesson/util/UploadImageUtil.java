package com.biz.lesson.util;

import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.*;
import javax.imageio.stream.*;

import com.biz.lesson.util.*;

public class UploadImageUtil
{


  public static void createSmallImage(String sourceFileName, String smallFileName)
  {
    try
    {
      File sourceFile = new File(sourceFileName);
      BufferedImage sourceImage = ImageIO.read(sourceFile);
      File smallResult = new File(smallFileName);

      int originalHeight = sourceImage.getHeight();
      int originalWidth = sourceImage.getWidth();

      int smallHeight = 0;
      int smallWidth = 0;
      if (originalHeight <= originalWidth)
      {
        smallHeight = 150;
        smallWidth = originalWidth * 150 / originalHeight;
      }
      else
      {
        smallHeight = originalHeight * 150 / originalWidth;
        smallWidth = 150;
      }
      double heightRatio = (double) smallHeight / originalHeight;
      double widthRatio = (double) smallWidth / originalWidth;
      AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(widthRatio, heightRatio), null);
      BufferedImage smallImage = op.filter(sourceImage, null);

      Rectangle rect = new Rectangle(0, 0, 150, 150);
      Raster r = smallImage.getData(rect);
      BufferedImage newimage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
      newimage.setData(r);

      ImageIO.write(newimage, "jpg", smallResult);
    }

    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void convertGIF2JPG(String sourceFileName, String resultFileName)
  {
    try
    {
      File sourceFile = new File(sourceFileName);
      Image sourceImage = ImageIO.read(sourceFile);
      int width = sourceImage.getWidth(null);
      int height = sourceImage.getHeight(null);

      BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = resultImage.createGraphics();
      g.drawImage(sourceImage, 0, 0, width, height, null);
      g.dispose();
      ImageIO.write(resultImage, "jpg", new File(resultFileName));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void addWaterMark(String sourceFileName, String markFileName, String targetFileName)
  {
    try
    {
      File markFile = new File(markFileName);
      File sourceFile = new File(sourceFileName);
      File targetFile = new File(targetFileName);

      Image imageWaterMark = ImageIO.read(markFile);
      Image sourceImage = ImageIO.read(sourceFile);

      int originalHeight = sourceImage.getHeight(null);
      int originalWidth = sourceImage.getWidth(null);

      //添加水印
      BufferedImage resultImage = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = resultImage.createGraphics();
      g.drawImage(sourceImage, 0, 0, originalWidth, originalHeight, null);
      //水印文件

      int widthWaterMark = imageWaterMark.getWidth(null);
      int heightWaterMark = imageWaterMark.getHeight(null);
      //设置透明度
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
      //水印文件在源文件的右下角
      g.drawImage(imageWaterMark, originalWidth - widthWaterMark - 10, originalHeight - heightWaterMark - 10, widthWaterMark, heightWaterMark, null);
      g.dispose();
      ImageIO.write(resultImage, "jpg", targetFile);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }


  public static void main(String[] args)
  {

    com.biz.lesson.util.UploadImageUtil iu = new com.biz.lesson.util.UploadImageUtil();

    iu.convertGIF2JPG("c:\\1.gif", "c:\\1-gif.jpg");
    iu.createSmallImage("c:\\1-gif.jpg", "c:\\1-gif-s.jpg");
    iu.addWaterMark("c:\\1-gif.jpg", "c:\\watermark.gif", "c:\\1-gif-t.jpg");

    iu.convertGIF2JPG("c:\\png2.png", "c:\\1-png.jpg");
    iu.createSmallImage("c:\\1-png.jpg", "c:\\1-png-s.jpg");
    iu.addWaterMark("c:\\1-png.jpg", "c:\\watermark.gif", "c:\\1-png-t.jpg");

    try
    {
      String[] styles = ImageIO.getWriterFormatNames();
      for (int i = 0; i < styles.length; i++)
      {
        System.out.println((i + 1) + " : " + styles[i] + "格式.");
      }
      /*
             File inputFile = new File("c:\\1.bmp");
             BufferedImage input = ImageIO.read(inputFile);

             File outputFile = new File("c:\\test.jpg");
             ImageIO.write(input, "JPG", outputFile);
       */
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    /*
         try
         {
      BufferedImage src = ImageIO.read(new File("c:\\1.gif"));
      BufferedImage dest = new BufferedImage(src.getWidth() / 2, src.getHeight() / 2, BufferedImage.TYPE_INT_RGB);
      dest.getGraphics().drawImage(src, 0, 0, src.getWidth() / 2, src.getHeight() / 2, null);
      ImageIO.write(dest, "gif", new File("C:\\small.gif"));
         }
         catch (Exception e)
         {
      e.printStackTrace();
         }

         try
         {
      File file = new File("c:\\1100589661.jpg");
      InputStream in = new FileInputStream(file);
      BufferedImage sourceImg = ImageIO.read(file);
      System.out.println(sourceImg.getWidth());
      System.out.println(sourceImg.getHeight());
      long l = file.length();
      byte[] data = new byte[(int) l];
      int bytesRead = 0;
      int offset = 0;
      while (offset < l)
      {
        bytesRead = in.read(data, offset, data.length - offset);
        if (bytesRead == -1)break;
        offset += bytesRead;
      }
      in.close();
      String ss = (new sun.misc.BASE64Encoder()).encode(data);
      System.out.println(ss);
         }
         catch (Exception e)
         {
         }
     */

  }


}
