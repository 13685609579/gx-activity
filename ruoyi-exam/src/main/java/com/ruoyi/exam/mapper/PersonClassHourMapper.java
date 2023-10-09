package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.PersonClassHour;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 人员学时表 Mapper 接口
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
public interface PersonClassHourMapper extends BaseMapper<PersonClassHour> {

    /**
     * 获取当前考生已获学时
     * @param personClassHour
     * @return
     */
    public String getAcquiredHours(PersonClassHour personClassHour);

}
