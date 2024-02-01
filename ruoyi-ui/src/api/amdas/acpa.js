import request from '@/utils/request'

export function getPeriodicals(query) {
  return request({
    url: '/amdas/acpa/getPeriodicals',
    method: 'get'
  })
}

// 查询活动交叉参与分析列表
export function listAcpa(query) {
  return request({
    url: '/amdas/acpa/list',
    method: 'get',
    params: query
  })
}

// 查询活动交叉参与分析详细
export function getAcpa(id) {
  return request({
    url: '/amdas/acpa/' + id,
    method: 'get'
  })
}

// 新增活动交叉参与分析
export function addAcpa(data) {
  return request({
    url: '/amdas/acpa',
    method: 'post',
    data: data
  })
}

// 修改活动交叉参与分析
export function updateAcpa(data) {
  return request({
    url: '/amdas/acpa',
    method: 'put',
    data: data
  })
}

// 删除活动交叉参与分析
export function delAcpa(id) {
  return request({
    url: '/amdas/acpa/' + id,
    method: 'delete'
  })
}
