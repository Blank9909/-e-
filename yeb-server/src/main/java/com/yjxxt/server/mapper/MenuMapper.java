package com.yjxxt.server.mapper;

import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Blank
 * @since 2021-09-24
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查询用户登录用户拥有的菜单
     * @param adminId
     * @return
     */
    List<Menu> queryAllMenuByAdminId(Integer adminId);

    /**
     * 查询每一个菜单拥有的角色
     * @return
     */
    List<Menu> queryMenuWithRole();
}
