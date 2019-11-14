package com.ws.ldy.admincore.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseControllerApi {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

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
        }else {
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
     * 向request放入对象
     *
     * @param name
     * @param value
     */
    public void setAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }
}
