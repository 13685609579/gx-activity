package com.ruoyi.meap.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.MadsEpdsMapper;
import com.ruoyi.meap.domain.MadsEpds;
import com.ruoyi.meap.service.IMadsEpdsService;

/**
 * 活动奖品发放情况Service业务层处理
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@Service
public class MadsEpdsServiceImpl implements IMadsEpdsService 
{
    @Autowired
    private MadsEpdsMapper madsEpdsMapper;

    /**
     * 查询活动奖品发放情况
     * 
     * @param id 活动奖品发放情况主键
     * @return 活动奖品发放情况
     */
    @Override
    public MadsEpds selectMadsEpdsById(Long id)
    {
        return madsEpdsMapper.selectMadsEpdsById(id);
    }

    /**
     * 查询活动奖品发放情况列表
     * 
     * @param madsEpds 活动奖品发放情况
     * @return 活动奖品发放情况
     */
    @Override
    public List<MadsEpds> selectMadsEpdsList(MadsEpds madsEpds)
    {
        return madsEpdsMapper.selectMadsEpdsList(madsEpds);
    }

    /**
     * 新增活动奖品发放情况
     * 
     * @param madsEpds 活动奖品发放情况
     * @return 结果
     */
    @Override
    public int insertMadsEpds(MadsEpds madsEpds)
    {
        return madsEpdsMapper.insertMadsEpds(madsEpds);
    }

    /**
     * 修改活动奖品发放情况
     * 
     * @param madsEpds 活动奖品发放情况
     * @return 结果
     */
    @Override
    public int updateMadsEpds(MadsEpds madsEpds)
    {
        return madsEpdsMapper.updateMadsEpds(madsEpds);
    }

    /**
     * 批量删除活动奖品发放情况
     * 
     * @param ids 需要删除的活动奖品发放情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsEpdsByIds(Long[] ids)
    {
        return madsEpdsMapper.deleteMadsEpdsByIds(ids);
    }

    /**
     * 删除活动奖品发放情况信息
     * 
     * @param id 活动奖品发放情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsEpdsById(Long id)
    {
        return madsEpdsMapper.deleteMadsEpdsById(id);
    }
}
