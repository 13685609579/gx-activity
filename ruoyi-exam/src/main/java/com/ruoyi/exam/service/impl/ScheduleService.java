package com.ruoyi.exam.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.CandidatePaperState;
import com.ruoyi.exam.domain.ExamPaper;
import com.ruoyi.exam.mapper.CandidatePaperStateMapper;
import com.ruoyi.exam.mapper.ExamPaperMapper;
import com.ruoyi.exam.util.DataUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class ScheduleService {

    @Autowired
    private CandidatePaperStateMapper candidatePaperStateMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private ExamPaperMapper examPaperMapper;

    //cron语法：[秒] [分] [小时] [日] [月] [周] [年]
    //每五分钟执行一次
//    @Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "0 */5 * * * ?")
    public void cronTest(){
        LambdaQueryWrapper<CandidatePaperState> wrapper = new LambdaQueryWrapper<CandidatePaperState>();
        wrapper.eq(CandidatePaperState::getPaperState, 1)
                .eq(CandidatePaperState::getDelFlag, 0);
        List<CandidatePaperState> list = candidatePaperStateMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(list)){
            for(int i=0; i<list.size(); i++){
                CandidatePaperState cps = list.get(i);
                boolean overTimeStatus = overTimeStatus(cps.getStartTime());
                //当前考试已超时
                if(overTimeStatus){
                    log.info("超时试卷id---->"+cps.getId());
                    //将当前试卷的所有题目对应状态置为已答
                    LambdaUpdateWrapper<ExamPaper> wrapper1 = new LambdaUpdateWrapper<ExamPaper>();
                    wrapper1.eq(ExamPaper::getPaperStateId, cps.getId())
                            .eq(ExamPaper::getDelFlag, 0)
                            .set(ExamPaper::getTopicState, "0");
                    examPaperMapper.update(null, wrapper1);

                    //将当前试卷状态置为已结束
                    cps.setPaperState("0");
                    candidatePaperStateMapper.updateById(cps);
                }
            }
        }
    }

    /**
     * 判断当前考生考试是否超时
     * @param time
     * @return
     */
    public boolean overTimeStatus(String time){
        long remainTime = remainTime();
        long timeM = DataUtils.getTimeMillis(time);
        long tm = System.currentTimeMillis(); //获取当前毫秒数
        long distance = tm - timeM;
        boolean b = false;
        if(distance>=remainTime){
            b = true;
        }
        return b;
    }

    /**
     * 获取数据字典剩余考试时间
     * @return
     */
    public long remainTime(){
        long remainTime = 0;
        List<SysDictData> sysDictDataList = sysDictDataMapper.selectDictDataByType("remain_time");
        if(null != sysDictDataList && sysDictDataList.size()>0){
            String dictValue = sysDictDataList.get(0).getDictValue();
            if(StringUtils.isNotEmpty(dictValue) && StringUtils.isNotBlank(dictValue)){
                remainTime = Long.valueOf(Integer.parseInt(dictValue)*60*1000);
            }
        }
        return remainTime;
    }

}
