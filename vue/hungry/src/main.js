// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import './r/css/init.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(MintUI);

import axios from 'axios';
Vue.prototype.$axios = axios;
import * as custom from '@/utils/util'

Object.keys(custom).forEach(key => {
    Vue.filter(key, custom[key])
})

router.beforeEach((to, from, next) => {
	if (to.matched.length != 0) {
		if (to.meta.requireAuth && to.meta.authGroup === "Admin") { // 判断该路由是否需要登录权限
			if (Boolean(localStorage.getItem("AdminInfo"))) {
				next();
			} else {
				next({
					path: '/admin/login',
					query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
				})
			}
		}else if(to.meta.requireAuth && to.meta.authGroup === "Mergant"){
			if (Boolean(localStorage.getItem("MergantInfo"))) {
				next();
			} else {
				next({
					path: '/mergant/login',
					query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
				})
			}
		}else if(to.meta.requireAuth && to.meta.authGroup === "Client"){
			if (Boolean(sessionStorage.getItem("UserInfo"))) {
				next();
			} else {
				next({
					path: '/client/login',
					query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
				})
			}
		}else if(to.meta.requireAuth && to.meta.authGroup === "Courier"){
			if (Boolean(sessionStorage.getItem("CourierInfo"))) {
				next();
			} else {
				next({
					path: '/courier/login',
					query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
				})
			}
		}
		next();
	} else {
		next();
		// next({
		// 	path: '/admin/login',
		// 	query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
		// })
	}
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
