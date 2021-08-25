package com.ws.ldy.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.cache.CacheKey;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.cache.CacheUtil;
import com.ws.ldy.core.config.error.ErrorException;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.manage.xj.mapper.XjAdminConfigMapper;
import com.ws.ldy.manage.xj.model.dto.XjAdminConfigDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminConfig;
import com.ws.ldy.manage.xj.service.XjAdminConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@Service
public class XjAdminConfigServiceImpl extends BaseIServiceImpl<XjAdminConfigMapper, XjAdminConfig> implements XjAdminConfigService {


    @Override
    public XjAdminConfig findByCode(String code) {
        if (!CacheUtil.containsKey(CacheKey.CONFIG_MAP_KEY.getKey())) {
            List<XjAdminConfig> list = this.list();
            if (!list.isEmpty()) {
                Map<String, XjAdminConfig> xjAdminConfigMap = list.stream().collect(Collectors.toMap(XjAdminConfig::getCode, p -> p));
                CacheUtil.set(CacheKey.CONFIG_MAP_KEY.getKey(), xjAdminConfigMap);
            }
        }
        return CacheUtil.getMap(CacheKey.CONFIG_MAP_KEY.getKey(), XjAdminConfig.class).get(code);
    }

    @Override
    public boolean insert(XjAdminConfigDTO dto) {
        // 判code重复
        if (this.count(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, dto.getCode())) > 0) {
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        boolean b = this.save(dto.convert(XjAdminConfig.class));
        CacheUtil.del(CacheKey.CONFIG_MAP_KEY.getKey());
        return b;
    }

    @Override
    public boolean upd(String id,XjAdminConfigDTO dto) {
        XjAdminConfig config = this.getById(id);
        if (!config.getCode().equals(dto.getCode())) {
            if (this.count(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, dto.getCode())) > 0) {
                throw new ErrorException(RType.DICT_DUPLICATE);
            }
        }
        XjAdminConfig entity = dto.convert(XjAdminConfig.class);
        entity.setId(id);
        boolean b = this.updateById(entity);
        CacheUtil.del(CacheKey.CONFIG_MAP_KEY.getKey());
        return b;
    }
}
