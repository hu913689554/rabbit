package com.hqm.rabbit.mapper;

import com.hqm.rabbit.domain.entity.SysMuen;
import com.hqm.rabbit.domain.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @作者 胡勤明
 * @时间 2022-01-10 22:09
 * @版本 1.0
 */
@Mapper
public interface MenuMapper {

    /**
     * 创建人 胡勤明
     * 创建时间 2022/2/08 10:44
     * 方法作用 查询权限
     */
    public List<Map<String,Object>> getUserMenu(String username);
    public List<SysMuen> getUserMenus(String username);


}
