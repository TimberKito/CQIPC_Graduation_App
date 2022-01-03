package com.timberkito.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限控制拦截器
 * 判断用户角色
 *
 * @author Timber.Wang
 * @date 2022-01-03 12:54 AM
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager{
    @Override
    public void decide(Authentication authentication,Object o,Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException{

        for (ConfigAttribute configAttribute : collection) {
            // 当前url所需角色
            String needRole = configAttribute.getAttribute();
//            System.out.println("当前url所需权限" + configAttribute.getAttribute());
            // 判断角色是否登陆即可访问的角色，此角色在CustomFilter中设置
            if ("ROLE_LOGIN".equals(needRole)){
                // 判断是否登陆
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登陆，请登陆！");
                } else {
                    return;
                }
            }

            // 判断用户是否为url所需角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 打印登陆用户权限
//                System.out.println(authority.getAuthority());
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足！请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute){
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass){
        return false;
    }
}
