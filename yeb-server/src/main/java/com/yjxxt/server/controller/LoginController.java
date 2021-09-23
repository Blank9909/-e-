package com.yjxxt.server.controller;

import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.pojo.AdminLoginParam;
import com.yjxxt.server.pojo.RespBean;
import com.yjxxt.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService iAdminService;

    @ApiOperation(value = "登录之后返回token")
    @RequestMapping("login")
    public RespBean login(AdminLoginParam adminLoginParam){
        return iAdminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword());
    }

    @ApiOperation(value = "获取当前用户信息")
    @RequestMapping("/admin/info")
    public Admin getAdmininfo(Principal principal){
        if(null==principal){
            return null;
        }

        String username = principal.getName();
        Admin admin=iAdminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }
    
    @ApiOperation(value ="退出成功")
    @RequestMapping("logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }
}
