package com.ruoyi.meap.domain.vo;

import lombok.Data;

/**
 * 参与活动浏览数用户活跃数
 *
 * @author ruoyi
 * @date 2024-01-25
 */
@Data
public class CyPvUvVo {

    private Long pv; //pv
    private Long uv; //日参与用户数
    private Long ljuv; //参与活动用户累计（剔重）
    private Long xzysuv; //参与活动新增原生用户数
    private Long xzyhuv; //参与活动新增月活用户数

    private String pvtb; //pv同比
    private String uvtb; //日参与用户数同比
    private String ljuvtb; //参与活动用户累计（剔重）同比
    private String xzysuvtb; //参与活动新增原生用户数同比
    private String xzyhuvtb; //参与活动新增月活用户数同比

    private String pvhb; //pv环比
    private String uvhb; //日参与用户数环比
    private String ljuvhb; //参与活动用户累计（剔重）环比
    private String xzysuvhb; //参与活动新增原生用户数环比
    private String xzyhuvhb; //参与活动新增月活用户数环比

}
