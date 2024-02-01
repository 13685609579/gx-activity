package com.ruoyi.meap.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.AmdasAcpaMapper;
import com.ruoyi.meap.domain.AmdasAcpa;
import com.ruoyi.meap.service.IAmdasAcpaService;

/**
 * 活动交叉参与分析Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-31
 */
@Service
public class AmdasAcpaServiceImpl implements IAmdasAcpaService 
{
    @Autowired
    private AmdasAcpaMapper amdasAcpaMapper;

    @Override
    public List<String> getPeriodicals() {
        return amdasAcpaMapper.getPeriodicals();
    }

    /**
     * 查询活动交叉参与分析
     * 
     * @param id 活动交叉参与分析主键
     * @return 活动交叉参与分析
     */
    @Override
    public AmdasAcpa selectAmdasAcpaById(Long id)
    {
        return amdasAcpaMapper.selectAmdasAcpaById(id);
    }

    /**
     * 查询活动交叉参与分析列表
     * 
     * @param amdasAcpa 活动交叉参与分析
     * @return 活动交叉参与分析
     */
    @Override
    public List<AmdasAcpa> selectAmdasAcpaList(AmdasAcpa amdasAcpa)
    {
        return amdasAcpaMapper.selectAmdasAcpaList(amdasAcpa);
    }

    /**
     * 新增活动交叉参与分析
     * 
     * @param amdasAcpa 活动交叉参与分析
     * @return 结果
     */
    @Override
    public int insertAmdasAcpa(AmdasAcpa amdasAcpa)
    {
        return amdasAcpaMapper.insertAmdasAcpa(amdasAcpa);
    }

    /**
     * 修改活动交叉参与分析
     * 
     * @param amdasAcpa 活动交叉参与分析
     * @return 结果
     */
    @Override
    public int updateAmdasAcpa(AmdasAcpa amdasAcpa)
    {
        return amdasAcpaMapper.updateAmdasAcpa(amdasAcpa);
    }

    /**
     * 批量删除活动交叉参与分析
     * 
     * @param ids 需要删除的活动交叉参与分析主键
     * @return 结果
     */
    @Override
    public int deleteAmdasAcpaByIds(Long[] ids)
    {
        return amdasAcpaMapper.deleteAmdasAcpaByIds(ids);
    }

    /**
     * 删除活动交叉参与分析信息
     * 
     * @param id 活动交叉参与分析主键
     * @return 结果
     */
    @Override
    public int deleteAmdasAcpaById(Long id)
    {
        return amdasAcpaMapper.deleteAmdasAcpaById(id);
    }
}
