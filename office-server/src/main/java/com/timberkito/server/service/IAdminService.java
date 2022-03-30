package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.Admin;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface IAdminService extends IService<Admin> {

    /**
     * @param username
     * @param password
     * @param code
     * @param request
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:32
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * @param username
     * @return com.timberkito.server.pojo.Admin
     * @author Timber.Wang
     * @describe: 根据用户名获取用户
     * @date 2021/12/11 12:34
     */
    Admin getAdminByUserName(String username);

    /**
     * @param adminId
     * @return java.util.List<com.timberkito.server.pojo.Role>
     * @author Timber.Wang
     * @describe: 根据用户ID查询角色权限
     * @date 2022-01-03 12:33 AM
     */
    List<Role> getRoles(Integer adminId);


    /**
     * @param keywords
     * @return java.util.List<com.timberkito.server.pojo.Admin>
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:32
     */
    List<Admin> getAllAdmins(String keywords);
}
