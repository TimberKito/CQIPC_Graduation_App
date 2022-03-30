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
     * @param id
     * @param keywords
     * @return java.util.List<com.timberkito.server.pojo.Admin>
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:35
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
