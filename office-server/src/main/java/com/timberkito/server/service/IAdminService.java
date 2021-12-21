package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.Admin;
import com.timberkito.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface IAdminService extends IService<Admin>{

    /**
     * @param username
     * @param password
     * @param code
     * @param request
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 登陆后返回 token
     * @date 2021/12/11 2:09
     */
    RespBean login(String username,String password,String code,HttpServletRequest request);

    /**
     * @param username
     * @return com.timberkito.server.pojo.Admin
     * @author Timber.Wang
     * @describe: 根据用户名获取用户
     * @date 2021/12/11 12:34
     */
    Admin getAdminByUserName(String username);
}
