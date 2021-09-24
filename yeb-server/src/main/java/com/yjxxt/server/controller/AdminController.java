package com.yjxxt.server.controller;


import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Blank
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "用户管理模块")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/{id}")
    @ApiOperation(value = "用户详情查询")
    public Admin findAdminById(@PathVariable Integer id){
        return iAdminService.getById(id);
    }

    @GetMapping("username/{username}")
    @ApiOperation(value = "根据用户姓名查询用户详情")
    public Admin findAdminByName(@PathVariable String username){
        return iAdminService.queryAdminByUserName(username);
    }

    @PutMapping("add")
    @ApiOperation(value = "保存用户记录")
    public void addAdmin(Admin admin){
        iAdminService.save(admin);
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "删除用户记录")
    public void removeAdmin(Integer id){
        iAdminService.removeById(id);
    }

    @PostMapping("change")
    @ApiOperation(value = "多条件更新用户记录")
    public void changeAdmin(Admin admin){
        iAdminService.updateAdminByParams(admin);
    }

    @PostMapping("changeAdminById")
    @ApiOperation(value = "更新用户记录")
    public void changeAdminById(Admin admin){
        iAdminService.updateById(admin);
    }

    @GetMapping
    @ApiOperation(value = "用户详情查询")
    public Admin info(Authentication authentication){
        return (Admin) authentication.getPrincipal();
    }
}
