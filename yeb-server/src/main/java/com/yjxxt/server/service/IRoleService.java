package com.yjxxt.server.service;

import com.yjxxt.server.pojo.Role;
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
public interface IRoleService extends IService<Role> {

    public List<Role> queryRolesByAdminId(Integer adminId);
}
