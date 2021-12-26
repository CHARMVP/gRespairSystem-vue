import request from '@/utils/request'

// 测试下载二维码
export function listOrder(query) {
  return request({
    url: '/repair-system/test',
    method: 'get',
    // params: query
  })
}
