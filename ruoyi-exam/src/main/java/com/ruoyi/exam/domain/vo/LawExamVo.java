package com.ruoyi.exam.domain.vo;

import com.ruoyi.exam.domain.ClassHourSf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学法考试
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LawExamVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目分类、学时
     */
    private List<DictLabelClassHourVo> dictLabelClassHourVoList;

    /**
     * 司法人员学时
     */
    private List<PersonClassHourVo> personClassHourVoList;

    /**
     * 题目题型、分数、题数
     */
    private List<TopicTypeEntityVo> topicTypeEntityVoList;

    /**
     * 学时分布
     */
    private List<CreditHoursDistributionVo> creditHoursDistributionVoList;

}
