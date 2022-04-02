package com.timberkito.server.exception;


import com.timberkito.server.pojo.RespBean;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 优雅的全局异常处理
 *
 * @author Timber.Wang
 * @date 2022-01-03 3:01 PM
 */
@RestControllerAdvice
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 数据库异常处理
     *
     * @param e
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022-01-25 1:22 PM
     */
    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            logger.error("SQLIntegrityConstraintViolationException [数据库异常：此数据有关联数据！]", e);
            return RespBean.error("此数据有关联数据，操作失败！");
        }
        logger.error("数据库异常", e);
        return RespBean.error("数据库异常，操作失败！");
    }

    /**
     * 空指针异常处理
     *
     * @param e
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022-01-25 1:44 PM
     */
    @ExceptionHandler(NullPointerException.class)
    public RespBean myNullPointerException(NullPointerException e) {
        logger.error("NullPointerException [空指针异常]", e);
        return RespBean.error("服务器内部空指针异常");
    }

    /**
     * 权限认证异常
     *
     * @param e
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022/3/26 19:46
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public RespBean jwtAuthenticationException(ExpiredJwtException e) {
        logger.error("ExpiredJwtException [权限认证异常]", e);
        return RespBean.error("权限认证异常,请先登录！");
    }

}
