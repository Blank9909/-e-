package com.yjxxt.server.service.impl;

import com.yjxxt.server.pojo.Menu;
import com.yjxxt.server.mapper.MenuMapper;
import com.yjxxt.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blank
 * @since 2021-09-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenuByAdminId(Integer adminId) {
        return menuMapper.queryAllMenuByAdminId(adminId);
    }

    @Override
    public List<Menu> queryMenuWithRole() {
        return menuMapper.queryMenuWithRole();
    }
}
