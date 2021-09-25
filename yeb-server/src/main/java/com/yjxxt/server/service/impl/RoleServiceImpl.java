package com.yjxxt.server.service.impl;

import com.yjxxt.server.pojo.Role;
import com.yjxxt.server.mapper.RoleMapper;
import com.yjxxt.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> queryRolesByAdminId(Integer adminId) {
        return this.baseMapper.queryRolesByAdminId(adminId);
    }
}
