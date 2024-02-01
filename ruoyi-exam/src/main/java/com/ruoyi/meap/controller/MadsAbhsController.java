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
import com.ruoyi.meap.domain.MadsAbhs;
import com.ruoyi.meap.service.IMadsAbhsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动业务办理情况Controller
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/mads/abhs")
public class MadsAbhsController extends BaseController
{
    @Autowired
    private IMadsAbhsService madsAbhsService;

    /**
     * 查询活动业务办理情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:abhs:list')")
    @GetMapping("/list")
    public TableDataInfo list(MadsAbhs madsAbhs)
    {
        startPage();
        List<MadsAbhs> list = madsAbhsService.selectMadsAbhsList(madsAbhs);
        return getDataTable(list);
    }

    /**
     * 导出活动业务办理情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:abhs:export')")
    @Log(title = "活动业务办理情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MadsAbhs madsAbhs)
    {
        List<MadsAbhs> list = madsAbhsService.selectMadsAbhsList(madsAbhs);
        ExcelUtil<MadsAbhs> util = new ExcelUtil<MadsAbhs>(MadsAbhs.class);
        util.exportExcel(response, list, "活动业务办理情况数据");
    }

    /**
     * 获取活动业务办理情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('mads:abhs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(madsAbhsService.selectMadsAbhsById(id));
    }

    /**
     * 新增活动业务办理情况
     */
    @PreAuthorize("@ss.hasPermi('mads:abhs:add')")
    @Log(title = "活动业务办理情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MadsAbhs madsAbhs)
    {
        return toAjax(madsAbhsService.insertMadsAbhs(madsAbhs));
    }

    /**
     * 修改活动业务办理情况
     */
    @PreAuthorize("@ss.hasPermi('mads:abhs:edit')")
    @Log(title = "活动业务办理情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MadsAbhs madsAbhs)
    {
        return toAjax(madsAbhsService.updateMadsAbhs(madsAbhs));
    }

    /**
     * 删除活动业务办理情况
     */
    @PreAuthorize("@ss.hasPermi('mads:abhs:remove')")
    @Log(title = "活动业务办理情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(madsAbhsService.deleteMadsAbhsByIds(ids));
    }
}
