package com.ruoyi.meap.service.impl;

import java.util.List;

import com.ruoyi.meap.domain.vo.CyPvUvVo;
import com.ruoyi.meap.domain.vo.CyhdyhhyqkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.MadsApMapper;
import com.ruoyi.meap.domain.MadsAp;
import com.ruoyi.meap.service.IMadsApService;

/**
 * 活动参与情况Service业务层处理
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@Service
public class MadsApServiceImpl implements IMadsApService 
{
    @Autowired
    private MadsApMapper madsApMapper;

    /**
     * 查询活动参与情况
     * 
     * @param id 活动参与情况主键
     * @return 活动参与情况
     */
    @Override
    public MadsAp selectMadsApById(Long id)
    {
        return madsApMapper.selectMadsApById(id);
    }

    /**
     * 查询活动参与情况列表
     * 
     * @param madsAp 活动参与情况
     * @return 活动参与情况
     */
    @Override
    public List<MadsAp> selectMadsApList(MadsAp madsAp)
    {
        return madsApMapper.selectMadsApList(madsAp);
    }

    @Override
    public List<MadsAp> yxhdfxList(MadsAp madsAp) {
        List<MadsAp> yhxwfxList = madsApMapper.yxhdfxList(madsAp);
        return yhxwfxList;
    }

    @Override
    public List<MadsAp> yxhdcyList(MadsAp madsAp) {
        List<MadsAp> yxhdcyList = madsApMapper.yxhdcyList(madsAp);
        return yxhdcyList;
    }

    @Override
    public List<MadsAp> yxhdymfxList(MadsAp madsAp) {
        List<MadsAp> yxhdymfxList = madsApMapper.yxhdymfxList(madsAp);
        return yxhdymfxList;
    }

    @Override
    public List<CyhdyhhyqkVo> cyhdyhhyqkList(MadsAp madsAp) {
        List<CyhdyhhyqkVo> cyhdyhhyqkList = madsApMapper.cyhdyhhyqkList(madsAp);
        return cyhdyhhyqkList;
    }

    @Override
    public CyPvUvVo cyPvUv(MadsAp madsAp) {
        CyPvUvVo cyPvUvkVo = madsApMapper.cyPvUv(madsAp);
        if(null != cyPvUvkVo){
            cyPvUvkVo.setPvtb("0.9");
            cyPvUvkVo.setUvtb("1");
            cyPvUvkVo.setLjuvtb("1");
            cyPvUvkVo.setXzysuvtb("1");
            cyPvUvkVo.setXzyhuvtb("1");

            cyPvUvkVo.setPvhb("-0.78");
            cyPvUvkVo.setUvhb("1");
            cyPvUvkVo.setLjuvhb("1");
            cyPvUvkVo.setXzysuvhb("1");
            cyPvUvkVo.setXzyhuvhb("1");
        }
        return cyPvUvkVo;
    }
    
    /**
     * 新增活动参与情况
     * 
     * @param madsAp 活动参与情况
     * @return 结果
     */
    @Override
    public int insertMadsAp(MadsAp madsAp)
    {
        return madsApMapper.insertMadsAp(madsAp);
    }

    /**
     * 修改活动参与情况
     * 
     * @param madsAp 活动参与情况
     * @return 结果
     */
    @Override
    public int updateMadsAp(MadsAp madsAp)
    {
        return madsApMapper.updateMadsAp(madsAp);
    }

    /**
     * 批量删除活动参与情况
     * 
     * @param ids 需要删除的活动参与情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsApByIds(Long[] ids)
    {
        return madsApMapper.deleteMadsApByIds(ids);
    }

    /**
     * 删除活动参与情况信息
     * 
     * @param id 活动参与情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsApById(Long id)
    {
        return madsApMapper.deleteMadsApById(id);
    }
}
