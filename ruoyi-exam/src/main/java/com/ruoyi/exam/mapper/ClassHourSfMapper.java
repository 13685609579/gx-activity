package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ClassHourSf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.exam.domain.vo.PersonClassHourVo;

import java.util.List;

/**
 * <p>
 * 司法人员学时管理表 Mapper 接口
 * </p>
 *
 * @author hougq
 * @since 2023-10-08
 */
public interface ClassHourSfMapper extends BaseMapper<ClassHourSf> {

    /**
     * 获取目标学时
     * @param classHourSf
     * @return
     */
    public String getTargetHours(ClassHourSf classHourSf);

    /**
     * 新增司法人员学时
     * @param classHourSf
     * @return
     */
    public int insertClassHourSfData(ClassHourSf classHourSf);

    /**
     * 修改司法人员学时
     * @param classHourSf
     * @return
     */
    public int updateClassHourSf(ClassHourSf classHourSf);

    /**
     * 获取执法司法人员、非执法司法人员学时
     * @param examId
     * @return
     */
    public List<PersonClassHourVo> getPersonClassHour(String examId);

    /**
     * 获取当前司法考生人员学时分布
     * @param examId
     * @return
     */
    public List<PersonClassHourVo> getPersonLabelHour(String examId);

}
