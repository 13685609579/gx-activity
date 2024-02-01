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
import com.ruoyi.meap.domain.AmdasApa;
import com.ruoyi.meap.service.IAmdasApaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动参与分析Controller
 * 
 * @author youxiuping
 * @date 2024-01-31
 */
@RestController
@RequestMapping("/amdas/apa")
public class AmdasApaController extends BaseController
{
    @Autowired
    private IAmdasApaService amdasApaService;

    /**
     * 查询活动参与分析列表
     */
    @PreAuthorize("@ss.hasPermi('amdas:apa:list')")
    @GetMapping("/list")
    public TableDataInfo list(AmdasApa amdasApa)
    {
        startPage();
        List<AmdasApa> list = amdasApaService.selectAmdasApaList(amdasApa);
        return getDataTable(list);
    }

    /**
     * 导出活动参与分析列表
     */
    @PreAuthorize("@ss.hasPermi('amdas:apa:export')")
    @Log(title = "活动参与分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AmdasApa amdasApa)
    {
        List<AmdasApa> list = amdasApaService.selectAmdasApaList(amdasApa);
        ExcelUtil<AmdasApa> util = new ExcelUtil<AmdasApa>(AmdasApa.class);
        util.exportExcel(response, list, "活动参与分析数据");
    }

    /**
     * 获取活动参与分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('amdas:apa:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(amdasApaService.selectAmdasApaById(id));
    }

    /**
     * 新增活动参与分析
     */
    @PreAuthorize("@ss.hasPermi('amdas:apa:add')")
    @Log(title = "活动参与分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AmdasApa amdasApa)
    {
        return toAjax(amdasApaService.insertAmdasApa(amdasApa));
    }

    /**
     * 修改活动参与分析
     */
    @PreAuthorize("@ss.hasPermi('amdas:apa:edit')")
    @Log(title = "活动参与分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AmdasApa amdasApa)
    {
        return toAjax(amdasApaService.updateAmdasApa(amdasApa));
    }

    /**
     * 删除活动参与分析
     */
    @PreAuthorize("@ss.hasPermi('amdas:apa:remove')")
    @Log(title = "活动参与分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(amdasApaService.deleteAmdasApaByIds(ids));
    }
}
