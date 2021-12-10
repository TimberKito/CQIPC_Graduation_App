package com.timberkito.server.controller;

import com.timberkito.server.pojo.AdminLoginParam;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }
}
