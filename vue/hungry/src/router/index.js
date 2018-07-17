import Vue from 'vue'
import Router from 'vue-router'

import root from '@/page/client/root'
import login from '@/page/client/login'
import home_index from '@/page/client/home/index'
import mine_index from '@/page/client/mine/index'
import find_index from '@/page/client/find/index'
import order_index from '@/page/client/order/index'

import account_inf from '@/page/client/mine/account_inf'
import order_detail from '@/page/client/order/detail'
import mergant_detail from '@/page/client/home/mergant'

import mmanageOrder from '@/page/mergant/manageOrder'
import mmergantDetail from '@/page/mergant/mergantDetail'
import mergant from '@/page/mergant/home'
import mergantLogin from '@/page/mergant/login'

import admin from '@/page/admin/home'
import adminLogin from '@/page/admin/login'
import manageUser from '@/page/admin/manageUser'
import manageMergant from '@/page/admin/manageMergant'
import manageCourier from '@/page/admin/manageCourier'
import manageOrder from '@/page/admin/manageOrder'
import mergantDetail from '@/page/admin/mergantDetail'

import courier_login from '@/page/courier/login'
import courierView from '@/page/courier/courierView'
import courier_signup from '@/page/courier/signup'
import courier_order from '@/page/courier/order'
import courier_order_detail from '@/page/courier/order_detail'
Vue.use(Router)

export default new Router({
  routes: [
//courier
  {
    path: '/courier',
    component:courierView,
  },
  {
    path: '/courier/login',
    component: courier_login,
  },
  {
    path: '/courier/signup',
    component: courier_signup,
    meta: {
      requireAuth: true,
      authGroup: "Courier",
    }
  },
  {
    path: '/courier/order',
    component: courier_order,
    meta: {
      requireAuth: true,
      authGroup: "Courier",
    }
  },
  {
    path: '/courier/order/detail/:id',
    component: courier_order_detail,
    meta: {
      requireAuth: true,
      authGroup: "Courier",
    }
  },
//mergant
  {
    path: '/mergant/login',
    name: 'mergant_login',
    component: mergantLogin,
  },
  {
    path: '/mergant',
    component:mergant,
    meta: {
      requireAuth: true,
      authGroup: "Mergant",
    },
    children: [
    {
      path:'detail',
      component: mmergantDetail,
      meta: {
        requireAuth: true,
        authGroup: "Mergant",
      }
    },
    {
      path:'order',
      component: mmanageOrder,
      meta: {
        requireAuth: true,
        authGroup: "Mergant",
      }
    }
    ]
  },

//admin
  {
    path: '/admin/login',
    name: 'admin_login',
    component: adminLogin,
  },
  {
    path: '/admin',
    component: admin,
    meta: {
      requireAuth: true,
      authGroup: "Admin",
    },
    children: [
    {
      path:'mergant',
      component: manageMergant,
      meta: {
        requireAuth: true,
        authGroup: "Admin",
      }
    },
    {
      path: 'mergant/detail/:id',
      component: mergantDetail,
      meta: {
        requireAuth: true,
        authGroup: "Admin",
      }
    },
    {
      path:'user',
      component: manageUser,
      meta: {
        requireAuth: true,
        authGroup: "Admin",
      }
    },
    {
      path:'courier',
      component: manageCourier,
      meta: {
        requireAuth: true,
        authGroup: "Admin",
      }
    },
    {
      path:'order',
      component: manageOrder,
      meta: {
        requireAuth: true,
        authGroup: "Admin",
      }
    },
    ]
  },
//client
  {
    path: '/client',
    component: root,
    children: [
    {
      path: '',
      component: home_index,
    },
    {
      path: 'home',
      component: home_index,
    },
    {
      path: 'mine',
      component: mine_index,
    },
    {
      path: 'mine/account',
      component: account_inf,
      meta: {
        requireAuth: true,
        authGroup: "Client",
      }
    },
    {
      path: 'find',
      component: find_index,
    },
    {
      path: 'order',
      component: order_index,
      meta: {
        requireAuth: true,
        authGroup: "Client",
      }
    },
    {
      path: 'order/detail/:id',
      component: order_detail,
      meta: {
        requireAuth: true,
        authGroup: "Client",
      }
    },
    {
      path: 'mergant/:id',
      component: mergant_detail,
      meta: {
        requireAuth: true,
        authGroup: "Client",
      }
    },

    ]
  },
  {
    path: '/client/login',
    component: login,
  },
  ]
})
