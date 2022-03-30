package com.timberkito.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timberkito.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @param id
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据用户id查询菜单列表
     * @date 2021-12-22 8:30 PM
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 根据角色获取菜单列表
     * @date 2022-01-02 7:37 PM
     */
    List<Menu> getMenusWithRole();

    /**
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Menu>
     * @author Timber.Wang
     * @describe: 查询所有菜单
     * @date 2022/3/28 10:44
     */
    List<Menu> getAllMenus();
}
