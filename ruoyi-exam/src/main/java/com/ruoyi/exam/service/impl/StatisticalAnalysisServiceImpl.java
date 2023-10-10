package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.ClassHourSf;
import com.ruoyi.exam.domain.PersonClassHour;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisDetailsVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisVo;
import com.ruoyi.exam.mapper.*;
import com.ruoyi.exam.service.StatisticalAnalysisService;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 统计分析 服务实现类
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
@Service
public class StatisticalAnalysisServiceImpl extends ServiceImpl<StatisticalAnalysisMapper, StatisticalAnalysisVo> implements StatisticalAnalysisService {

    @Autowired
    private StatisticalAnalysisMapper statisticalAnalysisMapper;

    @Autowired
    private CandidateInfoMapper candidateInfoMapper;

    @Autowired
    private ClassHourSfMapper classHourSfMapper;

    @Autowired
    private UnitManageMapper unitManageMapper;

    @Autowired
    private PersonClassHourMapper personClassHourMapper;

    /**
     * 统计分析列表
     * @param candidateClassHourVo
     * @return
     */
    @Override
    public List<StatisticalAnalysisVo> selectStatisticalAnalysisList(CandidateClassHourVo candidateClassHourVo) {
        List<UnitManage> unitManageList = unitManageMapper.selectUnitList(candidateClassHourVo);
        List<StatisticalAnalysisVo> list = new ArrayList<>();
        Set<StatisticalAnalysisVo> sets = new HashSet<>();
        if(null != unitManageList && unitManageList.size()>0){
            List<String> unitIds = new ArrayList<>();
            Set<String> unitNames = new HashSet<>();
            unitManageList.stream().forEach(m->{
                unitNames.add(m.getUnitName());
                unitIds.add(m.getUnitId());
            });
            List<CandidateClassHourVo> candidateClassHourVoList = statisticalAnalysisMapper.selectStatisticalAnalysisList(candidateClassHourVo);
            Map<String, List<CandidateClassHourVo>> stringListMap = new HashMap<>();
            if(null != candidateClassHourVoList && candidateClassHourVoList.size()>0){
                unitNames.stream().forEach(n->{
                    List<CandidateClassHourVo> list1 = new ArrayList<>();
                    candidateClassHourVoList.stream().forEach(o->{
                        if(StringUtils.equals(n, o.getUnitName())){
                            CandidateInfo candidateInfo = new CandidateInfo();
                            candidateInfo.setOpenId(o.getCandidateId());
                            CandidateInfo candidateInfo1 = candidateInfoMapper.selectCandidateInfo(candidateInfo);
                            ClassHourSf classHourSf = new ClassHourSf();
                            classHourSf.setExamId(o.getExamId());
                            classHourSf.setPersonType(candidateInfo1.getPersonCategory());
                            String targetHours = classHourSfMapper.getTargetHours(classHourSf);
                            if(Integer.parseInt(o.getClassHour()) == Integer.parseInt(targetHours)){
                                o.setCompleteState("1");
                            }
                            if(Integer.parseInt(o.getClassHour()) < Integer.parseInt(targetHours)){
                                o.setCompleteState("0");
                            }
                            list1.add(o);
                        }
                    });
                    stringListMap.put(n, list1);
                });
                unitNames.stream().forEach(a->{
                    List<CandidateClassHourVo> voList = stringListMap.get(a);
                    Set<String> examIdSets = new HashSet<>();
                    voList.stream().forEach(b->{
                        examIdSets.add(b.getExamId());
                    });
                    examIdSets.stream().forEach(c->{
                        Set<CandidateClassHourVo> voList1 = new HashSet<>();
                        voList.stream().forEach(d->{
                            if(StringUtils.equals(c, d.getExamId())){
                                voList1.add(d);
                            }
                        });
                        List<CandidateClassHourVo> listComplete = new ArrayList<>(); //完成
                        List<CandidateClassHourVo> listUncompleted = new ArrayList<>(); //未完成
                        voList1.stream().forEach(f->{
                            if(StringUtils.equals("1", f.getCompleteState())){
                                listComplete.add(f);
                            }
                            if(StringUtils.equals("0", f.getCompleteState())){
                                listUncompleted.add(f);
                            }
                        });
                        voList1.stream().forEach(g->{
                            StatisticalAnalysisVo analysisVo = new StatisticalAnalysisVo();
                            analysisVo.setExamId(g.getExamId());
                            analysisVo.setExamTitle(g.getExamTitle());
                            analysisVo.setUnitId(g.getUnitId());
                            analysisVo.setUnitName(g.getUnitName());
                            analysisVo.setExamYear(g.getExamYear());
                            analysisVo.setCompleteCount(String.valueOf(listComplete.size()));
                            analysisVo.setUncompletedCount(String.valueOf(listUncompleted.size()));
                            double v1 = Double.parseDouble(String.valueOf(listComplete.size()));
                            double v2 = Double.parseDouble(String.valueOf(listUncompleted.size()));
                            double completeRate = DataUtils.division(v1, v2, 4)*100;
                            analysisVo.setCompleteRate(String.valueOf(completeRate)+"%");
                            sets.add(analysisVo);
                        });
                    });
                });
            }
        }
        sets.stream().forEach(h->{
            list.add(h);
        });
        return list;
    }

    /**
     * 统计分析详情
     * @param detailsVo
     * @return
     */
    @Override
    public List<StatisticalAnalysisDetailsVo> getStatisticalAnalysisDetails(StatisticalAnalysisDetailsVo detailsVo) {
        List<StatisticalAnalysisDetailsVo> list = statisticalAnalysisMapper.getStatisticalAnalysisDetails(detailsVo);
        if(null != list && list.size()>0){
            list.stream().forEach(m->{
                PersonClassHour personClassHour = new PersonClassHour();
                personClassHour.setCandidateId(m.getCandidateId());
                personClassHour.setExamId(m.getExamId());
                String acquiredHours = personClassHourMapper.getAcquiredHours(personClassHour);
                if(acquiredHours.contains(".")){
                    acquiredHours = acquiredHours.substring(0, acquiredHours.indexOf("."));
                }
                m.setAcquiredHours(acquiredHours);
            });
        }
        return list;
    }

}
