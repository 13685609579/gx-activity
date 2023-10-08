package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 考试管理表 Mapper 接口
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
public interface ExamManageMapper extends BaseMapper<ExamManage> {

    /**
     * 考试管理列表
     * @param examManage
     * @return
     */
    public List<ExamManage> selectExamManageList(ExamManage examManage);

    /**
     * 新增考试项目
     * @param examManage
     * @return
     */
    public int insertExamManageData(ExamManage examManage);

    /**
     * 获取考试项目详情
     * @param examId
     * @return
     */
    public ExamManage examManageInfo(String examId);

}
