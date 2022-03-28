package com.timberkito.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timberkito.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * @param [rid, mids]
     * @return void
     * @author Timber.Wang
     * @describe: 批量更新角色菜单
     * @date 2022/3/28 11:20
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
