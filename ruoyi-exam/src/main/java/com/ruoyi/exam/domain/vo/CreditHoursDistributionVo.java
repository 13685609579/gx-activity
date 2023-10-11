package com.ruoyi.exam.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * <p>
 * 学时分布
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreditHoursDistributionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 人员类型
     */
    private String personType;

    /**
     * 学时分布
     */
    private String creditHoursInfo;

}
