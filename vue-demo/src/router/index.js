import Vue from 'vue'
import Router from 'vue-router'
import BlogLogin from '@/components/manage/login.vue'
import BlogIndex from '@/components/home/index.vue'
import HelloWorld from '@/components/HelloWorld.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/index',
      name: 'BlogIndex',
      component: BlogIndex
    },
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/manage',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'BlogLogin',
      component: BlogLogin
    }
  ]
})
