package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 考试管理表 服务类
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
public interface ExamManageService extends IService<ExamManage> {


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

    /**
     * 修改考试项目
     * @param examManage
     * @return
     */
    public int updateExamManage(ExamManage examManage);

    /**
     * 修改发布状态
     * @param examId
     * @return
     */
    public int updatePublicState(String examId);

}
