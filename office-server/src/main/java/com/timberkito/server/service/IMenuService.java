package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface IMenuService extends IService<Menu> {

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据用户id查询菜单列表
     * @date 2021-12-22 8:19 PM
     */
    List<Menu> getMenusByAdminId();

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据角色获取菜单列表
     * @date 2022-01-02 7:33 PM
     */
    List<Menu> getMenusWithRole();

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 查询所有菜单
     * @date 2022/3/28 10:42
     */
    List<Menu> getAllMenus();
}
