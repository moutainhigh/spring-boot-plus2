package com.ws.ldy.others.generatecode.service.impl;

import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.others.generatecode.config.GenerateConfig;
import com.ws.ldy.others.generatecode.config.SearchPtConfig;
import com.ws.ldy.others.generatecode.model.DsField;
import com.ws.ldy.others.generatecode.service.GenerationSevice;
import com.ws.ldy.others.generatecode.util.GenerateUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class GenerationSeviceImpl extends BaseIServiceImpl implements GenerationSevice {

    /**
     * 保存预览文件返回的文件地址url
     */
    public static Map<String, String> pathMap = new HashMap<>();


    /***
     * 生成实体类
     *
     * @param data  数据
     * @param this.TABLE_NAME 数据库表名
     * @param path      生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    public void buildEntity(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            // 字段名称
            String fieldName = fieldMap.get("name").toString();
            // 字段类型
            String type = fieldMap.get("type").toString();
            // 字段注释信息-->  普通注释 fields.append("\r\n    /** " + fieldMap.get("desc") + " */");
            // 字段注释信息-->  Swagger2 模式
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\")");
            // 字段名为id的 添加id主键注解, int 默认自增, bigint+varchar 默认雪花算法
            if ("id".equals(fieldName)) {
                // id生成策略
                if (type.equals("int")) {
                    fields.append("\r\n    @TableId(type = IdType.AUTO) //自增");
                } else if (type.equals("bigint")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                } else if (type.equals("varchar") || type.equals("char")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                }
            }
            // 字段对应数据库字段 ==> 处理 添加mysql 关键字映射，mysql关键字配置: GenerateConfig.KEYWORD_ARRAY
            if (Arrays.asList(GenerateConfig.KEYWORD_ARRAY).contains(fieldName)) {
                fields.append("\r\n    @TableField(value = \"`" + fieldName + "`\")");
            } else {
                fields.append("\r\n    @TableField(value = \"" + fieldName + "\")");
            }
            //字段,转为驼峰模式
            fieldName = GenerateUtil.getFieldName(fieldName);
            //字段
            JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        DsField.FIELD_ENTITYS = fields.toString();
        GenerateUtil.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        pathMap.put("entity", brBwPath.get("path").toString());
    }


    public void buildDTO(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "DTO");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String fieldName = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\")");
            JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        DsField.FIELD_ENTITYS = fields.toString();
        GenerateUtil.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        pathMap.put("DTO", brBwPath.get("path").toString());
    }

    public void buildVO(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "VO");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String fieldName = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\")");
            this.JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        DsField.FIELD_ENTITYS = fields.toString();
        GenerateUtil.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        pathMap.put("VO", brBwPath.get("path").toString());
    }


    //每一个字段(entity+dto+vo)
    private void JXModel(StringBuffer fields, String fieldName, String type) {
        //字段
        if (type.equals("int")) {
            //整数int
            fields.append("\r\n" + "    private Integer " + fieldName + ";");
        } else if (type.equals("bigint")) {
            //整数Long
            fields.append("\r\n" + "    private Long " + fieldName + ";");
        } else if (type.equals("varchar") || type.equals("char")) {
            //字符串
            fields.append("\r\n" + "    private String " + fieldName + ";");
        } else if (type.equals("text") || type.equals("longtext")) {
            //大文本、超大文本
            fields.append("\r\n" + "    private String " + fieldName + ";");
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            //时间
            fields.append("\r\n" + "    private LocalDateTime " + fieldName + ";");
        } else if (type.equals("double")) {
            //双精度小数 Double
            fields.append("\r\n" + "    private Double " + fieldName + ";");
        } else if (type.equals("float")) {
            //单精度小数 Float
            fields.append("\r\n" + "    private Float " + fieldName + ";");
        } else if (type.equals("decimal")) {
            //小数 decimal
            fields.append("\r\n" + "    private BigDecimal " + fieldName + ";");
        }
        //每生成一次换一次行
        fields.append("\r\n");
    }


    /**
     * 生成Controller层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildController(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Controller");
        // 数据拼接(所有字段)
        StringBuffer findPageParam = new StringBuffer(" ");//添加一个空，防止没有条件时空指针异常
        StringBuffer findPageMybatisPlus = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            String fieldName = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            String desc = fieldMap.get("desc").toString();
            Object search = fieldMap.get("search");
            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            //每生成一次换一次行
            findPageParam.append("\r\n");
            //每个字段前内容
            findPageParam.append("            @ApiParam(value = \"" + desc + "\",required = false) @RequestParam(required = false) ");
            //首字母大写
            String fieldNameUp = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            //字段
            if (type.equals("int")) {    // !=null StringUtils.isNotBlank(account)
                //整数int
                //方法参数
                findPageParam.append("Integer " + fieldName + ",");
                //mybatis-plus 参数
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("bigint")) {
                //整数Long
                findPageParam.append("Long " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("varchar") || type.equals("char")) {
                //字符串
                findPageParam.append("String " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(StringUtils.isNotBlank(" + fieldName + ")," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("text") || type.equals("longtext")) {
                //大文本、超大文本
                findPageParam.append("String " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(StringUtils.isNotBlank(" + fieldName + ")," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                //时间
                findPageParam.append("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\") LocalDateTime " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("double")) {
                //双精度小数 Double
                findPageParam.append("Double " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("float")) {
                //单精度小数 Float
                findPageParam.append("Float " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("decimal")) {
                //小数 decimal
                findPageParam.append("BigDecimal " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            }
            findPageMybatisPlus.append("\r\n");
        }
        // System.out.println(findPageParam.substring(0, findPageParam.length() - 1));
        // System.out.println(findPageMybatisPlus.toString()); //
        DsField.FIND_PAGE_PARAM = findPageParam.substring(0, findPageParam.length() - 1);
        DsField.FIND_PAGE_MYBATIS_PLUS = findPageMybatisPlus.toString();

        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("controller", brBwPath.get("path").toString());
    }


    /**
     * 生成Service层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildService(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Service");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("service", brBwPath.get("path").toString());
    }


    // {code1} 包名     {code2} 项目Base 文件名      Demo 实体类名

    /**
     * 生成ServiceImpl
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildServiceImpl(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "ServiceImpl");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("serviceImpl", brBwPath.get("path").toString());
    }


    /**
     * 生成Dao
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildMapper(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Mapper");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("mapper", brBwPath.get("path").toString());
    }


    /**
     * 生成Html-main 主页
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildMainHtml(List<Map<String, Object>> dataList, String path) {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Html");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        // 数据表格字段
        StringBuffer fieldStr = new StringBuffer(" ");
        // 搜索条件html
        StringBuffer SearchPtStr = new StringBuffer();
        // 搜索条件请求值url拼接
        StringBuffer SearchParamsStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            String name = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            String desc = fieldMap.get("desc").toString();
            // 数据表格内容
            fieldStr.append("\r\n                    {field: '" + name + "', title: '" + desc + "'},");
            Object search = fieldMap.get("search");
            //是否为搜索值
            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            // 搜索内容输入框
            // 1/ input  * {desc} 字段描叙  * {id}   字段名 * {name} 字段名
            SearchPtStr.append(SearchPtConfig.INPUT_PT.replace("{id}", name).replace("{name}", name).replace("{desc}", desc));
            // 搜索内容条件拼接
            SearchParamsStr.append("                params += \"&" + name + "=\" + $(\"#" + name + "\").val();");
            // 换行
            SearchPtStr.append("\r\n");
            SearchParamsStr.append("\r\n");
        }
        // 数据保存
        DsField.LAYUI_FIELDS = fieldStr.toString().substring(0, fieldStr.length() - 1);
        DsField.LAYUI_SEARCH_PT_STR = SearchPtStr.toString();
        DsField.LAYUI_SEARCH_PARAMS_STR = SearchParamsStr.toString();

        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("main", brBwPath.get("path").toString());
    }


    //code1  字段div    / demo 类名小写

    /**
     * 生成Html-Add 添加页
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildAddHtml(List<Map<String, Object>> dataList, String path) {

        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "HtmlAdd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");


        String htmlAdd = " <div class=\"layui-form-item\">\n" +
                "        <label class=\"layui-form-label\">fieldTitle</label>\n" +
                "        <div class=\"layui-input-inline\">\n" +
                "            <input type=\"text\" id=\"fieldId\" name=\"fieldName\" lay-verify=\"required\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "        </div>\n" +
                "    </div>";
        StringBuffer fieldStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            String name = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            if ("id".equals(name)) {
                continue;
            }
            String desc = fieldMap.get("desc").toString();
//            String primarykeyId = GenerateUtil.getValue(fieldMap, "primarykeyId", ""); //是否id
//            String selfGrowth = GenerateUtil.getValue(fieldMap, "selfGrowth", "");//是否自增
            //  if (!primarykeyId.equals("true")) {
            fieldStr.append("\r\n" + htmlAdd
                    .replace("fieldTitle", desc)
                    .replace("fieldId", name)
                    .replace("fieldName", name));
            // }
        }
        // 数据保存
        DsField.ADD_HTMLS = fieldStr.toString();
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // url保存
        pathMap.put("mainAdd", brBwPath.get("path").toString());
    }


    /**
     * 生成Html-Upd 修改页
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildUpdHtml(List<Map<String, Object>> dataList, String path) {

        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "HtmlUpd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String htmlAdd = " <div class=\"layui-form-item\">\n" +
                "        <label class=\"layui-form-label\">fieldTitle</label>\n" +
                "        <div class=\"layui-input-inline\">\n" +
                "            <input type=\"text\" id=\"fieldId\" name=\"fieldName\" lay-verify=\"required\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "        </div>\n" +
                "    </div>";
        StringBuffer fieldStr = new StringBuffer();
        StringBuffer echoDisplay = new StringBuffer();
        String fieldId = "";
        for (Map<String, Object> fieldMap : dataList) {
            String name = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            if ("id".equals(name)) {
                fieldId = "data.field." + name + " = parent.data." + name + ";";
            } else {
                String desc = fieldMap.get("desc").toString();
//            String primarykeyId = GenerateUtil.getValue(fieldMap, "primarykeyId", ""); //是否id
//            String selfGrowth = GenerateUtil.getValue(fieldMap, "selfGrowth", "");//是否自增
//            if (!primarykeyId.equals("true")) {
                echoDisplay.append("\r\n         $('#" + name + "').val(parent.data." + name + ");");
                fieldStr.append("\r\n" + htmlAdd
                        .replace("fieldTitle", desc)
                        .replace("fieldId", name)
                        .replace("fieldName", name));
                // } else {
            }
        }
        // 数据保存
        DsField.UPD_HTMLS = fieldStr.toString();
        DsField.UPD_BACKFILL = echoDisplay.toString();
        DsField.UPD_ID = fieldId;
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        pathMap.put("mainUpd", brBwPath.get("path").toString());
    }
}