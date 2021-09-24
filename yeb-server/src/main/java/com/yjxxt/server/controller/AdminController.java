package com.yjxxt.server.controller;


import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/{id}")
    public Admin findAdminById(@PathVariable Integer id){
        return iAdminService.getById(id);
    }

    @GetMapping("username/{username}")
    public Admin findAdminByName(@PathVariable String username){
        return iAdminService.queryAdminByUserName(username);
    }

    @RequestMapping("add")
    public void addAdmin(Admin admin){
        iAdminService.save(admin);
    }

    @RequestMapping("remove")
    public void removeAdmin(Integer id){
        iAdminService.removeById(id);
    }

    @RequestMapping("change")
    public void changeAdmin(Admin admin){
        iAdminService.updateAdminByParams(admin);
    }

    @RequestMapping("changeAdminById")
    public void changeAdminById(Admin admin){
        iAdminService.updateById(admin);
    }

}
