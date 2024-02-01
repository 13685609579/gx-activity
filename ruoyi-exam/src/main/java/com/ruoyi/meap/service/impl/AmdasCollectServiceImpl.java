package com.ruoyi.meap.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meap.mapper.AmdasCollectMapper;
import com.ruoyi.meap.domain.AmdasCollect;
import com.ruoyi.meap.service.IAmdasCollectService;

/**
 * 汇总Service业务层处理
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@Service
public class AmdasCollectServiceImpl implements IAmdasCollectService 
{
    @Autowired
    private AmdasCollectMapper amdasCollectMapper;

    /**
     * 查询汇总
     * 
     * @param id 汇总主键
     * @return 汇总
     */
    @Override
    public AmdasCollect selectAmdasCollectById(Long id)
    {
        return amdasCollectMapper.selectAmdasCollectById(id);
    }

    /**
     * 查询汇总列表
     * 
     * @param amdasCollect 汇总
     * @return 汇总
     */
    @Override
    public List<AmdasCollect> selectAmdasCollectList(AmdasCollect amdasCollect)
    {
        return amdasCollectMapper.selectAmdasCollectList(amdasCollect);
    }

    /**
     * 新增汇总
     * 
     * @param amdasCollect 汇总
     * @return 结果
     */
    @Override
    public int insertAmdasCollect(AmdasCollect amdasCollect)
    {
        return amdasCollectMapper.insertAmdasCollect(amdasCollect);
    }

    /**
     * 修改汇总
     * 
     * @param amdasCollect 汇总
     * @return 结果
     */
    @Override
    public int updateAmdasCollect(AmdasCollect amdasCollect)
    {
        return amdasCollectMapper.updateAmdasCollect(amdasCollect);
    }

    /**
     * 批量删除汇总
     * 
     * @param ids 需要删除的汇总主键
     * @return 结果
     */
    @Override
    public int deleteAmdasCollectByIds(Long[] ids)
    {
        return amdasCollectMapper.deleteAmdasCollectByIds(ids);
    }

    /**
     * 删除汇总信息
     * 
     * @param id 汇总主键
     * @return 结果
     */
    @Override
    public int deleteAmdasCollectById(Long id)
    {
        return amdasCollectMapper.deleteAmdasCollectById(id);
    }
}
