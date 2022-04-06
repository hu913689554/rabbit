import request from '@/utils/request'

export function login(data) {
  return request({
    // url: '/vue-element-admin/user/login',
    // method: 'post',
    // data
    url: '/rabbit/system/post/postLogin',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  // return request({
  //   url: '/vue-element-admin/user/info',
  //   method: 'get',
  //   params: { token }
  // })
  return request({
    url: '/rabbit/system/post/getinfo',
    method: 'post'
    // params: { token }
  })
}

export function getMenu(token) {
  // return request({
  //   url: '/vue-element-admin/user/info',
  //   method: 'get',
  //   params: { token }
  // })
  return request({
    url: '/rabbit/menuLogin',
    method: 'post'
    // params: { token }
  })
}

export function getUserList(data) {
  // return request({
  //   url: '/vue-element-admin/user/info',
  //   method: 'get',
  //   params: { token }
  // })
  return request({
    url: '/rabbit/user/getuserlist', // ?pageNum='+data.pageNum+'&pagesize='+data.pagesize,
    method: 'get',
    params: data
    // params: { token }

  })
}

export function postInsertUser(data) {
  // return request({
  //   url: '/vue-element-admin/user/info',
  //   method: 'get',
  //   params: { token }
  // })
  return request({
    url: '/rabbit/user/inseruser', // ?pageNum='+data.pageNum+'&pagesize='+data.pagesize,
    method: 'post',
    data
    // params: { token }

  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}
