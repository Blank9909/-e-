package com.yjxxt.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 用户角色权限
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();

        // configAttributes:菜单对应的角色列表
        for (ConfigAttribute configAttribute : configAttributes) {
            String role= configAttribute.getAttribute();
            if(role.equals("ROLE_LOGIN")){
                if(authentication instanceof AnonymousAuthenticationToken){
                    System.out.println("用户未登录!");
                    throw new AccessDeniedException("用户未登录!");
                }
                return;
            }
            for (GrantedAuthority authority:authorities){
                if(authority.getAuthority().equals(role)){
                    System.out.println("用户存在访问菜单权限");
                    return;
                }
            }
        }
        throw new AccessDeniedException("用户暂无权限,请联系管理员!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
