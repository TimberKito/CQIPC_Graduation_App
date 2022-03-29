package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.MenuMapper;
import com.timberkito.server.pojo.Menu;
import com.timberkito.server.service.IMenuService;
import com.timberkito.server.utils.AdminUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据用户id查询菜单列表
     * @date 2021-12-22 8:20 PM
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        //从SpringSecurity中获取登陆用户id
        Integer adminId = AdminUtils.getCurrentAdmin().getId();

        try {
            // 实例化redis对象
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            // 从redis获取菜单数据
            List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
            // 如果redis返回为空
            if (CollectionUtils.isEmpty(menus)) {
                // 从数据库中获取数据
                menus = menuMapper.getMenusByAdminId(adminId);
                // 将数据设置到redis中
                valueOperations.set("menu_" + adminId, menus);
            }
            return menus;
        } catch (Exception e) {
            // 如果redis出现异常
            log.error("Redis server exception [Redis服务异常]", e);
            // 直接从数据库中获取数据
            return menuMapper.getMenusByAdminId(adminId);
        }

    }

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据角色获取菜单列表
     * @date 2022-01-02 7:34 PM
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 查询所有菜单
     * @date 2022/3/28 10:43
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
