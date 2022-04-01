package com.timberkito.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页公共返回对象
 *
 * @author Timber
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    /**
     * @param null
     * @return
     * @author Timber.Wang
     * @describe: 总条数
     * @date 2022/4/1 17:41
     */
    private Long total;

    /**
     * @param null
     * @return
     * @author Timber.Wang
     * @describe: 数据list
     * @date 2022/4/1 17:41
     */
    private List<?> data;
}
