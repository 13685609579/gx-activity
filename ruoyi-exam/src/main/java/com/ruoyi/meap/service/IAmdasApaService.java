package com.ruoyi.meap.service;

import java.util.List;
import com.ruoyi.meap.domain.AmdasApa;

/**
 * 活动参与分析Service接口
 * 
 * @author youxiuping
 * @date 2024-01-31
 */
public interface IAmdasApaService 
{
    /**
     * 查询活动参与分析
     * 
     * @param id 活动参与分析主键
     * @return 活动参与分析
     */
    public AmdasApa selectAmdasApaById(Long id);

    /**
     * 查询活动参与分析列表
     * 
     * @param amdasApa 活动参与分析
     * @return 活动参与分析集合
     */
    public List<AmdasApa> selectAmdasApaList(AmdasApa amdasApa);

    /**
     * 新增活动参与分析
     * 
     * @param amdasApa 活动参与分析
     * @return 结果
     */
    public int insertAmdasApa(AmdasApa amdasApa);

    /**
     * 修改活动参与分析
     * 
     * @param amdasApa 活动参与分析
     * @return 结果
     */
    public int updateAmdasApa(AmdasApa amdasApa);

    /**
     * 批量删除活动参与分析
     * 
     * @param ids 需要删除的活动参与分析主键集合
     * @return 结果
     */
    public int deleteAmdasApaByIds(Long[] ids);

    /**
     * 删除活动参与分析信息
     * 
     * @param id 活动参与分析主键
     * @return 结果
     */
    public int deleteAmdasApaById(Long id);
}
