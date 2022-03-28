package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.DepartmentMapper;
import com.timberkito.server.pojo.Department;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * @param []
     * @return java.util.List<com.timberkito.server.pojo.Department>
     * @author Timber.Wang
     * @describe: 获取所有部门
     * @date 2022/3/28 13:29
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * @param [dep]
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 添加部门
     * @date 2022/3/28 13:49
     */
    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()) {
            return RespBean.success("添加成功！", dep);
        }
        return RespBean.error("添加失败！");
    }

    /**
     * @param [id]
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 删除部门
     * @date 2022/3/28 14:14
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDep(department);
        if (-2 == department.getResult()) {
            return RespBean.error("该部门下存在子部门，删除失败！");
        }
        if (-1 == department.getResult()) {
            return RespBean.error("该部门下存在员工，删除失败！");
        }
        if (1 == department.getResult()) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
