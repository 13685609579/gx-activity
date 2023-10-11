package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.service.UnitManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 考生单位管理表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
@RestController
@RequestMapping("/unit-manage")
public class UnitManageController extends BaseController {

    @Autowired
    private UnitManageService unitManageService;

    /**
     * 获取所有单位
     * @return
     */
    @GetMapping("/getUnitNames")
    @Anonymous
    public TableDataInfo getUnitNames()
    {
        startPage();
        List<SysDept> list = unitManageService.getUnitNames();
        return getDataTable(list);
    }

}

