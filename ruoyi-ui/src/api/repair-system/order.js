import request from '@/utils/request'

// 查询报修信息列表
export function listOrder(query) {
  return request({
    url: '/repair-system/order/list',
    method: 'get',
    params: query
  })
}

// 查询报修信息详细
export function getOrder(id) {
  return request({
    url: '/repair-system/order/' + id,
    method: 'get'
  })
}

// 新增报修信息
export function addOrder(data) {
  return request({
    url: '/repair-system/order',
    method: 'post',
    data: data
  })
}

// 修改报修信息
export function updateOrder(data) {
  return request({
    url: '/repair-system/order',
    method: 'put',
    data: data
  })
}

// 删除报修信息
export function delOrder(id) {
  return request({
    url: '/repair-system/order/' + id,
    method: 'delete'
  })
}
