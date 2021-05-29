package com.ws.ldy.modules.sys.gc.template;

/**
 * 搜索Html 模板配置
 */
public class LayuiSearchTemplate {


    /**
     * 搜索
     * {desc} 字段描叙
     * {id}   字段名
     * {name} 字段名
     */
    public static final String INPUT_SEARCH_PT = "    <div class=\"layui-inline\">\n" +
            "        <label class=\"layui-form-label\">{desc}:</label>\n" +
            "        <div class=\"layui-input-block\">\n" +
            "            <input type=\"text\" id=\"{id}\" name=\"{name}\" placeholder=\"请输入{desc}...\" autocomplete=\"off\" class=\"layui-input\">\n" +
            "        </div>\n" +
            "    </div>";




}
