import request from '@/utils/request'

export function getChat(data) {
  return request({
    // url: '/vue-element-admin/user/login',
    // method: 'post',
    // data
    url: '/rabbit/system/chat/getUserChat',
    method: 'post',
    data
  })
}

