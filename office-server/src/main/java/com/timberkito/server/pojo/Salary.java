package com.timberkito.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_salary")
@ApiModel(value="Salary对象", description="")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "基本工资")
    private Integer basicSalary;

    @ApiModelProperty(value = "奖金")
    private Integer bonus;

    @ApiModelProperty(value = "午餐补助")
    private Integer lunchSalary;

    @ApiModelProperty(value = "交通补助")
    private Integer trafficSalary;

    @ApiModelProperty(value = "应发工资")
    private Integer allSalary;

    @ApiModelProperty(value = "养老金基数")
    private Integer pensionBase;

    @ApiModelProperty(value = "养老金比率")
    private Float pensionPer;

    @ApiModelProperty(value = "启用时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "医疗基数")
    private Integer medicalBase;

    @ApiModelProperty(value = "医疗保险比率")
    private Float medicalPer;

    @ApiModelProperty(value = "公积金基数")
    private Integer accumulationFundBase;

    @ApiModelProperty(value = "公积金比率")
    private Float accumulationFundPer;

    @ApiModelProperty(value = "名称")
    private String name;


}
