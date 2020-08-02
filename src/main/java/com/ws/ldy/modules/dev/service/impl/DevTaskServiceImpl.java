package com.ws.ldy.modules.dev.service.impl;

import com.ws.ldy.modules.dev.mapper.DevTaskMapper;
import com.ws.ldy.modules.dev.model.entity.DevTask;
import com.ws.ldy.modules.dev.service.DevTaskService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *   开发任务
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 11:14:57
 */
@Service
public class DevTaskServiceImpl extends BaseIServiceImpl<DevTaskMapper, DevTask> implements DevTaskService {


    @Override
    public List<DevTask> findList() {
        return baseMapper.findList();
    }
}
