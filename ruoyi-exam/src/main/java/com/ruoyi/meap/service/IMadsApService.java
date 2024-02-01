package com.ruoyi.meap.service;

import java.util.List;
import com.ruoyi.meap.domain.MadsAp;
import com.ruoyi.meap.domain.vo.CyPvUvVo;
import com.ruoyi.meap.domain.vo.CyhdyhhyqkVo;

/**
 * 活动参与情况Service接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface IMadsApService 
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

    public List<MadsAp> yxhdfxList(MadsAp meapAp);

    public List<MadsAp> yxhdcyList(MadsAp meapAp);

    public List<MadsAp> yxhdymfxList(MadsAp meapAp);

    public List<CyhdyhhyqkVo> cyhdyhhyqkList(MadsAp meapAp);

    public CyPvUvVo cyPvUv(MadsAp meapAp);
    
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
     * 批量删除活动参与情况
     * 
     * @param ids 需要删除的活动参与情况主键集合
     * @return 结果
     */
    public int deleteMadsApByIds(Long[] ids);

    /**
     * 删除活动参与情况信息
     * 
     * @param id 活动参与情况主键
     * @return 结果
     */
    public int deleteMadsApById(Long id);
}
