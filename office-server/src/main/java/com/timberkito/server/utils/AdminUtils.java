package com.timberkito.server.utils;

import com.timberkito.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Timber
 */
public class AdminUtils {

    /**
     * @param []
     * @return com.timberkito.server.pojo.Admin
     * @author Timber.Wang
     * @describe: 获取当前所登录的用户
     * @date 2022/3/29 16:59
     */
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal();
    }

}
