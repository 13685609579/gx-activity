package com.ruoyi.meap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ruoyi.meap.domain.vo.CyPvUvVo;
import com.ruoyi.meap.domain.vo.CyhdyhhyqkVo;
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
import com.ruoyi.meap.domain.MadsAp;
import com.ruoyi.meap.service.IMadsApService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动参与情况Controller
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/mads/ap")
public class MadsApController extends BaseController
{
    @Autowired
    private IMadsApService madsApService;

    /**
     * 查询活动参与情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:list')")
    @GetMapping("/list")
    public TableDataInfo list(MadsAp madsAp)
    {
        startPage();
        List<MadsAp> list = madsApService.selectMadsApList(madsAp);
        return getDataTable(list);
    }
    /**
     * 活动页面用户行为分析
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:yxhdfxList')")
    @GetMapping("/yxhdfxList")
    public AjaxResult yxhdfxList(MadsAp meapAp)
    {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> pieList = new ArrayList<>();
        List<MadsAp> yxhdfxList = madsApService.yxhdfxList(meapAp);
        if(CollectionUtils.isNotEmpty(yxhdfxList)){
            yxhdfxList.stream().forEach(m->{
                Map<String, String> data = new HashMap<>(2);
                data.put("name", m.getCity());
                data.put("value", String.valueOf(m.getPv()));
                pieList.add(data);
            });
        }
        result.put("yxhdfxList", pieList);
        return AjaxResult.success(result);
    }

    /**
     * 活动页面日参与用户数
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:yxhdcyList')")
    @GetMapping("/yxhdcyList")
    public AjaxResult yxhdcyList(MadsAp meapAp)
    {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> pieList = new ArrayList<>();
        List<MadsAp> yxhdcyList = madsApService.yxhdcyList(meapAp);
        if(CollectionUtils.isNotEmpty(yxhdcyList)){
            yxhdcyList.stream().forEach(m->{
                Map<String, String> data = new HashMap<>(2);
                data.put("name", m.getCity());
                data.put("value", String.valueOf(m.getUv()));
                pieList.add(data);
            });
        }
        result.put("yxhdcyList", pieList);
        return AjaxResult.success(result);
    }

    /**
     * 营销活动页面分析
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:yxhdymfxList')")
    @GetMapping("/yxhdymfxList")
    public AjaxResult yxhdymfxList(MadsAp meapAp)
    {
        List<MadsAp> yxhdymfxList = madsApService.yxhdymfxList(meapAp);
        return success(yxhdymfxList);
    }

    /**
     * 参与活动用户活跃情况
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:cyhdyhhyqkList')")
    @GetMapping("/cyhdyhhyqkList")
    public AjaxResult cyhdyhhyqkList(MadsAp meapAp)
    {
        List<CyhdyhhyqkVo> cyhdyhhyqkList = madsApService.cyhdyhhyqkList(meapAp);
        return success(cyhdyhhyqkList);
    }

    /**
     * 参与活动浏览数用户活跃数
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:cyPvUv')")
    @GetMapping("/cyPvUv")
    public AjaxResult cyPvUv(MadsAp meapAp)
    {
        CyPvUvVo cyPvUvkVo = madsApService.cyPvUv(meapAp);
        return success(cyPvUvkVo);
    }
    

    /**
     * 导出活动参与情况列表
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:export')")
    @Log(title = "活动参与情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MadsAp madsAp)
    {
        List<MadsAp> list = madsApService.selectMadsApList(madsAp);
        ExcelUtil<MadsAp> util = new ExcelUtil<MadsAp>(MadsAp.class);
        util.exportExcel(response, list, "活动参与情况数据");
    }

    /**
     * 获取活动参与情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(madsApService.selectMadsApById(id));
    }

    /**
     * 新增活动参与情况
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:add')")
    @Log(title = "活动参与情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MadsAp madsAp)
    {
        return toAjax(madsApService.insertMadsAp(madsAp));
    }

    /**
     * 修改活动参与情况
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:edit')")
    @Log(title = "活动参与情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MadsAp madsAp)
    {
        return toAjax(madsApService.updateMadsAp(madsAp));
    }

    /**
     * 删除活动参与情况
     */
    @PreAuthorize("@ss.hasPermi('mads:ap:remove')")
    @Log(title = "活动参与情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(madsApService.deleteMadsApByIds(ids));
    }
}
