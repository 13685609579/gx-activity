import request from '@/utils/request'

// 营销活动页面浏览次数分析
export function yxhdfxList(query) {
  return request({
    url: '/mads/ap/yxhdfxList',
    method: 'get',
    params: query
  })
}

// 营销活动日参与用户数分析
export function yxhdcyList(query) {
  return request({
    url: '/mads/ap/yxhdcyList',
    method: 'get',
    params: query
  })
}

// 营销活动活动页面分析
export function yxhdymfxList(query) {
  return request({
    url: '/mads/ap/yxhdymfxList',
    method: 'get',
    params: query
  })
}

// 参与活动用户活跃情况
export function cyhdyhhyqkList(query) {
  return request({
    url: '/mads/ap/cyhdyhhyqkList',
    method: 'get',
    params: query
  })
}

// 参与活动浏览数用户活跃数
export function cyPvUv(query) {
  return request({
    url: '/mads/ap/cyPvUv',
    method: 'get',
    params: query
  })
}
