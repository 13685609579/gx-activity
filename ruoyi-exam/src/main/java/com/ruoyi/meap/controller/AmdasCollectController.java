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
import com.ruoyi.meap.domain.AmdasCollect;
import com.ruoyi.meap.service.IAmdasCollectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 汇总Controller
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/amdas/collect")
public class AmdasCollectController extends BaseController
{
    @Autowired
    private IAmdasCollectService amdasCollectService;

    /**
     * 查询汇总列表
     */
    @PreAuthorize("@ss.hasPermi('amdas:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(AmdasCollect amdasCollect)
    {
        startPage();
        List<AmdasCollect> list = amdasCollectService.selectAmdasCollectList(amdasCollect);
        return getDataTable(list);
    }

    /**
     * 导出汇总列表
     */
    @PreAuthorize("@ss.hasPermi('amdas:collect:export')")
    @Log(title = "汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AmdasCollect amdasCollect)
    {
        List<AmdasCollect> list = amdasCollectService.selectAmdasCollectList(amdasCollect);
        ExcelUtil<AmdasCollect> util = new ExcelUtil<AmdasCollect>(AmdasCollect.class);
        util.exportExcel(response, list, "汇总数据");
    }

    /**
     * 获取汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('amdas:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(amdasCollectService.selectAmdasCollectById(id));
    }

    /**
     * 新增汇总
     */
    @PreAuthorize("@ss.hasPermi('amdas:collect:add')")
    @Log(title = "汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AmdasCollect amdasCollect)
    {
        return toAjax(amdasCollectService.insertAmdasCollect(amdasCollect));
    }

    /**
     * 修改汇总
     */
    @PreAuthorize("@ss.hasPermi('amdas:collect:edit')")
    @Log(title = "汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AmdasCollect amdasCollect)
    {
        return toAjax(amdasCollectService.updateAmdasCollect(amdasCollect));
    }

    /**
     * 删除汇总
     */
    @PreAuthorize("@ss.hasPermi('amdas:collect:remove')")
    @Log(title = "汇总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(amdasCollectService.deleteAmdasCollectByIds(ids));
    }
}
