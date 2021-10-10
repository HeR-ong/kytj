import Vue from 'vue'
import Router from 'vue-router'

import welcome from '../page/welcome/index'
import userList from '../page/user/userList'
import scoreList from '../page/score/scoreList'
import userDetail from '../page/user/userDetail'
import index from '../index'
import appVue from '../App'
import login from '../login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: appVue,
      meta: {
        title: '首页',
        type: 'login'
      },
      children: [
        {
          path: '',
          component: index,
          meta: {
            title: '首页',
            type: 'login'
          },
          children: [
            {
              path: '/welcome',
              component: welcome,
              meta: {
                title: '首页',
                type: 'login'
              }
            },
            {
              path: '/scoreList',
              component: scoreList,
              meta: {
                title: '考研历史数据',
                type: 'login'
              }
            },
            {
              path: '/userDetail',
              component: userDetail,
              meta: {
                title: '用户详情',
                type: 'login'
              }
            },
            {
              path: '/userList',
              component: userList,
              meta: {
                title: '用户列表',
                type: 'login'
              }
            }
          ]
        },
        {
          path: '/login',
          component: login,
          meta: {
            title: '登录',
            type: ''
          }
        }
      ]
    }
  ]
})
