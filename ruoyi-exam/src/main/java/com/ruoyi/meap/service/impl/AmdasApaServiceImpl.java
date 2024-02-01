package com.ruoyi.meap.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.AmdasApaMapper;
import com.ruoyi.meap.domain.AmdasApa;
import com.ruoyi.meap.service.IAmdasApaService;

/**
 * 活动参与分析Service业务层处理
 * 
 * @author youxiuping
 * @date 2024-01-31
 */
@Service
public class AmdasApaServiceImpl implements IAmdasApaService 
{
    @Autowired
    private AmdasApaMapper amdasApaMapper;

    /**
     * 查询活动参与分析
     * 
     * @param id 活动参与分析主键
     * @return 活动参与分析
     */
    @Override
    public AmdasApa selectAmdasApaById(Long id)
    {
        return amdasApaMapper.selectAmdasApaById(id);
    }

    /**
     * 查询活动参与分析列表
     * 
     * @param amdasApa 活动参与分析
     * @return 活动参与分析
     */
    @Override
    public List<AmdasApa> selectAmdasApaList(AmdasApa amdasApa)
    {
        return amdasApaMapper.selectAmdasApaList(amdasApa);
    }

    /**
     * 新增活动参与分析
     * 
     * @param amdasApa 活动参与分析
     * @return 结果
     */
    @Override
    public int insertAmdasApa(AmdasApa amdasApa)
    {
        return amdasApaMapper.insertAmdasApa(amdasApa);
    }

    /**
     * 修改活动参与分析
     * 
     * @param amdasApa 活动参与分析
     * @return 结果
     */
    @Override
    public int updateAmdasApa(AmdasApa amdasApa)
    {
        return amdasApaMapper.updateAmdasApa(amdasApa);
    }

    /**
     * 批量删除活动参与分析
     * 
     * @param ids 需要删除的活动参与分析主键
     * @return 结果
     */
    @Override
    public int deleteAmdasApaByIds(Long[] ids)
    {
        return amdasApaMapper.deleteAmdasApaByIds(ids);
    }

    /**
     * 删除活动参与分析信息
     * 
     * @param id 活动参与分析主键
     * @return 结果
     */
    @Override
    public int deleteAmdasApaById(Long id)
    {
        return amdasApaMapper.deleteAmdasApaById(id);
    }
}
