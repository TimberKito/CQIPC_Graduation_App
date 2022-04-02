package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.Department;
import com.timberkito.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     *
     * @param
     * @return java.util.List<com.timberkito.server.pojo.Department>
     * @author Timber.Wang
     * @date 2022/3/30 11:33
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     *
     * @param dep
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022/3/30 11:33
     */
    RespBean addDep(Department dep);


    /**
     * 删除部门
     *
     * @param id
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022/3/30 11:33
     */
    RespBean deleteDep(Integer id);
}
