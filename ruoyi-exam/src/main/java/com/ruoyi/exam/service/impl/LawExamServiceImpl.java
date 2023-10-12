package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.ClassHourSf;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.domain.PersonClassHour;
import com.ruoyi.exam.domain.vo.*;
import com.ruoyi.exam.mapper.*;
import com.ruoyi.exam.service.LawExamService;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学法考试 服务实现类
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Service
public class LawExamServiceImpl extends ServiceImpl<LawExamMapper, LawExamVo> implements LawExamService {

    @Autowired
    private CandidateInfoMapper candidateInfoMapper;

    @Autowired
    private ExamManageMapper examManageMapper;

    @Autowired
    private PersonClassHourMapper personClassHourMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private DictLabelClassHourMapper dictLabelClassHourMapper;

    @Autowired
    private ClassHourSfMapper classHourSfMapper;

    /**
     * 考试（结题考试）
     * @param openId
     * @return
     */
    @Override
    public LawExamVo getFinalExam(String openId) {
        LawExamVo lawExamVo = new LawExamVo();
        ExamManage examManage = examManageMapper.latestExamManageInfo();
        if(null != examManage && StringUtils.equals("1", examManage.getPublishState())){
            //题目分类、学时
            CandidateInfo candidateInfo = new CandidateInfo();
            candidateInfo.setCandidateId(openId);
            candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
            ClassHourVo classHourVo = new ClassHourVo();
            classHourVo.setExamId(examManage.getExamId());
            classHourVo.setPersonType(candidateInfo.getPersonCategory());
            classHourVo.setCandidateId(openId);
            classHourVo.setExamId(examManage.getExamId());
            List<DictLabelClassHourVo> labelClassHourVoList = dictLabelClassHourMapper.getTopicSortClassHours(classHourVo);
            if(null != labelClassHourVoList && labelClassHourVoList.size()>0){
                labelClassHourVoList.stream().forEach(m->{
                    m.setTargetHours(m.getTargetHours().substring(0, m.getTargetHours().indexOf(".")));
                });
                lawExamVo.setDictLabelClassHourVoList(labelClassHourVoList);
            }

            //司法人员学时
            List<PersonClassHourVo> personClassHourVoList = classHourSfMapper.getPersonClassHour(examManage.getExamId());
            if(null != personClassHourVoList && personClassHourVoList.size()>0){
                personClassHourVoList.stream().forEach(m->{
                    m.setTargetHours(m.getTargetHours().substring(0, m.getTargetHours().indexOf(".")));
                });
                lawExamVo.setPersonClassHourVoList(personClassHourVoList);
            }

            //题目题型、分数、题数
            List<TopicTypeEntityVo> topicTypeEntityVoList = new ArrayList<TopicTypeEntityVo>();
            TopicTypeEntityVo topicTypeEntityVo1 = new TopicTypeEntityVo();
            topicTypeEntityVo1.setTopicType("单选题");
            topicTypeEntityVo1.setPerScore("2");
            topicTypeEntityVo1.setTopicCount("30");
            topicTypeEntityVoList.add(topicTypeEntityVo1);

            TopicTypeEntityVo topicTypeEntityVo2 = new TopicTypeEntityVo();
            topicTypeEntityVo2.setTopicType("判断题");
            topicTypeEntityVo2.setPerScore("2");
            topicTypeEntityVo2.setTopicCount("10");
            topicTypeEntityVoList.add(topicTypeEntityVo2);

            TopicTypeEntityVo topicTypeEntityVo3 = new TopicTypeEntityVo();
            topicTypeEntityVo3.setTopicType("多选题");
            topicTypeEntityVo3.setPerScore("5");
            topicTypeEntityVo3.setTopicCount("4");
            topicTypeEntityVoList.add(topicTypeEntityVo3);
            lawExamVo.setTopicTypeEntityVoList(topicTypeEntityVoList);

            //学时分布
            List<PersonClassHourVo> personLabelHourList = classHourSfMapper.getPersonLabelHour(examManage.getExamId());
            List<CreditHoursDistributionVo> creditHoursDistributionVoList = new ArrayList<CreditHoursDistributionVo>();
            if(null != personLabelHourList && personLabelHourList.size()>0){
                String sfzfry = "";
                String fsfzfry = "";
                for(int i=0; i<personLabelHourList.size(); i++){
                    PersonClassHourVo personClassHourVo = personLabelHourList.get(i);
                    if(StringUtils.equals("1", personClassHourVo.getPersonType())){
                        sfzfry += personClassHourVo.getDictLabel()+personClassHourVo.getTargetHours().substring(0, personClassHourVo.getTargetHours().indexOf("."))+"学时"+"、";
                    }
                    if(StringUtils.equals("2", personClassHourVo.getPersonType())){
                        fsfzfry += personClassHourVo.getDictLabel()+personClassHourVo.getTargetHours().substring(0, personClassHourVo.getTargetHours().indexOf("."))+"学时"+"、";
                    }
                }
                if(!StringUtils.equals("", sfzfry) && sfzfry.length()>0){
                    sfzfry = sfzfry.substring(0, sfzfry.lastIndexOf("、")-1);
                }
                if(!StringUtils.equals("", fsfzfry) && fsfzfry.length()>0){
                    fsfzfry = fsfzfry.substring(0, fsfzfry.lastIndexOf("、")-1);
                }
                CreditHoursDistributionVo creditHoursDistributionVo1 = new CreditHoursDistributionVo();
                creditHoursDistributionVo1.setPersonType("执法司法人员");
                creditHoursDistributionVo1.setCreditHoursInfo(sfzfry);
                creditHoursDistributionVoList.add(creditHoursDistributionVo1);

                CreditHoursDistributionVo creditHoursDistributionVo2 = new CreditHoursDistributionVo();
                creditHoursDistributionVo2.setPersonType("非执法司法人员");
                creditHoursDistributionVo2.setCreditHoursInfo(fsfzfry);
                creditHoursDistributionVoList.add(creditHoursDistributionVo2);
                lawExamVo.setCreditHoursDistributionVoList(creditHoursDistributionVoList);
            }

        }
        return lawExamVo;
    }
}
