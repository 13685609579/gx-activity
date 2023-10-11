import request from '@/utils/request'
//获取考生信息列表
export function getlist(query) {
    return request({
        url: 'statistical-analysis/list',
        method: 'get',
        params: query
    })
}

//修改考生状态
export function updatePersonState(query) {
    return request({
        url: 'candidate-info/updatePersonState',
        method: 'get',
        params: query
    })
}