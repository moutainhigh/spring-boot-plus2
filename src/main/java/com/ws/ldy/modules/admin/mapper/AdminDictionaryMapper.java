package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminDictionaryMapper extends BaseMapper<AdminDictionary> {


    /***
     *   查询字典表 数据，基本最多两层，  A --> B,C  -->  B1,B2,B3,C1,C2  = 返回list数据
     * @param code
     * @date 2020/7/12 0012 19:41
     * @return java.util.List<com.ws.ldy.admin.model.entity.AdminDictionary>
     */
    @Select("  select * from t_admin_dictionary " +
            "    where code =#{code} " +
            "    or pid=(select id from t_admin_dictionary where code =#{code}) " +
            "    or pid in(select id from t_admin_dictionary where pid=(select id from t_admin_dictionary where code=#{code}))")
    AdminDictionaryVO findCode(@Param("code") String code);
}
