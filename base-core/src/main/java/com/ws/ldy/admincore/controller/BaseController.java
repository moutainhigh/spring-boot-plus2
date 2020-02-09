package com.ws.ldy.admincore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class BaseController {

    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected RestTemplate restTemplate;

    /**
     * 获取页面字符串
     *
     * @param name
     * @param defalut
     * @return String
     */
    public String getString(String name, String defalut) {
        String str = request.getParameter(name);
        if (str == null) {
            return defalut;
        } else {
            return str;
        }
    }

    /**
     * 获取整数对象
     *
     * @param name
     * @param defalut
     * @return Integer
     */
    public Integer getInt(String name, Integer defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }

    /**
     * 获取整数对象
     *
     * @param name
     * @param defalut
     * @return Long
     */
    public Long getLong(String name, Long defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Long.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }

    /**
     * 获取duoble对象
     *
     * @param name
     * @param defalut
     * @return Double
     */
    public Double getDoule(String name, Double defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Double.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }

    /**
     * 获取时间对象
     *
     * @param name
     * @param defalut
     * @param format
     * @return Date
     */
    public Date getDate(String name, Date defalut, String format) {
        String str = request.getParameter(name);
        if (str == null) {
            return defalut;
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return defalut;
            }
        }
    }

    /**
     * 获取boolean对象
     *
     * @param name
     * @param defalut
     * @return boolean
     */
    public boolean getBoolean(String name, boolean defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Boolean.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }


    /**
     * TODO  获取项目部署后的classpath目录
     *
     * @return java.lang.String
     * @date 2019/11/21 10:01
     */
    public String getPath() {
        // 获取项目跟目录
        String path = "";
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * TODO  获取当前项目的父级硬盘目录 --> 如当前：D:\workSpace\tool1\code\spring-boot-plus2
     *
     * @return java.lang.String
     * @date 2019/11/21 10:02
     */
    public String getPathFather(String entryName) {
        // 获取项目跟目录
        String path = "";
        try {
            //D:\workSpace\tool1\code\spring-boot-plus2\项目名\target\classes
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String upPath = path.replace("/target/classes", "")
                .replace("/target/admin-console.jar!/BOOT-INF/admin-console", "")
                .replace("\\target\\admin-console.jar!\\BOOT-INF\\admin-console", "");
        System.out.println(upPath.length());
        int index = upPath.substring(0, upPath.length() - 1).lastIndexOf("/");
        String newUpPath = upPath.substring(0, index) + "/";
        return newUpPath;
    }
}
