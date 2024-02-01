package com.ruoyi.meap.service;

import java.util.List;
import com.ruoyi.meap.domain.AmdasCollect;

/**
 * 汇总Service接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface IAmdasCollectService 
{
    /**
     * 查询汇总
     * 
     * @param id 汇总主键
     * @return 汇总
     */
    public AmdasCollect selectAmdasCollectById(Long id);

    /**
     * 查询汇总列表
     * 
     * @param amdasCollect 汇总
     * @return 汇总集合
     */
    public List<AmdasCollect> selectAmdasCollectList(AmdasCollect amdasCollect);

    /**
     * 新增汇总
     * 
     * @param amdasCollect 汇总
     * @return 结果
     */
    public int insertAmdasCollect(AmdasCollect amdasCollect);

    /**
     * 修改汇总
     * 
     * @param amdasCollect 汇总
     * @return 结果
     */
    public int updateAmdasCollect(AmdasCollect amdasCollect);

    /**
     * 批量删除汇总
     * 
     * @param ids 需要删除的汇总主键集合
     * @return 结果
     */
    public int deleteAmdasCollectByIds(Long[] ids);

    /**
     * 删除汇总信息
     * 
     * @param id 汇总主键
     * @return 结果
     */
    public int deleteAmdasCollectById(Long id);
}
