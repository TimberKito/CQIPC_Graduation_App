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
     * @param rid
     * @param mids
     * @return java.lang.Integer
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:36
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
