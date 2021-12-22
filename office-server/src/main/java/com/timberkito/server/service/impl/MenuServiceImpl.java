package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.MenuMapper;
import com.timberkito.server.pojo.Admin;
import com.timberkito.server.pojo.Menu;
import com.timberkito.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService{


    @Autowired
    private MenuMapper menuMapper;

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据用户id查询菜单列表
     * @date 2021-12-22 8:20 PM
     */
    @Override
    public List<Menu> getMenusByAdminId(){
        return menuMapper.getMenusByAdminId(
                ((Admin) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getId()
        );
    }
}
