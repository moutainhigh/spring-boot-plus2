package com.ws.ldy.others.generatecode.controller;

import com.ws.ldy.common.result.Result;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.others.generatecode.config.GenerateConfig;
import com.ws.ldy.others.generatecode.model.DsField;
import com.ws.ldy.others.generatecode.model.dto.GenerateDto;
import com.ws.ldy.others.generatecode.service.impl.GenerationSeviceImpl;
import com.ws.ldy.others.generatecode.util.GenerateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@RestController
@RequestMapping("/generate")
@Api(value = "GenerateController", tags = "代码生成器-只限于页面调用", description = BaseConstant.InterfaceType.PC_ADMIN)
public class GenerateController extends BaseController<GenerationSeviceImpl> {


    @Autowired
    private GenerationSeviceImpl generationSeviceImpl;

    /**
     *  预览代码生成 (查询预览代码，预览代码存放于File/code/src.... 目录下，前端可直接访问)
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成预览代码")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public Result<Map<String, String>> preview(@RequestBody GenerateDto generateDto) throws Exception {
        // 解析数据库字段数据
        List<Map<String, Object>> dataList = GenerateUtil.getDataAnalysis(generateDto.getData());
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources/static,
        new DsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);

        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationSeviceImplImp.lpathMap 参数
        generationSeviceImpl.buildEntity(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_ENTITY);           // 生成Entity
        generationSeviceImpl.buildVO(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_VO);  //VO             // 生成Entity
        generationSeviceImpl.buildDTO(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_DTO);  //DTO          // 生成Entity
        generationSeviceImpl.buildController(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_CONTROLLER);     // 生成Controller
        generationSeviceImpl.buildService(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE);           // 生成service
        generationSeviceImpl.buildServiceImpl(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE_IMPL);  // 生成serviceImpl
        generationSeviceImpl.buildMapper(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_DAO);                   // 生成dao
//        // html
//        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + DsField.TABLE_NAME_LOWER + "/");

        // 生成html 的txt文件预览，html会被渲染成页面
        GenerateConfig.SUFFIX = GenerateConfig.SUFFIX_TXT;
        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + DsField.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/" + GenerateConfig.MODULE_NAME + "_" + DsField.TABLE_NAME_LOWER + "_" + DsField.TABLE_NAME_LOWER);

        return Result.success(generationSeviceImpl.pathMap);
    }



    /**
     * 代码生成对应路径
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成代码")
    @RequestMapping(value = "/generateCode", method = RequestMethod.POST)
    public Result<Map<String, String>> generateCode(@RequestBody GenerateDto generateDto) {
        // 解析数据库字段数据
        List<Map<String, Object>> dataList = GenerateUtil.getDataAnalysis(generateDto.getData());
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources/static,
        new DsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);

        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationSeviceImplImp.lpathMap 参数
        generationSeviceImpl.buildEntity(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY);           // 生成Entity
        generationSeviceImpl.buildVO(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO);  //VO             // 生成Entity
        generationSeviceImpl.buildDTO(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO);  //DTO          // 生成Entity
        generationSeviceImpl.buildController(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER);     // 生成Controller
        generationSeviceImpl.buildService(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE);           // 生成service
        generationSeviceImpl.buildServiceImpl(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL);  // 生成serviceImpl
        generationSeviceImpl.buildMapper(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DAO);                   // 生成dao
        // html
        GenerateConfig.SUFFIX = GenerateConfig.SUFFIX_HTML;
        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/");

        // 额外生成html 的txt文件预览，html会被渲染成页面
//        GenerateConfig.SUFFIX_HTML = GenerateConfig.SUFFIX_TXT;
//        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + DsField.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/" + GenerateConfig.MODULE_NAME + "_" + DsField.TABLE_NAME_LOWER + "_" + DsField.TABLE_NAME_LOWER);

        return Result.success(generationSeviceImpl.pathMap);
    }



    /**
     * 代码生成路径查询(代码生成时前端确认生成路径无误后再生成代码)
     */
    @ApiOperation("代码生成路径")
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    public Result<Map<String, String>> getPath(String tableName) {
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources/static,
        new DsField(tableName, null, GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);
        Map<String, String> mapPath = new HashMap<>();
        mapPath.put("entity", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY + DsField.TABLE_NAME_UP + ".java");
        mapPath.put("vo", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO + DsField.TABLE_NAME_UP + "VO.java");
        mapPath.put("dto", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO + DsField.TABLE_NAME_UP + "DTO.java");
        mapPath.put("controller", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER + DsField.TABLE_NAME_UP + "Controller.java");
        mapPath.put("service", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE + DsField.TABLE_NAME_UP + "Service.java");
        mapPath.put("serviceImpl", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL + DsField.TABLE_NAME_UP + "ServiceImpl.java");
        mapPath.put("mapper", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DAO + DsField.TABLE_NAME_UP + "Mapper.java");

        mapPath.put("htmlMain", GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/" + DsField.TABLE_NAME_LOWER + "Main.html");
        mapPath.put("htmlAdd", GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/" + DsField.TABLE_NAME_LOWER + "Add.html");
        mapPath.put("htmlUpd", GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/" + "/" + DsField.TABLE_NAME_LOWER + "Upd.html");
        mapPath.put("index", "/page/" + GenerateConfig.MODULE_NAME + "_" + DsField.TABLE_NAME_LOWER + "_" + DsField.TABLE_NAME_LOWER);
        return Result.success(mapPath);
    }
}
