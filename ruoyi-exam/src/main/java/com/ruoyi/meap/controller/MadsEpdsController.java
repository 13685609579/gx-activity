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
import com.ruoyi.meap.domain.MadsEpds;
import com.ruoyi.meap.service.IMadsEpdsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动奖品发放情况Controller
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/mads/epds")
public class MadsEpdsController extends BaseController
{
    @Autowired
    private IMadsEpdsService madsEpdsService;

    /**
     * 查询活动奖品发放情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:epds:list')")
    @GetMapping("/list")
    public TableDataInfo list(MadsEpds madsEpds)
    {
        startPage();
        List<MadsEpds> list = madsEpdsService.selectMadsEpdsList(madsEpds);
        return getDataTable(list);
    }

    /**
     * 导出活动奖品发放情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:epds:export')")
    @Log(title = "活动奖品发放情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MadsEpds madsEpds)
    {
        List<MadsEpds> list = madsEpdsService.selectMadsEpdsList(madsEpds);
        ExcelUtil<MadsEpds> util = new ExcelUtil<MadsEpds>(MadsEpds.class);
        util.exportExcel(response, list, "活动奖品发放情况数据");
    }

    /**
     * 获取活动奖品发放情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('mads:epds:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(madsEpdsService.selectMadsEpdsById(id));
    }

    /**
     * 新增活动奖品发放情况
     */
    @PreAuthorize("@ss.hasPermi('mads:epds:add')")
    @Log(title = "活动奖品发放情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MadsEpds madsEpds)
    {
        return toAjax(madsEpdsService.insertMadsEpds(madsEpds));
    }

    /**
     * 修改活动奖品发放情况
     */
    @PreAuthorize("@ss.hasPermi('mads:epds:edit')")
    @Log(title = "活动奖品发放情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MadsEpds madsEpds)
    {
        return toAjax(madsEpdsService.updateMadsEpds(madsEpds));
    }

    /**
     * 删除活动奖品发放情况
     */
    @PreAuthorize("@ss.hasPermi('mads:epds:remove')")
    @Log(title = "活动奖品发放情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(madsEpdsService.deleteMadsEpdsByIds(ids));
    }
}
