package com.ruoyi.meap.mapper;

import java.util.List;
import com.ruoyi.meap.domain.AmdasCollect;

/**
 * 汇总Mapper接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface AmdasCollectMapper 
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
     * 删除汇总
     * 
     * @param id 汇总主键
     * @return 结果
     */
    public int deleteAmdasCollectById(Long id);

    /**
     * 批量删除汇总
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAmdasCollectByIds(Long[] ids);
}
