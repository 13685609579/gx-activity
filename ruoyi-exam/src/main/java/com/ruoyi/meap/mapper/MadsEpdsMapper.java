package com.ruoyi.meap.mapper;

import java.util.List;
import com.ruoyi.meap.domain.MadsEpds;

/**
 * 活动奖品发放情况Mapper接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface MadsEpdsMapper 
{
    /**
     * 查询活动奖品发放情况
     * 
     * @param id 活动奖品发放情况主键
     * @return 活动奖品发放情况
     */
    public MadsEpds selectMadsEpdsById(Long id);

    /**
     * 查询活动奖品发放情况列表
     * 
     * @param madsEpds 活动奖品发放情况
     * @return 活动奖品发放情况集合
     */
    public List<MadsEpds> selectMadsEpdsList(MadsEpds madsEpds);

    /**
     * 新增活动奖品发放情况
     * 
     * @param madsEpds 活动奖品发放情况
     * @return 结果
     */
    public int insertMadsEpds(MadsEpds madsEpds);

    /**
     * 修改活动奖品发放情况
     * 
     * @param madsEpds 活动奖品发放情况
     * @return 结果
     */
    public int updateMadsEpds(MadsEpds madsEpds);

    /**
     * 删除活动奖品发放情况
     * 
     * @param id 活动奖品发放情况主键
     * @return 结果
     */
    public int deleteMadsEpdsById(Long id);

    /**
     * 批量删除活动奖品发放情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMadsEpdsByIds(Long[] ids);
}
