import request from '@/utils/request'

// 查询活动业务办理情况列表
export function listAbhs(query) {
  return request({
    url: '/mads/abhs/list',
    method: 'get',
    params: query
  })
}

// 查询活动业务办理情况详细
export function getAbhs(id) {
  return request({
    url: '/mads/abhs/' + id,
    method: 'get'
  })
}

// 新增活动业务办理情况
export function addAbhs(data) {
  return request({
    url: '/mads/abhs',
    method: 'post',
    data: data
  })
}

// 修改活动业务办理情况
export function updateAbhs(data) {
  return request({
    url: '/mads/abhs',
    method: 'put',
    data: data
  })
}

// 删除活动业务办理情况
export function delAbhs(id) {
  return request({
    url: '/mads/abhs/' + id,
    method: 'delete'
  })
}
