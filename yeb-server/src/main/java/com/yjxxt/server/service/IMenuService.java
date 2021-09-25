package com.yjxxt.server.service;

import com.yjxxt.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blank
 * @since 2021-09-24
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> queryAllMenuByAdminId(Integer adminId);

    /**
     * 查询每个菜单拥有的角色
     * @return
     */
    List<Menu> queryMenuWithRole();
}
