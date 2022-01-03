package com.timberkito.server.exception;

import com.timberkito.server.pojo.RespBean;
import io.lettuce.core.RedisConnectionException;
import io.lettuce.core.RedisException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author Timber.Wang
 * @date 2022-01-03 3:01 PM
 */
@RestControllerAdvice
public class GlobalException{

    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("此数据有关联数据，操作失败！");
        }
        return RespBean.error("数据库操作异常，操作失败！");
    }

    @ExceptionHandler(RedisException.class)
    public RespBean myRedisException(RedisException e){
        if (e instanceof RedisConnectionException){
            return RespBean.error("Redis连接异常,操作失败！");
        }
        return RespBean.error("Redis异常，操作失败！");
    }

}
