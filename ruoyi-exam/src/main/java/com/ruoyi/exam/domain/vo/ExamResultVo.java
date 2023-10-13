package com.ruoyi.exam.domain.vo;

import com.ruoyi.exam.domain.AnswerSheetVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 考试结果
 * </p>
 *
 * @author yxp
 * @since 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamResultVo {

    //本次作答分数
    private Integer thisScore;

    //正确率
    private String correctRate;

    //正确数
    private Integer correctNum;

    //错题数
    private Integer errorNum;

    //未答数
    private Integer wdNum;

    //学时
    private String classHour;

    //答题卡
    private List<AnswerSheetVo> asVoList;

}
