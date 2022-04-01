package com.timberkito.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timberkito.server.pojo.Employee;
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
}
