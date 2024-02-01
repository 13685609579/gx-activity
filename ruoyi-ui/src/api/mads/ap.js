import request from '@/utils/request'

// 查询活动参与情况列表
export function listAp(query) {
  return request({
    url: '/mads/ap/list',
    method: 'get',
    params: query
  })
}

// 查询活动参与情况详细
export function getAp(id) {
  return request({
    url: '/mads/ap/' + id,
    method: 'get'
  })
}

// 新增活动参与情况
export function addAp(data) {
  return request({
    url: '/mads/ap',
    method: 'post',
    data: data
  })
}

// 修改活动参与情况
export function updateAp(data) {
  return request({
    url: '/mads/ap',
    method: 'put',
    data: data
  })
}

// 删除活动参与情况
export function delAp(id) {
  return request({
    url: '/mads/ap/' + id,
    method: 'delete'
  })
}
