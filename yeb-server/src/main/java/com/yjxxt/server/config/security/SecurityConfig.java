package com.yjxxt.server.config.security;

import com.yjxxt.server.config.security.component.JwtTokenFiler;
import com.yjxxt.server.config.security.component.YebAccessDeniedHandler;
import com.yjxxt.server.config.security.component.YebAuthenticationEntryPoint;
import com.yjxxt.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IAdminService iAdminService;

    @Autowired
    private YebAccessDeniedHandler yebAccessDeniedHandler;

    @Autowired
    private YebAuthenticationEntryPoint yebAuthenticationEntryPoint;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源
        web.ignoring().antMatchers(
                "/css/**",
                "/js/**",
                "/images/**",
                "/doc.html",
                "/login",
                "/logout",
                "/index.html",
                "/favicon.ico",
                "/webjars/**",
                "/captcha",
                "/swagger-resources/**",
                "/v2/api-docs/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //禁用session创建
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/logout").permitAll()
                .anyRequest().authenticated();

        //添加过滤器 在UsernamePassWordAuthenticationFilter 执行前执行
        http.addFilterBefore(jwtTokenFiler(), UsernamePasswordAuthenticationFilter.class);

        //用户未登录或权限不足
        http.exceptionHandling()
                .accessDeniedHandler(yebAccessDeniedHandler)
                .authenticationEntryPoint(yebAuthenticationEntryPoint);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails userDetails=iAdminService.queryAdminByUserName(username);
                if(userDetails==null){
                    throw new UsernameNotFoundException("用户记录不存在！");
                }
                return userDetails;
            }
        };
    }

    @Bean
    public JwtTokenFiler jwtTokenFiler(){
        return new JwtTokenFiler();
    }

}
