package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.config.security.component.JwtTokenUtil;
import com.timberkito.server.mapper.AdminMapper;
import com.timberkito.server.mapper.AdminRoleMapper;
import com.timberkito.server.mapper.RoleMapper;
import com.timberkito.server.pojo.Admin;
import com.timberkito.server.pojo.AdminRole;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.pojo.Role;
import com.timberkito.server.service.IAdminService;
import com.timberkito.server.utils.AdminUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * @param username
     * @param password
     * @param code
     * @param request
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 登陆后返回token
     * @date 2021/12/11 2:10
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        // 从Session中获取验证码code
        String captcha = (String) request.getSession().getAttribute("captcha");
        // 如果Session中没有验证码
        if (StringUtils.isEmpty(captcha)) {
            log.error("No captcha in the Session [服务器Session中无验证码，可能前端未获取！]");
            captcha = "";
        }
        // 判断验证码
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码输入错误，请重新输入！");
        }

        // 登陆
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 判断密码
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        // 是否启用
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号已被禁用，请联系管理员");
        }

        // 更新 security 登陆用户对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登陆成功", tokenMap);
    }

    /**
     * @param username
     * @return com.timberkito.server.pojo.Admin
     * @author Timber.Wang
     * @describe: 根据用户名获取用户
     * @date 2021/12/11 12:35
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username", username)
        );
    }

    /**
     * @param adminId
     * @return java.util.List<com.timberkito.server.pojo.Role>
     * @author Timber.Wang
     * @describe: 根据用户ID查询角色权限
     * @date 2022-01-03 12:34 AM
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * @param keywords
     * @return java.util.List<com.timberkito.server.pojo.Admin>
     * @author Timber.Wang
     * @describe: 获取所有操作员
     * @date 2022/3/29 16:52
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        return adminMapper.getAllAdmins(adminId, keywords);
    }

    /**
     * @param adminId
     * @param rids
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 更新操作员角色
     * @date 2022/3/30 15:22
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
