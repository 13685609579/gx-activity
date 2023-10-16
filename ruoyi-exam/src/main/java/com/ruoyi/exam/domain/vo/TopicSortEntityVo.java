package com.ruoyi.exam.domain.vo;

import com.ruoyi.exam.domain.TestTrain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 题目分类
 * </p>
 *
 * @author yxp
 * @since 2023-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TopicSortEntityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    private Long dictCode;

    /**
     * 字典排序
      */
     private Long dictSort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    private String dictType;

    private TestTrainVo trainVo;

}
