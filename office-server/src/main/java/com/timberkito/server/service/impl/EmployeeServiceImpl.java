package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.EmployeeMapper;
import com.timberkito.server.pojo.Employee;
import com.timberkito.server.pojo.RespPageBean;
import com.timberkito.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return com.timberkito.server.pojo.RespPageBear
     * @author Timber.Wang
     * @describe: 获取所有员工(分页)
     * @date 2022/4/1 19:08
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }
}
