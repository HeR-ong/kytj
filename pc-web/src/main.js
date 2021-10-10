// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import api from './js/http'
import url from './js/api'

Vue.prototype.api = api
Vue.prototype.url = url

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  const type = to.meta.type
  console.log('----' + type)
  // 判断该路由是否需要登录权限
  if (type === 'login') {
    if (window.sessionStorage.getItem('userInfo')) {
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
})
