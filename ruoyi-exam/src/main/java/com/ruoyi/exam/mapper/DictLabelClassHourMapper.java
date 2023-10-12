package com.ruoyi.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.exam.domain.vo.ClassHourVo;
import com.ruoyi.exam.domain.vo.DictLabelClassHourVo;

import java.util.List;

/**
 * <p>
 * 题目分类、目标学时、已获学时 Mapper 接口
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
public interface DictLabelClassHourMapper extends BaseMapper<DictLabelClassHourVo> {

    /**
     * 获取当前考生题目分类、目标学时、已获学时
     * @param classHourVo
     * @return
     */
    public List<DictLabelClassHourVo> getTopicSortClassHours(ClassHourVo classHourVo);

}
