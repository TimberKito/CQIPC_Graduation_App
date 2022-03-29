package com.timberkito.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timberkito.server.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * @param [id, keywords]
     * @return java.util.List<com.timberkito.server.pojo.Admin>
     * @author Timber.Wang
     * @describe: 获取所有操作员
     * @date 2022/3/29 17:03
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
