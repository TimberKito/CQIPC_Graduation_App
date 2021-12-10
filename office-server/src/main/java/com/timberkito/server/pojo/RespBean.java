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

    /*
     * @Param: message
     * @return:
     * @author: Timber.Wang
     * @describe: 成功返回结果
     * @date 2021/12/11 1:33
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /*
     * @Param: message
     * @Param: obj
     * @return:
     * @author: Timber.Wang
     * @describe: 成功返回结果
     * @date 2021/12/11 1:35
     */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /*
     * @Param: message
     * @return:
     * @author: Timber.Wang
     * @describe: 失败返回结果
     * @date 2021/12/11 1:39
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /*
     * @Param: message
     * @Param: obj
     * @return:
     * @author: Timber.Wang
     * @describe: 失败返回结果
     * @date 2021/12/11 1:40
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }

}
