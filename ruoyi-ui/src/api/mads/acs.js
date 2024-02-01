import request from '@/utils/request'

// 查询活动触点统计情况列表
export function listAcs(query) {
  return request({
    url: '/mads/acs/list',
    method: 'get',
    params: query
  })
}

// 查询活动触点统计情况详细
export function getAcs(id) {
  return request({
    url: '/mads/acs/' + id,
    method: 'get'
  })
}

// 新增活动触点统计情况
export function addAcs(data) {
  return request({
    url: '/mads/acs',
    method: 'post',
    data: data
  })
}

// 修改活动触点统计情况
export function updateAcs(data) {
  return request({
    url: '/mads/acs',
    method: 'put',
    data: data
  })
}

// 删除活动触点统计情况
export function delAcs(id) {
  return request({
    url: '/mads/acs/' + id,
    method: 'delete'
  })
}
