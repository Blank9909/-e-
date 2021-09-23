package com.yjxxt.server.service;

import com.yjxxt.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjxxt.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blank
 * @since 2021-09-22
 */
public interface IAdminService extends IService<Admin> {
    Admin queryAdminByName(String username);

    Boolean updateAdminByParams(Admin admin);

    /**
     * 登录返回token
     * @param username
     * @param password
     * @return
     */
    RespBean login(String username,String password,String code);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);
}
