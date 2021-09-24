package com.yjxxt.server.config.security.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springfox.documentation.service.Header;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt 登录权限过滤器
 *
 * 1.获取头
 * 2.根据头获取token
 * 3.根据token，获取username
 * 4.判断用户是否登录
 * 5.执行登录
 * 6.设置登录信息
 * 7.过滤器放行
 */
public class JwtTokenFiler extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.获取头
        String header = request.getHeader(tokenHeader);
        //判断头不为空
        if(StringUtils.isNotBlank(header)){
            //判断是否以头开始
            if(header.startsWith(tokenHeader)){
                //2.根据头获取token
                //如果前缀是以Bearer为开头,将其分开拿到token
                String token = header.substring(tokenHead.length() + 1);
                //3.根据token，获取username,,从token中获取登录用户名
                String username = jwtTokenUtil.getUserNameFormToken(token);
                //4.判断用户是否登录
                //根据username查到数据
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //判断token是否有效
                if(jwtTokenUtil.validateToken(token,userDetails)){
                    //5.执行登录
                    //将token中的信息接收放入对象中
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //6.设置登录信息
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //放入security 环境
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        //7.过滤器放行
        filterChain.doFilter(request,response);
    }
}
