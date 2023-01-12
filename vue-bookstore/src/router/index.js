import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/ManageView.vue'),
    redirect:"/home",
    children:[
      {path: 'user', name: 'User', component: () => import('../views/User.vue')},
      {path: 'home', name: 'Home', component: () => import('../views/Home.vue')},
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.Base_URL,
  routes
})

export default router
