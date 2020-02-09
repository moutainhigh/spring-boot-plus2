package com.ws.ldy.xijiaserver.xjservice.impl;

import com.ws.ldy.admincore.common.utils.BaseImg64;
import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.xijiaserver.openapiutils.OpenBaiDuGeneralBasicApi;
import com.ws.ldy.xijiaserver.xjservice.PicParsingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

@RestController
@RequestMapping("/api/pic")
@Api(tags = {"PicParsing"}, description = "图片相关")
public class PicParsingServiceImpl extends BaseController implements PicParsingService {

    @Override
    @ApiOperation("图片文字提取")
    @RequestMapping(value = "/picTextExtract", method = RequestMethod.POST)
    public ResponseData PicTextExtract(HttpServletRequest request) {
        try {
            //获得上传文件并转为流
            MultipartHttpServletRequest muRequest = (MultipartHttpServletRequest) request;
            MultipartFile images = muRequest.getFile("file");
            InputStream inputStream = images.getInputStream();
            //baseImg64编码
            String baseImg64Image = BaseImg64.getImageStrFromPath2(inputStream);
            //调用百度图片识别接口
            String data = OpenBaiDuGeneralBasicApi.startAnalysis(baseImg64Image);
            System.out.println(data);
            return ResponseData.success(data);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseData.error("500", "图片解析错误");
        }
    }
}
