package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.Employee;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.pojo.RespPageBean;

import java.time.LocalDate;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return com.timberkito.server.pojo.RespPageBear
     * @author Timber.Wang
     * @describe: 获取所有员工(分页)
     * @date 2022/4/1 19:06
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * @param
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 获取工号
     * @date 2022/4/2 12:47
     */
    RespBean maxWorkId();

    /**
     * @param employee
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @describe: 添加员工
     * @date 2022/4/2 13:08
     */
    RespBean addEmp(Employee employee);
}
