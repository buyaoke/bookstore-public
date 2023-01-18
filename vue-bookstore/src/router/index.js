import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/ManageView.vue'),
    redirect:"/login",
    children:[
      {path: 'user', name: 'User', component: () => import('../views/User.vue')},
      {path: 'home', name: 'Home', component: () => import('../views/Home.vue')},
      {path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      {path: 'chart', name: 'chart', component: () => import('../views/Echart.vue')},
      {path: 'order', name: 'chart', component: () => import('../views/Order.vue')},
      {path: 'book', name: 'chart', component: () => import('../views/Book.vue')},
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.Base_URL,
  routes
})

export default router
