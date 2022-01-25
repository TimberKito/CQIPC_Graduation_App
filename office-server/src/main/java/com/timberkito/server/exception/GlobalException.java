package com.timberkito.server.exception;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.timberkito.server.pojo.RespBean;
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
public class GlobalException{

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * @param e
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 数据库异常处理
     * @date 2022-01-25 1:22 PM
     */
    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            logger.error("SQLIntegrityConstraintViolationException [数据库异常：此数据有关联数据！]",e);
            return RespBean.error("此数据有关联数据，操作失败！");
        }
        logger.error("数据库异常",e);
        return RespBean.error("数据库异常，操作失败！");
    }

    /**
     * @param e
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 空指针异常处理
     * @date 2022-01-25 1:44 PM
     */
    @ExceptionHandler(NullPointerException.class)
    public RespBean myNullPointerException(NullPointerException e){
        logger.error("NullPointerException [空指针异常]",e);
        return RespBean.error("服务器内部空指针异常");
    }

}
