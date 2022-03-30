package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.MenuRole;
import com.timberkito.server.pojo.RespBean;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface IMenuRoleService extends IService<MenuRole> {


    /**
     * @param rid
     * @param mids
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:33
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
