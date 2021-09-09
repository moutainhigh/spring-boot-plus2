package com.ws.ldy.basepay.manage.service.impl;

import com.ws.ldy.basepay.manage.mapper.PayWalletFlowMapper;
import com.ws.ldy.basepay.manage.model.dto.PayWalletFlowDTO;
import com.ws.ldy.basepay.manage.model.entity.PayWalletFlow;
import com.ws.ldy.basepay.manage.service.PayWalletFlowService;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.enums.Admin;
import com.ws.ldy.core.enums.Base;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
@Service
public class PayWalletFlowServiceImpl extends BaseIServiceImpl<PayWalletFlowMapper, PayWalletFlow> implements PayWalletFlowService {


    @Override
    public Boolean addWalletFlow(PayWalletFlowDTO dto) {
        PayWalletFlow walletFlow = dto.convert(PayWalletFlow.class);
        return this.save(walletFlow);
    }


    /**
     * 添加平台总资产流水(不计算手续费)
     * @param walletType  支付/收入
     * @param money       金额(元)
     * @param payBusiness 业务类型
     * @param orderNo     订单号
     */
    @Override
    public Boolean addPlatformWalletFlow(String orderNo,
                                         BigDecimal money,
                                         BigDecimal moneyAfter,
                                         Base.WalletType walletType,
                                         Admin.PayBusiness payBusiness) {
        PayWalletFlowDTO dto = new PayWalletFlowDTO();
        dto.setUserId("0");
        dto.setOrderNo(orderNo);
        dto.setMoney(money);
        dto.setWalletType(walletType.getValue());
        dto.setBusinessType(payBusiness.getValue());
        dto.setBusinessDesc(payBusiness.getDesc());
        dto.setMoneyAfter(moneyAfter);
        this.addWalletFlow(dto);
        return true;
    }


    /**
     * 添加用户/商家流水
     * @param userId     用户/商家id
     * @param walletType  支付/收入
     * @param money       金额(元)
     * @param payBusiness 业务类型
     * @param orderNo     订单号
     */
    @Override
    public Boolean addUserWalletFlow(String userId,
                                     String orderNo,
                                     BigDecimal money,
                                     BigDecimal moneyAfter,
                                     Base.WalletType walletType,
                                     Admin.PayBusiness payBusiness) {
        PayWalletFlowDTO dto = new PayWalletFlowDTO();
        dto.setUserId(userId);
        dto.setOrderNo(orderNo);
        dto.setMoney(money);
        dto.setMoneyAfter(moneyAfter);
        dto.setWalletType(walletType.getValue());
        dto.setBusinessType(payBusiness.getValue());
        dto.setBusinessDesc(payBusiness.getDesc());
        this.addWalletFlow(dto);
        return true;
    }


    @Override
    public BigDecimal findUserTotalAmount(String userId) {
        BigDecimal userTotalAmount = baseMapper.findUserTotalAmount(userId);
        return userTotalAmount;
    }
}
