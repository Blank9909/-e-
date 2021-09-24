package com.yjxxt.server.mapper;

import com.yjxxt.server.pojo.Admin;
import com.yjxxt.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Blank
 * @since 2021-09-24
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> queryAllMenuByAdminId(Integer adminId);
}
