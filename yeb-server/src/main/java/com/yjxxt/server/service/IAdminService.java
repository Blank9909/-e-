package com.yjxxt.server.service;

import com.yjxxt.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
