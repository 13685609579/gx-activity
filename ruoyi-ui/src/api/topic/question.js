import request from '@/utils/request'
//获取题库列表
export function questionlist(query) {
    return request({
        url: 'question-bank-manage/list',
        method: 'get',
        params: query
    })
}

//获取题目分类、题目题型
export function datalist(query) {
    return request({
        url: 'system/dict/data/list',
        method: 'get',
        params: query
    })
}

//获取题目编号
export function topicCode(query) {
    return request({
        url: 'question-bank-manage/getTopicCode',
        method: 'get',
        params: query
    })
}

// 新增题库题目
export function addQuestionBank(data) {
    return request({
        url: 'question-bank-manage/addQuestionBank',
        method: 'post',
        data: data
    })
}

//获取题目详情
export function questionBankInfo(query) {
    return request({
        url: 'question-bank-manage/questionBankInfo',
        method: 'get',
        params: query
    })
}

// 修改题目
export function updateQuestionBank(data) {
    return request({
        url: 'question-bank-manage/updateQuestionBank',
        method: 'post',
        data: data
    })
}

//删除题目
export function removeQuestionBank(query) {
    return request({
        url: 'question-bank-manage/removeQuestionBank',
        method: 'get',
        params: query
    })
}
