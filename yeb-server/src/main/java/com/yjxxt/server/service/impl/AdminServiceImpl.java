package com.yjxxt.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.mapper.AdminMapper;
import com.yjxxt.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blank
 * @since 2021-09-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public Admin queryAdminByName(String username) {
        return this.getOne(new QueryWrapper<Admin>().eq("username",username));
    }

    @Override
    public Boolean updateAdminByParams(Admin admin) {
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",admin.getUsername());
        updateWrapper.eq("id",admin.getId());
        updateWrapper.set("name",admin.getName());
        return this.update(updateWrapper);
    }

}
