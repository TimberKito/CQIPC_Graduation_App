package com.timberkito.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timberkito.server.pojo.Role;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @param adminId
     * @return java.util.List<com.timberkito.server.pojo.Role>
     * @author Timber.Wang
     * @describe: 根据用户ID查询角色权限
     * @date 2022-01-03 12:40 AM
     */
    List<Role> getRoles(Integer adminId);
}
