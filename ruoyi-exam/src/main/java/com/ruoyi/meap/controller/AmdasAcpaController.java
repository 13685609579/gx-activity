package com.ruoyi.meap.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.meap.domain.AmdasAcpa;
import com.ruoyi.meap.service.IAmdasAcpaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动交叉参与分析Controller
 * 
 * @author ruoyi
 * @date 2024-01-31
 */
@RestController
@RequestMapping("/amdas/acpa")
public class AmdasAcpaController extends BaseController
{
    @Autowired
    private IAmdasAcpaService amdasAcpaService;

    @GetMapping(value = "/getPeriodicals")
    public AjaxResult getPeriodicals()
    {
        return success(amdasAcpaService.getPeriodicals());
    }

    /**
     * 查询活动交叉参与分析列表
     */
    @PreAuthorize("@ss.hasPermi('amdas:acpa:list')")
    @GetMapping("/list")
    public TableDataInfo list(AmdasAcpa amdasAcpa)
    {
        startPage();
        List<AmdasAcpa> list = amdasAcpaService.selectAmdasAcpaList(amdasAcpa);
        return getDataTable(list);
    }

    /**
     * 导出活动交叉参与分析列表
     */
    @PreAuthorize("@ss.hasPermi('amdas:acpa:export')")
    @Log(title = "活动交叉参与分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AmdasAcpa amdasAcpa)
    {
        List<AmdasAcpa> list = amdasAcpaService.selectAmdasAcpaList(amdasAcpa);
        ExcelUtil<AmdasAcpa> util = new ExcelUtil<AmdasAcpa>(AmdasAcpa.class);
        util.exportExcel(response, list, "活动交叉参与分析数据");
    }

    /**
     * 获取活动交叉参与分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('amdas:acpa:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(amdasAcpaService.selectAmdasAcpaById(id));
    }

    /**
     * 新增活动交叉参与分析
     */
    @PreAuthorize("@ss.hasPermi('amdas:acpa:add')")
    @Log(title = "活动交叉参与分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AmdasAcpa amdasAcpa)
    {
        return toAjax(amdasAcpaService.insertAmdasAcpa(amdasAcpa));
    }

    /**
     * 修改活动交叉参与分析
     */
    @PreAuthorize("@ss.hasPermi('amdas:acpa:edit')")
    @Log(title = "活动交叉参与分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AmdasAcpa amdasAcpa)
    {
        return toAjax(amdasAcpaService.updateAmdasAcpa(amdasAcpa));
    }

    /**
     * 删除活动交叉参与分析
     */
    @PreAuthorize("@ss.hasPermi('amdas:acpa:remove')")
    @Log(title = "活动交叉参与分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(amdasAcpaService.deleteAmdasAcpaByIds(ids));
    }
}
