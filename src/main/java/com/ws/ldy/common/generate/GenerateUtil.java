package com.ws.ldy.common.generate;


import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.utils.JsonUtils;
import com.ws.ldy.common.utils.LocalDateTimeUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * TODO  代码生成工具类( 处理相关数据使用)
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@SuppressWarnings("all")
public class GenerateUtil extends BaseController {


    /**
     * TODO  json 数据处理成 List<Map<String, Object>> (处理代码生成前端传入数据)
     *
     * @param data
     * @param data [{ search：true }] 表示要为该字段添加搜索功能
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @date 2019/11/22 12:08
     */
    public static List<Map<String, Object>> getDataAnalysis(String data) {
        //所有字段数据处理成 List集 -->  每个字段名称，类型，描叙为 Map集
        List<Map<String, Object>> tableList = new ArrayList<>();
        List<Object> dataObjs = JsonUtils.parseList(data, null);
        dataObjs.forEach(item -> tableList.add(JsonUtils.parseMap(item.toString())));
        //System.out.println(tableList.toString());
        return tableList;
    }


    /**
     * TODO  把下化线的字段映射成驼峰模式
     *
     * @return
     * @date 2019/11/20 19:22
     */
    public static String getFieldName(String field) {
        String[] fields = field.split("_");
        if (fields.length > 1) {
            String fieldUp = fields[0];
            for (int i = 0; i < fields.length; i++) {
                if (i > 0) {
                    fieldUp += fields[i].substring(0, 1).toUpperCase() + fields[i].substring(1);
                }
            }
            return fieldUp;
        } else {
            return fields[0];
        }
    }


    /***
     * TODO  获取字段名为空处理（为空返回默认值default1）
     * @param objMap
     * @param key
     * @param default1
     * @date 2019/11/22 12:07
     * @return java.lang.String
     */
    public static String getValue(Map<String, Object> objMap, String key, String default1) {
        if (objMap.containsKey(key)) {
            return objMap.get(key).toString();
        } else {
            return default1;
        }
    }


