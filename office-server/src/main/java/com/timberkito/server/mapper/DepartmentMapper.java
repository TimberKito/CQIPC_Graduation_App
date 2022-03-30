package com.timberkito.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timberkito.server.pojo.Department;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface DepartmentMapper extends BaseMapper<Department> {


    /**
     * @param parentId
     * @return java.util.List<com.timberkito.server.pojo.Department>
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:35
     */
    List<Department> getAllDepartments(Integer parentId);


    /**
     * @param dep
     * @return void
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:35
     */
    void addDep(Department dep);


    /**
     * @param department
     * @return void
     * @author Timber.Wang
     * @describe:
     * @date 2022/3/30 11:35
     */
    void deleteDep(Department department);
}
