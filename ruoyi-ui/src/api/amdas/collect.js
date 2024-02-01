import request from '@/utils/request'

// 查询汇总列表
export function listCollect(query) {
  return request({
    url: '/amdas/collect/list',
    method: 'get',
    params: query
  })
}

// 查询汇总详细
export function getCollect(id) {
  return request({
    url: '/amdas/collect/' + id,
    method: 'get'
  })
}

// 新增汇总
export function addCollect(data) {
  return request({
    url: '/amdas/collect',
    method: 'post',
    data: data
  })
}

// 修改汇总
export function updateCollect(data) {
  return request({
    url: '/amdas/collect',
    method: 'put',
    data: data
  })
}

// 删除汇总
export function delCollect(id) {
  return request({
    url: '/amdas/collect/' + id,
    method: 'delete'
  })
}