    /**
     * TODO  判断文件路径是否存在，不存在创建
     *
     * @param path
     * @return void
     * @date 2019/11/22 15:18
     */
    public static void mkdirFile(String path) {
        // 不存在创建文件夹
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * TODO  获得BufferedReader（根据url读取模版文档），BufferedWriter （写入文件流），path生成的件路径
     *
     * @param FieldCG =  生成的路径/包名/类名等属性
     * @param path    =  代码生成路径，从父项目跟目录开始的路径
     * @param name    =  Dao，Controller， Service 等等.对应模板 DemoDao，DemoController， DemoService 等等
     * @param suffix  =  this.SUFFIX_NAME_JAVA || this.SUFFIX_NAME_HTML
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:37
     */
    public static Map<String, Object> getBrBwPath(String path, String name) {
        Map<String, Object> brBw = new HashMap<>();
        // 路径 + 类名 + name + 后缀
        String upPath = null;
        if (name.indexOf("Html") != -1) {
            upPath = path + FieldCG.TABLE_NAME_LOWER + name.replace("Html", "") + GenerateConfig.SUFFIX;
        } else {
            upPath = path + FieldCG.TABLE_NAME_UP + name + GenerateConfig.SUFFIX_JAVA;
        }
        // 检查目录,不存在添加
        mkdirFile(path);
        try {
            // 服务器模板路径（url+ 文件路径）+ 模板名称
            BufferedReader br = getUrlDetail(FieldCG.PATH_TP + "/Demo" + name + ".tp");
            BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
            brBw.put("br", br);
            brBw.put("bw", bw);
            brBw.put("path", upPath);
            //打印参数区分
            brBw.put("name", name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return brBw;
    }


    /**
     * TODO  通过url 获取文件流
     *
     * @param urlStr
     * @param withSep
     * @return java.lang.String
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:01
     */
    //获取链接地址的字符数据，wichSep是否换行标记
    public static BufferedReader getUrlDetail(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.connect();
        InputStream cin = httpConn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(cin, "UTF-8"));
        return br;
    }


    /**
     * ================================ 代码生成所有替换字段 ===========================================
     * =================================注释信息
     *  {author} ：      作者
     *  {email} ：       邮箱 || 联系方式
     *  {date} :         时间
     *  =================================原始数据
     *  {Demo} ：         类名/文件名大小（驼峰模式）
     *  {demo} ：         类名/文件名小写开头（驼峰模式）
     *  {htmlNameLower} ：html文件名称
     *  {packName} ：     生成代码的包名/路径 （从java 目录开始，如当前: com.ws.ldy.baseadmin）
     *  {tableName} ：    数据库表的实际名称
     *  {entryName} ：    项目名实际名称
     *  {entryNameUp} ：  项目名称-处理下划线(驼峰模式全大写开头)
     *  {entryNameSmall}: 项目名称-处理下划线(全转小写)
     *  {entryNameLast}:  项目名称 (下化线分隔，除第一个，拼接后面的全部单词,全小写,如只存在一个，则使用第一个)
     *  =================================代码生成方法内获得的处理数据
     *  {entitys} ：        entity 实体类所有字段数据
     *  {primary-key-type} ： 主键id 数据类型
     *  {layui-fields} ：   html主页layui数据表格所有字段数据
     *  {add-htmls} ：      html添加页，表单所有添加字段数据
     *  {upd-htmls} ：      html修改页，表单所有添加字段数据(无Id)
     *  {upd-id} ：         html修改页，添加表单id赋值
     *  {upd-backfill} ：   html修改页，打开提交回填数据赋值
     *
     */
    /**
     * TODO  代码生成所有文件数据替换工具类
     *
     * @param brBwPath
     * @param fieldCG
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/23 0023 8:12
     */
    public static void replacBrBwWritee(Map<String, Object> brBwPath) {
        // 获取到getBrBwPath 方法拼装的数据
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String line = null;
        String newLine = null;
        try {
            while ((line = br.readLine()) != null) {
                newLine = line
                        //注释信息
                        .replace("{author}", "@author  " + GenerateConfig.AUTHOR)
                        .replace("{email}", "@email  " + GenerateConfig.EMAIL)
                        .replace("{describe}", GenerateConfig.DESCRIBE)
                        .replace("{date}", "@date  " + LocalDateTimeUtils.parse(LocalDateTimeUtils.now()))
                        //原始数据
                        .replace("{tableName}", FieldCG.TABLE_NAME) //表名
                        .replace("{tableNameUp}", FieldCG.TABLE_NAME_UP)//表名大写开头驼峰
                        .replace("{tableNameLower}", FieldCG.TABLE_NAME_LOWER)//表名小写开头驼峰
                       // .replace("{htmlNameLower}", FieldCG.TABLE_NAME_LOWER) //表名小写开头驼峰
                        .replace("{packPath}", FieldCG.PACK_PATH) //包路径
                        .replace("{moduleName}", GenerateConfig.MODULE_NAME)//模块名
                        .replace("{tableComment}", FieldCG.TABLE_COMMENT)
                        //.replace("{entryName}", FieldCG.entryName)
//                    .replace("{entryNameUp}", FieldCG.entryNameUp)
//                    .replace("{entryNameLast}", FieldCG.entryNameLast)
                        //  .replace("{entryNameSmall}", FieldCG.entryNameSmall)
                        //代码生成方法内获得的处理数据
                        .replace("{entitys}", FieldCG.FIELD_ENTITYS)
                        .replace("{findPageParam}", FieldCG.FIND_PAGE_PARAM)
                        .replace("{findPageMybatisPlus}", FieldCG.FIND_PAGE_MYBATIS_PLUS)
                        // .replace("{primary-key-type}", FieldCG.PRIMARY_KEY_TYPE)
                        .replace("{layui-fields}", FieldCG.LAYUI_FIELDS)
                        .replace("{layui-search-pt-str}", FieldCG.LAYUI_SEARCH_PT_STR)
                        .replace("{layui-search-params-str}", FieldCG.LAYUI_SEARCH_PARAMS_STR)
                        //
                        .replace("{add-htmls}", FieldCG.ADD_HTMLS)
                        .replace("{upd-htmls}", FieldCG.UPD_HTMLS)
                        .replace("{upd-backfill}", FieldCG.UPD_BACKFILL)
                        .replace("{upd-id}", FieldCG.UPD_ID);
                bw.write(newLine);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(brBwPath.get("name") + " --> " + brBwPath.get("path").toString());
    }
}
