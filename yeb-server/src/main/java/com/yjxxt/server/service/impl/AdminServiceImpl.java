package com.yjxxt.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yjxxt.server.config.security.component.JwtTokenUtil;
import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.mapper.AdminMapper;
import com.yjxxt.server.pojo.RespBean;
import com.yjxxt.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blank
 * @since 2021-09-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private HttpSession httpSession;

    @Override
    public Admin queryAdminByName(String username) {
        return this.getOne(new QueryWrapper<Admin>().eq("username",username));
    }

    @Override
    public Boolean updateAdminByParams(Admin admin) {
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",admin.getUsername());
        updateWrapper.eq("id",admin.getId());
        updateWrapper.set("name",admin.getName());
        return this.update(updateWrapper);
    }


    /**
     * 1.参数校验
     *      验证码校验：code 非空 与会话中验证码一致
     *      username password非空
     * 2.用户记录存在查询
     *      根据用户名查询记录
     *      用户不存在 方法返回用户不存在结果
     * 3.密码比对
     * 4.生成token 返回
     */
    @Override
    public RespBean login(String username, String password,String code) {

        /*1.参数校验*/
        //验证码校验，code非空，并与话中的验证码一致
        if(StringUtils.isBlank(code)){
            return RespBean.error("请输入验证码");
        }
        //从会话中获取code
        String sessionCode= (String) this.httpSession.getAttribute("code");
        //判断验证码是否与会话中的一致
        if(sessionCode==null||!sessionCode.equals(code)){
            return RespBean.error("验证码已过期或输入错误");
        }
        //移除验证码
        this.httpSession.removeAttribute("code");

        if(StringUtils.isBlank(username)){
            return RespBean.error("请输入用户名");
        }
        if(StringUtils.isBlank(password)){
            return RespBean.error("请输入密码");
        }
        /*2.用户记录存在查询*/
        //根据用户名查询记录
        //重写userDetailsService-->loadUserByUsername
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        /*密码比对*/
        if(!(passwordEncoder.matches(password,userDetails.getPassword()))){
            return RespBean.error("用户名或密码错误！");
        }
        /*生成token并返回*/
        String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return RespBean.success("登录成功。",map);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        return this.getOne(new QueryWrapper<Admin>().eq("username",username));
    }

}
