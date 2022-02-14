import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)


export default new Router({
  routes: [
    {                    
      path: '/',
      component: (resolve) => require(['@/components/login'], resolve),
    },
    {                    
      path: '/toppage',
      component: (resolve) => require(['@/components/page/toppage'], resolve),
    },
    {                    
      path: '/leftpage',
      component: (resolve) => require(['@/components/page/leftpage'], resolve),
    },
    {                   
      path: '/index', 
      component: (resolve) => require(['@/components/index'], resolve),
    },

  ]
  
})
