import request from '@/utils/request'

// 查询活动参与分析列表
export function listApa(query) {
  return request({
    url: '/amdas/apa/list',
    method: 'get',
    params: query
  })
}

// 查询活动参与分析详细
export function getApa(id) {
  return request({
    url: '/amdas/apa/' + id,
    method: 'get'
  })
}

// 新增活动参与分析
export function addApa(data) {
  return request({
    url: '/amdas/apa',
    method: 'post',
    data: data
  })
}

// 修改活动参与分析
export function updateApa(data) {
  return request({
    url: '/amdas/apa',
    method: 'put',
    data: data
  })
}

// 删除活动参与分析
export function delApa(id) {
  return request({
    url: '/amdas/apa/' + id,
    method: 'delete'
  })
}
