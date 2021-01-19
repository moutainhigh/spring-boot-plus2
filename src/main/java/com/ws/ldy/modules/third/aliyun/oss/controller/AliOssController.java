package com.ws.ldy.modules.third.aliyun.oss.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.FileDownloadUtil;
import com.ws.ldy.common.utils.FileUploadUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.third.aliyun.oss.util.OSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 阿里云OSS 文件上传下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "AliOssController", tags = "AliYun --> OSS文件管理")
@RequestMapping(BaseConstant.Sys.API + "/aliOssFile")
@Slf4j
public class AliOssController extends BaseController {

    // 阿里云oss工具类
    @Autowired
    private OSSUtil ossUtil;


    // 文件保存路径地址
    private final static String UPLOAD_PATH_IMAGE = "image";  //  oss/file/image
    private final static String UPLOAD_PATH_MUSIC = "music";  //  oss/file/music
    private final static String UPLOAD_PATH_VIDEO = "video";  //  oss/file/video
    private final static String UPLOAD_PATH_EXCEL = "excel";  //  oss/file/excel
    private final static String UPLOAD_PATH_FILE = "file";    //  oss/file/file


    @RequestMapping(value = "/upload", method = RequestMethod.POST) //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true),
            @ApiImplicitParam(name = "isReduce", value = "是否压缩(默认压缩, 压缩后图片MB大小直线下降, 放大后的清晰度将下降)", required = false)
    })
    public R<String> upload(@RequestParam MultipartFile file,
                            @RequestParam("filePath") String filePath,
                            @RequestParam(required = false) Boolean isReduce) {
        // 验证文件格式及路径，并获取文件上传路径, file.getOriginalFilename()=原文件名
        String fileName = FileUploadUtil.getPath(filePath, file.getOriginalFilename());
        try {
            // 获得上传的文件流并并对图片进行压缩
            InputStream inputStream = FileUploadUtil.imgReduce(filePath, isReduce, file.getInputStream());
            // 上传到OSS,返回访问地址
            String path = ossUtil.upload(filePath, fileName, inputStream);
            return R.success(path);
        } catch (Exception e) {
            return R.error(RType.FILE_UPLOAD_FAILED);
        }

    }


    /**
     * 多文件上传
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/2 0002 8:54
     * @version 1.0.0
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST, headers = "content-type=multipart/form-data") //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true),
            @ApiImplicitParam(name = "isReduce", value = "是否压缩(默认压缩, 压缩后图片MB大小直线下降, 放大后的清晰度将下降)", required = false)
    })
    public R<List<String>> uploads(@RequestParam("file") MultipartFile[] files, @RequestParam("filePath") String filePath, @RequestParam(required = false) Boolean isReduce) {
        // 接收到的文件
        // List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        List<String> pathList = new ArrayList();
        MultipartFile file = null;
        for (int i = 0; i < files.length; ++i) {
            // 获取文件
            file = files[i];
            // 验证文件格式及路径，并获取文件上传路径, file.getOriginalFilename()=原文件名
            String fileName = FileUploadUtil.getPath(filePath, file.getOriginalFilename());
            try {
                // 获得上传的文件流并并对图片进行压缩
                InputStream inputStream = FileUploadUtil.imgReduce(filePath, isReduce, file.getInputStream());
                // 上传到OSS,返回访问地址
                String path = ossUtil.upload(filePath, fileName, inputStream);
                pathList.add(path);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(RType.FILE_UPLOAD_FAILED);
            }
        }
        return R.success(pathList);
    }


    /**
     * oss-文件列表
     */
    @ApiOperation("OSS-文件Object列表")
    @RequestMapping(value = "/fileList", method = RequestMethod.GET)
    public R<List<OSSObjectSummary>> fileList() {
        List<OSSObjectSummary> objectListing = ossUtil.getObjectListing();
        return R.success(objectListing);
    }


    /**
     * oss-文件删除
     */
    @ApiOperation("OSS-文件删除")
    @ApiImplicitParam(name = "filePath", value = "文件保存的完整可访问URL,或OSS相对路径", required = true)
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public R del(@RequestParam String filePath) {
        // 去除域名 ,获得oss存储路径
        ossUtil.deleteObject(filePath);
        return R.success();
    }

    /**
     * 网络文件下载
     */
    @ApiOperation("OSS-文件下载--单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件可访问的完整URL", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePath) {
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        // 文件下载
        FileDownloadUtil.download(filePath, fileName, response);
    }

    /**
     * 网络文件打包下载
     */
    @ApiOperation("OSS-文件下载--多文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePaths", value = "文件可访问的完整URL,多个逗号分隔", required = true),
            @ApiImplicitParam(name = "zipName", value = "下载后的文件名", required = true)
    })
    @RequestMapping(value = "/downloadZip", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePaths, @RequestParam String zipName) {
        // 文件打包下载
        FileDownloadUtil.downloadZip(Arrays.asList(filePaths.split(",")), zipName, response);
    }


}

