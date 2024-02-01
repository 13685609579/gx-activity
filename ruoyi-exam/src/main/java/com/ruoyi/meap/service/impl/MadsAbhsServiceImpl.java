package com.ruoyi.meap.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.MadsAbhsMapper;
import com.ruoyi.meap.domain.MadsAbhs;
import com.ruoyi.meap.service.IMadsAbhsService;

/**
 * 活动业务办理情况Service业务层处理
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@Service
public class MadsAbhsServiceImpl implements IMadsAbhsService 
{
    @Autowired
    private MadsAbhsMapper madsAbhsMapper;

    /**
     * 查询活动业务办理情况
     * 
     * @param id 活动业务办理情况主键
     * @return 活动业务办理情况
     */
    @Override
    public MadsAbhs selectMadsAbhsById(Long id)
    {
        return madsAbhsMapper.selectMadsAbhsById(id);
    }

    /**
     * 查询活动业务办理情况列表
     * 
     * @param madsAbhs 活动业务办理情况
     * @return 活动业务办理情况
     */
    @Override
    public List<MadsAbhs> selectMadsAbhsList(MadsAbhs madsAbhs)
    {
        return madsAbhsMapper.selectMadsAbhsList(madsAbhs);
    }

    /**
     * 新增活动业务办理情况
     * 
     * @param madsAbhs 活动业务办理情况
     * @return 结果
     */
    @Override
    public int insertMadsAbhs(MadsAbhs madsAbhs)
    {
        return madsAbhsMapper.insertMadsAbhs(madsAbhs);
    }

    /**
     * 修改活动业务办理情况
     * 
     * @param madsAbhs 活动业务办理情况
     * @return 结果
     */
    @Override
    public int updateMadsAbhs(MadsAbhs madsAbhs)
    {
        return madsAbhsMapper.updateMadsAbhs(madsAbhs);
    }

    /**
     * 批量删除活动业务办理情况
     * 
     * @param ids 需要删除的活动业务办理情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsAbhsByIds(Long[] ids)
    {
        return madsAbhsMapper.deleteMadsAbhsByIds(ids);
    }

    /**
     * 删除活动业务办理情况信息
     * 
     * @param id 活动业务办理情况主键
     * @return 结果
     */
    @Override
    public int deleteMadsAbhsById(Long id)
    {
        return madsAbhsMapper.deleteMadsAbhsById(id);
    }
}
