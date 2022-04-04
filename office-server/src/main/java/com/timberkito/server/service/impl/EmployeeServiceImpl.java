package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.EmployeeMapper;
import com.timberkito.server.pojo.Employee;
import com.timberkito.server.pojo.RespBean;
import com.timberkito.server.pojo.RespPageBean;
import com.timberkito.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return com.timberkito.server.pojo.RespPageBear
     * @author Timber.Wang
     * @date 2022/4/1 19:08
     */
    @SuppressWarnings("checkstyle:NonEmptyAtclauseDescription")
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee,
                                          LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage =
                employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean =
                new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * 获取工号
     *
     * @param
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022/4/2 12:47
     */
    @Override
    public RespBean maxWorkId() {
        List<Map<String, Object>> maps =
                employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));

        return RespBean.success(null, String.format("%08d", Integer.parseInt(
                maps.get(0).get("max(workID)").toString()) + 1));
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return com.timberkito.server.pojo.RespBean
     * @author Timber.Wang
     * @date 2022/4/2 13:08
     */
    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
            return RespBean.success("添加员工成功！");
        }
        return RespBean.error("添加员工失败！");
    }

    /**
     * 查询员工
     *
     * @param id
     * @return java.util.List<com.timberkito.server.pojo.Employee>
     * @author Timber.Wang
     * @date 2022/4/4 20:53
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }
}
