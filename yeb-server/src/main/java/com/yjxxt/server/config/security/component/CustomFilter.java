package com.yjxxt.server.config.security.component;

import com.yjxxt.server.pojo.Menu;
import com.yjxxt.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation= (FilterInvocation) object;
        //用户访问的菜单
        String url = filterInvocation.getRequestUrl();
        //所有菜单记录（包含角色）
        List<Menu> menuList=menuService.queryMenuWithRole();
        for (Menu menu : menuList) {
            if (antPathMatcher.match(menu.getUrl(), url)) {
                System.out.println("菜单匹配成功--->" + url);
                //获取菜单对应的角色
                String[] str = menu.getRoles().stream().map(r -> r.getName()).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }
        }
        //如果没有匹配成功，用户角色为登录角色即可
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
