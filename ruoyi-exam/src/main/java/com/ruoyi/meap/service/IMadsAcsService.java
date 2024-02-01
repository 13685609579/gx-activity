package com.ruoyi.meap.service;

import java.util.List;
import com.ruoyi.meap.domain.MadsAcs;

/**
 * 活动触点统计情况Service接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface IMadsAcsService 
{
    /**
     * 查询活动触点统计情况
     * 
     * @param id 活动触点统计情况主键
     * @return 活动触点统计情况
     */
    public MadsAcs selectMadsAcsById(Long id);

    /**
     * 查询活动触点统计情况列表
     * 
     * @param madsAcs 活动触点统计情况
     * @return 活动触点统计情况集合
     */
    public List<MadsAcs> selectMadsAcsList(MadsAcs madsAcs);

    /**
     * 新增活动触点统计情况
     * 
     * @param madsAcs 活动触点统计情况
     * @return 结果
     */
    public int insertMadsAcs(MadsAcs madsAcs);

    /**
     * 修改活动触点统计情况
     * 
     * @param madsAcs 活动触点统计情况
     * @return 结果
     */
    public int updateMadsAcs(MadsAcs madsAcs);

    /**
     * 批量删除活动触点统计情况
     * 
     * @param ids 需要删除的活动触点统计情况主键集合
     * @return 结果
     */
    public int deleteMadsAcsByIds(Long[] ids);

    /**
     * 删除活动触点统计情况信息
     * 
     * @param id 活动触点统计情况主键
     * @return 结果
     */
    public int deleteMadsAcsById(Long id);
}
