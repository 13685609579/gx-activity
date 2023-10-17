package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.ClassHourSf;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.domain.vo.CreditHoursDistributionVo;
import com.ruoyi.exam.domain.vo.PersonClassHourVo;
import com.ruoyi.exam.mapper.ClassHourSfMapper;
import com.ruoyi.exam.mapper.ExamManageMapper;
import com.ruoyi.exam.service.ExamManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 考试管理表 服务实现类
 * </p>
 *
 * @author xyp
 * @since 2023-09-28
 */
@Service
public class ExamManageServiceImpl extends ServiceImpl<ExamManageMapper, ExamManage> implements ExamManageService {

    @Autowired
    private ExamManageMapper examManageMapper;

    @Autowired
    private ClassHourSfMapper classHourSfMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 考试管理列表
     * @param examManage
     * @return
     */
    @Override
    public List<ExamManage> selectExamManageList(ExamManage examManage) {
        List<ExamManage> examManageList = examManageMapper.selectExamManageList(examManage);
        return examManageList;
    }

    /**
     * 新增考试项目
     * @param examManage
     * @return
     */
    @Override
    public int insertExamManageData(ExamManage examManage) {
        examManage.setExamYear(examManage.getStartTime().substring(0, 4));
        String examId = DataUtils.uuids();
        examManage.setExamId(examId);
        int row = examManageMapper.insertExamManageData(examManage);
        List<ClassHourSf> classHourSfList = examManage.getClassHourSfList();
        if(null != classHourSfList && classHourSfList.size()>0){
            for(int i=0; i<classHourSfList.size(); i++){
                ClassHourSf classHourSf = classHourSfList.get(i);
                classHourSf.setHourId(DataUtils.uuids());
                classHourSf.setExamId(examId);
                classHourSf.setTopicSort(classHourSf.getDictCode());
                classHourSf.setCreateBy(examManage.getCreateBy());
                classHourSf.setDelFlag("1");
                classHourSfMapper.insertClassHourSfData(classHourSf);
            }
        }
        return row;
    }

    /**
     * 获取考试项目详情
     * @param examId
     * @return
     */
    @Override
    public ExamManage examManageInfo(String examId) {
        ExamManage examManage = examManageMapper.examManageInfo(examId);
        List<PersonClassHourVo> personLabelHourList = classHourSfMapper.getPersonLabelHour(examManage.getExamId());
        Map<String, List<PersonClassHourVo>> maps = new HashMap<>();
        List<PersonClassHourVo> listZf = new ArrayList<PersonClassHourVo>();
        List<PersonClassHourVo> listFzf = new ArrayList<PersonClassHourVo>();
        if(null != personLabelHourList && personLabelHourList.size()>0){
            personLabelHourList.stream().forEach(m->{
                m.setTargetHours(m.getTargetHours().substring(0, m.getTargetHours().indexOf(".")));
                if(StringUtils.equals("1", m.getPersonType())){
                    listZf.add(m);
                }
                if(StringUtils.equals("2", m.getPersonType())){
                    listFzf.add(m);
                }
            });
        }
        maps.put("zf", listZf);
        maps.put("fzf", listFzf);
        examManage.setSfClassHours(maps);
        return examManage;
    }

    /**
     * 修改考试项目
     * @param examManage
     * @return
     */
    @Override
    public int updateExamManage(ExamManage examManage) {
        examManage.setExamYear(examManage.getStartTime().substring(0, 4));
        int row = examManageMapper.updateExamManage(examManage);
        List<ClassHourSf> classHourSfList = examManage.getClassHourSfList();
        if(null != classHourSfList && classHourSfList.size()>0){
            classHourSfList.stream().forEach(m->{
                classHourSfMapper.updateClassHourSf(m);
            });
        }
        return row;
    }

    /**
     * 修改发布状态
     * @param examId
     * @return
     */
    @Override
    public int updatePublicState(String examId) {
        ExamManage examManage = examManageMapper.examManageInfo(examId);
        ExamManage examManage1 = new ExamManage();
        examManage1.setExamId(examId);
        if(StringUtils.equals("1", examManage.getPublishState())){
            examManage1.setPublishState("0");
        }
        if(StringUtils.equals("0", examManage.getPublishState())){
            examManage1.setPublishState("1");
        }
        int row = examManageMapper.updatePublicState(examManage1);
        return row;
    }

}
