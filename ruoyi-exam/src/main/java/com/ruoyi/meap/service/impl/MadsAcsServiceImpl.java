package com.ruoyi.meap.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.MadsAcsMapper;
import com.ruoyi.meap.domain.MadsAcs;
import com.ruoyi.meap.service.IMadsAcsService;

/**
 * 活动触点统计情况Service业务层处理
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@Service
public class MadsAcsServiceImpl implements IMadsAcsService 
{
    @Autowired
    private MadsAcsMapper madsAcsMapper;

    /**
     * 查询活动触点统计情况
     * 
     * @param id 活动触点统计情况主键
     * @return 活动触点统计情况
     */
    @Override
    public MadsAcs selectMadsAcsById(Long id)
    {
        return madsAcsMapper.selectMadsAcsById(id);
    }

    /**
     * 查询活动触点统计情况列表
     * 
     * @param madsAcs 活动触点统计情况
     * @return 活动触点统计情况
     */
    @Override
    public List<MadsAcs> selectMadsAcsList(MadsAcs madsAcs)
    {
        return madsAcsMapper.selectMadsAcsList(madsAcs);
    }

    /**
     * 新增活动触点统计情况
     * 
     * @param madsAcs 活动触点统计情况
     * @return 结果
     */
    @Override
    public int insertMadsAcs(MadsAcs madsAcs)
    {
        return madsAcsMapper.insertMadsAcs(madsAcs);
    }

    /**
     * 修改活动触点统计情况
     * 
     * @param madsAcs 活动触点统计情况
     * @return 结果
     */
    @Override
    public int updateMadsAcs(MadsAcs madsAcs)
    {
        return madsAcsMapper.updateMadsAcs(madsAcs);
    }

    /**
     * 批量删除活动触点统计情况
     * 
     * @param ids 需要删除的活动触点统计情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsAcsByIds(Long[] ids)
    {
        return madsAcsMapper.deleteMadsAcsByIds(ids);
    }

    /**
     * 删除活动触点统计情况信息
     * 
     * @param id 活动触点统计情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsAcsById(Long id)
    {
        return madsAcsMapper.deleteMadsAcsById(id);
    }
}
