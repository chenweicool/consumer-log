/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: resolve => require(['../pages/Login.vue'], resolve)
    },

    {
      path: '/Home',
      component: resolve => require(['../components/Home.vue'], resolve),
      children:[
        {
          path: '/Info',
          component: resolve => require(['../pages/InfoPage.vue'], resolve)
        },
        {
          path: '/Consumer',
          component: resolve => require(['../pages/ConsumerPage.vue'], resolve)
        },
        {
          path: '/Location',
          component: resolve => require(['../pages/locationPage.vue'], resolve)
        },
        {
          path: '/Order',
          component: resolve => require(['../pages/OrderPage.vue'], resolve)
        },

        // 测试的组件
        // {
        //   path: '/Test',
        //   component: resolve => require(['../pages/Test.vue'], resolve)
        // },
        
        // {
        //   path: '/ListSong',
        //   component: resolve => require(['../pages/ListSongPage.vue'], resolve)
        // },
        // {
        //   path: '/collect',
        //   component: resolve => require(['../pages/CollectPage.vue'], resolve)
        // }
      ]
    }   
    
  ]
})
