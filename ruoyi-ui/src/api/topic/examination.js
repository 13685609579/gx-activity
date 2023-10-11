import request from '@/utils/request'
//获取考试管理列表
export function getmanagelist(query) {
    return request({
        url: 'exam-manage/list',
        method: 'get',
        params: query
    })
}

// 新增考试项目
export function insertExamManageData(data) {
    return request({
        url: 'exam-manage/insertExamManageData',
        method: 'post',
        data: data
    })
}

// 修改考试项目
export function updateExamManage(data) {
    return request({
        url: 'exam-manage/updateExamManage',
        method: 'post',
        data: data
    })
}

//获取考试项目详情
export function examManageInfo(query) {
    return request({
        url: 'exam-manage/examManageInfo',
        method: 'get',
        params: query
    })
}

//修改发布状态
export function updatePublicState(query) {
    return request({
        url: 'exam-manage/updatePublicState',
        method: 'get',
        params: query
    })
}
