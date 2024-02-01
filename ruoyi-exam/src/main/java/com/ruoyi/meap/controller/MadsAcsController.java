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
import com.ruoyi.meap.domain.MadsAcs;
import com.ruoyi.meap.service.IMadsAcsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动触点统计情况Controller
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/mads/acs")
public class MadsAcsController extends BaseController
{
    @Autowired
    private IMadsAcsService madsAcsService;

    /**
     * 查询活动触点统计情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:acs:list')")
    @GetMapping("/list")
    public TableDataInfo list(MadsAcs madsAcs)
    {
        startPage();
        List<MadsAcs> list = madsAcsService.selectMadsAcsList(madsAcs);
        return getDataTable(list);
    }

    /**
     * 导出活动触点统计情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:acs:export')")
    @Log(title = "活动触点统计情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MadsAcs madsAcs)
    {
        List<MadsAcs> list = madsAcsService.selectMadsAcsList(madsAcs);
        ExcelUtil<MadsAcs> util = new ExcelUtil<MadsAcs>(MadsAcs.class);
        util.exportExcel(response, list, "活动触点统计情况数据");
    }

    /**
     * 获取活动触点统计情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('mads:acs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(madsAcsService.selectMadsAcsById(id));
    }

    /**
     * 新增活动触点统计情况
     */
    @PreAuthorize("@ss.hasPermi('mads:acs:add')")
    @Log(title = "活动触点统计情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MadsAcs madsAcs)
    {
        return toAjax(madsAcsService.insertMadsAcs(madsAcs));
    }

    /**
     * 修改活动触点统计情况
     */
    @PreAuthorize("@ss.hasPermi('mads:acs:edit')")
    @Log(title = "活动触点统计情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MadsAcs madsAcs)
    {
        return toAjax(madsAcsService.updateMadsAcs(madsAcs));
    }

    /**
     * 删除活动触点统计情况
     */
    @PreAuthorize("@ss.hasPermi('mads:acs:remove')")
    @Log(title = "活动触点统计情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(madsAcsService.deleteMadsAcsByIds(ids));
    }
}
