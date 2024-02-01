package com.ruoyi.meap.mapper;

import java.util.List;
import com.ruoyi.meap.domain.AmdasAcpa;

/**
 * 活动交叉参与分析Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-31
 */
public interface AmdasAcpaMapper 
{

    public List<String> getPeriodicals();

    /**
     * 查询活动交叉参与分析
     * 
     * @param id 活动交叉参与分析主键
     * @return 活动交叉参与分析
     */
    public AmdasAcpa selectAmdasAcpaById(Long id);

    /**
     * 查询活动交叉参与分析列表
     * 
     * @param amdasAcpa 活动交叉参与分析
     * @return 活动交叉参与分析集合
     */
    public List<AmdasAcpa> selectAmdasAcpaList(AmdasAcpa amdasAcpa);

    /**
     * 新增活动交叉参与分析
     * 
     * @param amdasAcpa 活动交叉参与分析
     * @return 结果
     */
    public int insertAmdasAcpa(AmdasAcpa amdasAcpa);

    /**
     * 修改活动交叉参与分析
     * 
     * @param amdasAcpa 活动交叉参与分析
     * @return 结果
     */
    public int updateAmdasAcpa(AmdasAcpa amdasAcpa);

    /**
     * 删除活动交叉参与分析
     * 
     * @param id 活动交叉参与分析主键
     * @return 结果
     */
    public int deleteAmdasAcpaById(Long id);

    /**
     * 批量删除活动交叉参与分析
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAmdasAcpaByIds(Long[] ids);
}
