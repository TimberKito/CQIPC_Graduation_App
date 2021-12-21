package com.timberkito.server.controller;

import com.timberkito.server.pojo.Admin;
import com.timberkito.server.pojo.AdminLoginParam;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Timber.Wang
 * @date 2021/12/11 1:52
 */
@Api(tags = "LoginController")
@RestController
public class LoginController{

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登陆之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam,HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "获取当前登陆用户信息")
    @PostMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (null == principal){
            return null;
        }
        String username = principal.getName();
        // 通过 username 获取用户实体
        Admin admin = adminService.getAdminByUserName(username);
        // 设置返回密码为空
        admin.setPassword(null);
        return admin;
    }

    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }

}
