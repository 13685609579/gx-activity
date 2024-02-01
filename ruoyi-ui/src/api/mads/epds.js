import request from '@/utils/request'

// 查询活动奖品发放情况列表
export function listEpds(query) {
  return request({
    url: '/mads/epds/list',
    method: 'get',
    params: query
  })
}

// 查询活动奖品发放情况详细
export function getEpds(id) {
  return request({
    url: '/mads/epds/' + id,
    method: 'get'
  })
}

// 新增活动奖品发放情况
export function addEpds(data) {
  return request({
    url: '/mads/epds',
    method: 'post',
    data: data
  })
}

// 修改活动奖品发放情况
export function updateEpds(data) {
  return request({
    url: '/mads/epds',
    method: 'put',
    data: data
  })
}

// 删除活动奖品发放情况
export function delEpds(id) {
  return request({
    url: '/mads/epds/' + id,
    method: 'delete'
  })
}
