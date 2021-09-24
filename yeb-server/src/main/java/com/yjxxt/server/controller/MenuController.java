package com.yjxxt.server.controller;


import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.pojo.Menu;
import com.yjxxt.server.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Blank
 * @since 2021-09-24
 */
@Api(tags = "菜单模块")
@RestController
@RequestMapping("/system")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @GetMapping("menu")
    @ApiOperation(value = "根据用户ID获取菜单列表")
    public List<Menu> queryAllMenuByAdminId(Authentication authentication){
        Admin admin= (Admin) authentication.getPrincipal();
        return menuService.queryAllMenuByAdminId(admin.getId());
    }
}
