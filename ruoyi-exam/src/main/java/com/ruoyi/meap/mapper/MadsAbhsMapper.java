package com.ruoyi.meap.mapper;

import java.util.List;
import com.ruoyi.meap.domain.MadsAbhs;

/**
 * 活动业务办理情况Mapper接口
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public interface MadsAbhsMapper 
{
    /**
     * 查询活动业务办理情况
     * 
     * @param id 活动业务办理情况主键
     * @return 活动业务办理情况
     */
    public MadsAbhs selectMadsAbhsById(Long id);

    /**
     * 查询活动业务办理情况列表
     * 
     * @param madsAbhs 活动业务办理情况
     * @return 活动业务办理情况集合
     */
    public List<MadsAbhs> selectMadsAbhsList(MadsAbhs madsAbhs);

    /**
     * 新增活动业务办理情况
     * 
     * @param madsAbhs 活动业务办理情况
     * @return 结果
     */
    public int insertMadsAbhs(MadsAbhs madsAbhs);

    /**
     * 修改活动业务办理情况
     * 
     * @param madsAbhs 活动业务办理情况
     * @return 结果
     */
    public int updateMadsAbhs(MadsAbhs madsAbhs);

    /**
     * 删除活动业务办理情况
     * 
     * @param id 活动业务办理情况主键
     * @return 结果
     */
    public int deleteMadsAbhsById(Long id);

    /**
     * 批量删除活动业务办理情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMadsAbhsByIds(Long[] ids);
}
