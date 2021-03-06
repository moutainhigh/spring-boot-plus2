package com.ws.ldy.starter.pay.service.impl;

import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.ws.ldy.core.result.R;
import com.ws.ldy.starter.pay.model.dto.WxEntPayDTO;
import com.ws.ldy.starter.pay.model.vo.WxEntPayResultVO;
import com.ws.ldy.starter.pay.result.WxPayRType;
import com.ws.ldy.starter.pay.service.XjEntPayService;
import com.ws.ldy.starter.pay.wxApi.EntPayApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * 企业付款相关接口
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 *
 */

@Service
@Slf4j
public class XjEntPayServiceImpl implements XjEntPayService {

    @Autowired
    private EntPayApi entPayApi;

    @Autowired
    private HttpServletRequest request;

    @Override
    public R<WxEntPayResultVO> entPay(WxEntPayDTO wxEntPayDTO) {
        if (StringUtils.isBlank(wxEntPayDTO.getOpenid())) {
            return R.error(WxPayRType.WX_PAY_NO_OPENID);
        }
        // 默认是否验证用户姓名
        if (StringUtils.isBlank(wxEntPayDTO.getCheckName())) {
            wxEntPayDTO.setCheckName("NO_CHECK");
        }
        // 默认描叙
        if (StringUtils.isBlank(wxEntPayDTO.getDescription())) {
            wxEntPayDTO.setDescription("企业打款");
        }
        EntPayRequest payRequest = new EntPayRequest();
        payRequest.setOpenid(wxEntPayDTO.getOpenid());
        payRequest.setPartnerTradeNo(wxEntPayDTO.getPartnerTradeNo());
        payRequest.setCheckName(wxEntPayDTO.getCheckName());
        payRequest.setReUserName(wxEntPayDTO.getReUserName());
        payRequest.setAmount(wxEntPayDTO.getAmount());
        payRequest.setDescription(wxEntPayDTO.getDescription());
        payRequest.setSpbillCreateIp(request.getRemoteHost());
        WxEntPayResultVO vo = new WxEntPayResultVO();
        try {
            EntPayResult result = entPayApi.entPay(payRequest);
            BeanUtils.copyProperties(result, vo);
            // return vo;
        } catch (WxPayException e) {
            return R.error(WxPayRType.WX_PAY_FAILURE.getValue(),
                    WxPayRType.WX_PAY_FAILURE.getMsg()
                            + ":" + e.getReturnMsg()
                            + ":" + e.getCustomErrorMsg()
                            + ":" + e.getErrCodeDes());
        }
        // 交易结果验证
        if (!"SUCCESS".equals(vo.getReturnCode()) || !"SUCCESS".equals(vo.getResultCode())) {
            if (!"SUCCESS".equals(vo.getReturnCode())) {
                log.info("支付签名验证/参数格式校验失败,交易号={}", vo.getPartnerTradeNo());
            } else if (!"SUCCESS".equals(vo.getResultCode())) {
                log.info("业务结果错误,交易号={}", vo.getPartnerTradeNo());
            }
            return R.error(WxPayRType.WX_PAY_FAILURE.getValue(),
                    WxPayRType.WX_PAY_FAILURE.getMsg()
                            + ":" + vo.getReturnMsg()
                            + ":" + vo.getErrCodeDes());
        }
        return R.success(vo);
    }
}
