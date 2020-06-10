package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.service.impl.DataBaseServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * TODO  数据库操作类/代码生成等处理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:03
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/dataBase")
@Api(value = "DataBaseController", tags = "数据库表查询")
public class DataBaseController extends BaseController {

    @Autowired
    private DataBaseServiceImpl dataBaseServiceImpl;

    @ApiOperation("查询所有表名")
    @RequestMapping(value = "/findTable", method = RequestMethod.GET)
    public Result<List<Map<String, String>>> findTable() {
        List<Map<String, String>> tables = dataBaseServiceImpl.findTable();
        //TABLE_NAME-t_sheep_user","TABLE_COMMENT
        //转为前台需要的树结构数据
//        List<Dict> tableList = new ArrayList<>();
//        table.forEach(item -> tableList.add(Dict.create().set("name", item)));
        return success(tables);
    }

    @ApiOperation("查询指定表下使用字段内容")
    @ApiImplicitParam(name = "tableName", value = "表名", required = false, paramType = "query")
    @RequestMapping(value = "/findTableField", method = RequestMethod.GET)
    public Result<List<Map<String, String>>> findTableField(@RequestParam(required = false) String tableName) {
        List<Map<String, String>> tableField = dataBaseServiceImpl.findTableField(tableName);
        return success(tableField);
    }
}