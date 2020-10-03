package com.ws.ldy.others.kuaidi.kuaidi100.service;

import com.ws.ldy.others.kuaidi.kuaidi100.entity.KuaiDiCode;

import java.util.List;

/**
 * 快递100 相关操作
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/16 0016 10:26
 * @version 1.0.0
 */
public interface KuaiDi100Service {


    /**
     * 获取快递信息
     * @param orderId
     */
    public String findOrder(String orderId);

    /**
     * 获取快递公司编号信息
     * @param orderId
     * @return
     */
    public List<KuaiDiCode> findKuaiDiCode(String orderId);
}
