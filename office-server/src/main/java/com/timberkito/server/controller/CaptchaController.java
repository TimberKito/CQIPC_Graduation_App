package com.timberkito.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 *
 * @author Timber.Wang
 * @date 2021-12-21 3:52 PM
 */
@RestController
public class CaptchaController{

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "生成验证码")
    @GetMapping(value = "/captcha", produces = "image/jpeg")
    public void captcha(HttpServletRequest request,HttpServletResponse response){
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires",0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control","post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma","no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        // 生成验证码
        // 获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容：" + text);
        // 将验证码文本内容放入session
        request.getSession().setAttribute("captcha",text);
        // 根据文本验证码创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);

        // 打开输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            // 输出流 输出图片，格式为 jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
