package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 题库管理表 question_bank_manage（用于题库导入功能）
 *
 * @author yxp
 */
public class QuestionBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号", cellType = ColumnType.NUMERIC, prompt = "序号")
    private Long rowNum;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String topicContent;

    /** 题目分类（题目分类（数据字典编码）） */
    @Excel(name = "题目分类", readConverterExp = "30=习近平的法治思想,31=民法典,32=宪法,33=党内法规,34=行政处罚法", combo = {"习近平的法治思想","民法典","宪法","党内法规","行政处罚法"})
    private String topicSort;

    /** 题目类型（1：单选题 2：多选题 3：判断题） */
    @Excel(name = "题目类型", readConverterExp = "1=单选题,2=多选题,3=判断题", combo = {"单选题","多选题","判断题"})
    private String topicType;

    /** 选项一 */
    @Excel(name = "选项一")
    private String optionOne;

    /** 选项二 */
    @Excel(name = "选项二")
    private String optionTwo;

    /** 选项三 */
    @Excel(name = "选项三")
    private String optionThree;

    /** 选项四 */
    @Excel(name = "选项四")
    private String optionFour;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String topicAnswer;

    /** 答案解析 */
    @Excel(name = "答案解析")
    private String answerAnalysis;

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicSort() {
        return topicSort;
    }

    public void setTopicSort(String topicSort) {
        this.topicSort = topicSort;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public String getTopicAnswer() {
        return topicAnswer;
    }

    public void setTopicAnswer(String topicAnswer) {
        this.topicAnswer = topicAnswer;
    }

    public String getAnswerAnalysis() {
        return answerAnalysis;
    }

    public void setAnswerAnalysis(String answerAnalysis) {
        this.answerAnalysis = answerAnalysis;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rowNum", getRowNum())
            .append("topicContent", getTopicContent())
            .append("topicSort", getTopicSort())
            .append("topicType", getTopicType())
            .append("optionOne", getOptionOne())
            .append("optionTwo", getOptionTwo())
            .append("optionThree", getOptionThree())
            .append("optionFour", getOptionFour())
            .append("topicAnswer", getTopicAnswer())
            .append("answerAnalysis", getAnswerAnalysis())
            .toString();
    }
}
