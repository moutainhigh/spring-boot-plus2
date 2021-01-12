package com.ws.ldy.modules.third.baidu.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * 图片解析
 * @author 王松
 * @mail  1720696548@qq.com
 * @date  2020/1/31 0031 20:20
 * @return
 */
public interface PicParsingService {

     /**
      * 图片文字提取
      * @author 王松
      * @mail  1720696548@qq.com 
      * @date  2020/1/31 0031 20:19
      * @return com.ws.ldy.admincore.controller.vo.ResponseData
      */
     String PicTextExtract(MultipartFile file);

}
