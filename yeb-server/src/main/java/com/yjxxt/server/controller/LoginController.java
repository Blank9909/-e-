package com.yjxxt.server.controller;

import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.pojo.AdminLoginParam;
import com.yjxxt.server.pojo.RespBean;
import com.yjxxt.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Autowired
    private IAdminService iAdminService;

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam){
        return iAdminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode());
    }


    /*@GetMapping("/admin/info")
    @ApiOperation(value = "获取当前用户信息")
    public Admin getAdminInfo(Principal principal){
        if(null==principal){
            return null;
        }

        String username = principal.getName();
        Admin admin=iAdminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }
    

    @PostMapping("logout")
    @ApiOperation(value ="退出成功")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }*/

}
