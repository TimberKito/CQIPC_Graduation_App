package com.timberkito.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 公共返回对象
 *
 * @author Timber.Wang
 * @date 2021/12/11 1:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean{
    private long code;
    private String message;
    private Object obj;

    /**
     *
     * @param message
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 成功返回结果
     * @date 2021-12-11 7:06 PM
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     *
     * @param message
	 * @param obj
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 成功返回结果带消息
     * @date 2021-12-11 1:07 AM
     */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     *
     * @param message
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 失败返回结果
     * @date 2021-12-11 7:08 PM
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     *
     * @param message
	 * @param obj
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 失败返回结果带消息
     * @date 2021-12-11 7:09 PM
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }

}
