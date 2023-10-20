package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 司法考试-考生单位管理表
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("unit_manage")
@ApiModel(value="UnitManage对象", description="司法考试-考生单位管理表")
public class UnitManage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "unit_id", type = IdType.AUTO)
    private String unitId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "数据状态（0：正常  1：删除）")
    private String delFlag;


}
