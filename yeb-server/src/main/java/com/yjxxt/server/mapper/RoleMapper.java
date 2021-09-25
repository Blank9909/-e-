package com.yjxxt.server.mapper;

import com.yjxxt.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户ID获取角色的权限
     * @param adminId
     * @return
     */
    public List<Role> queryRolesByAdminId(Integer adminId);
}
