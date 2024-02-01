package com.ruoyi.meap.mapper;

import java.util.List;
import com.ruoyi.meap.domain.MadsAp;
import com.ruoyi.meap.domain.vo.CyPvUvVo;
import com.ruoyi.meap.domain.vo.CyhdyhhyqkVo;

/**
 * 活动参与情况Mapper接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface MadsApMapper 
{
    /**
     * 查询活动参与情况
     * 
     * @param id 活动参与情况主键
     * @return 活动参与情况
     */
    public MadsAp selectMadsApById(Long id);

    /**
     * 查询活动参与情况列表
     * 
     * @param madsAp 活动参与情况
     * @return 活动参与情况集合
     */
    public List<MadsAp> selectMadsApList(MadsAp madsAp);

    public List<MadsAp> yxhdfxList(MadsAp madsAp);

    public List<MadsAp> yxhdcyList(MadsAp madsAp);

    public List<MadsAp> yxhdymfxList(MadsAp madsAp);

    public List<CyhdyhhyqkVo> cyhdyhhyqkList(MadsAp madsAp);

    public CyPvUvVo cyPvUv(MadsAp madsAp);
    
    /**
     * 新增活动参与情况
     * 
     * @param madsAp 活动参与情况
     * @return 结果
     */
    public int insertMadsAp(MadsAp madsAp);

    /**
     * 修改活动参与情况
     * 
     * @param madsAp 活动参与情况
     * @return 结果
     */
    public int updateMadsAp(MadsAp madsAp);

    /**
     * 删除活动参与情况
     * 
     * @param id 活动参与情况主键
     * @return 结果
     */
    public int deleteMadsApById(Long id);

    /**
     * 批量删除活动参与情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMadsApByIds(Long[] ids);
}
