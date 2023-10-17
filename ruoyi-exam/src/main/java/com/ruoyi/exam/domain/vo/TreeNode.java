package com.ruoyi.exam.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {

    /** 节点ID */
    private Long id;

    /** 父节点ID：顶级节点为0 */
    private Long parentId;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    private List<TreeNode> children;

}
