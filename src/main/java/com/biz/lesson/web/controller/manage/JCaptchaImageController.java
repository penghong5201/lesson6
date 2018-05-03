package com.biz.lesson.web.controller.manage;

import com.biz.lesson.web.controller.BaseController;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;



@Controller
@RequestMapping("/captcha")
public class JCaptchaImageController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required=false)
    private ImageCaptchaService imageCaptchaService;

    @RequestMapping
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            String captchaId = request.getSession().getId();
            BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());

            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.setContentType("image/jpeg");

            ImageIO.write(challenge, "jpeg", jpegOutputStream);
            byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

            ServletOutputStream respOs = response.getOutputStream();
            respOs.write(captchaChallengeAsJpeg);
            respOs.flush();
            respOs.close();
        } catch (IOException e) {
            logger.error("generate captcha image error: {}", e.getMessage());
        }
    }

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    @ResponseBody
    public boolean validateCode(@RequestParam("inputCode") String inputCode,HttpServletRequest request) throws Exception{
        try {
           return imageCaptchaService.validateResponseForID(request.getSession().getId(),inputCode);
        }catch (Exception ex){
            return false;
        }
    }
}
