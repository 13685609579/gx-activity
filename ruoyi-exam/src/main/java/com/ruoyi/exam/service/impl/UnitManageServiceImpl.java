package com.ruoyi.exam.service.impl;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;
import com.ruoyi.exam.mapper.UnitManageMapper;
import com.ruoyi.exam.service.UnitManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 考生单位管理表 服务实现类
 * </p>
 *
 * @author hougq
 * @since 2023-10-08
 */
@Service
public class UnitManageServiceImpl extends ServiceImpl<UnitManageMapper, UnitManage> implements UnitManageService {

    @Autowired
    private UnitManageMapper unitManageMapper;

    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 获取所有单位
     * @return
     */
    @Override
    public List<SysDept> getUnitNames() {
        SysDept dept = new SysDept();
        List<SysDept> deptList = deptMapper.selectDeptList(dept);
        /*List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        if(null != deptList && deptList.size()>0){
            for(SysDept sysDept: deptList){
                TreeNode node = new TreeNode();
                node.setId(sysDept.getDeptId());
                node.setParentId(sysDept.getParentId());
                node.setLabel(sysDept.getDeptName());
                treeNodeList.add(node);
            }
            // 创建树形结构（数据集合作为参数）
            TreeBuild treeBuild = new TreeBuild(treeNodeList);
            // 原查询结果转换树形结构
            treeNodeList = treeBuild.buildTree();
        }*/
        return deptList;
    }

    /**
     * 根据分页获取所有单位
     * @param candidateClassHourVo
     * @return
     */
    @Override
    public List<UnitManage> selectUnitList(CandidateClassHourVo candidateClassHourVo) {
        List<UnitManage> unitManageList = unitManageMapper.selectUnitList(candidateClassHourVo);
        return unitManageList;
    }
}
