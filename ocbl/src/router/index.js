import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/pages/Home'
import Profile from '@/pages/Profile'
import Register from '@/pages/Register'
import Free from '@/pages/Free'
import Github from '@/components/oauth/Github'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/login/github/:token',
    component: Github
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
    meta: { auth: true }
  }
]

const router = new VueRouter({
  mode: 'history',
  // history: true,
  // base: __dirname,
  routes: routes
})

// TODO:
// any response with 401, goto NOACCESS page
// loginStatus = false, goto => Home.vue
// loginStatue = true, user.status != REGISTERED got to => Register.vue
// loginStatus = true, user.status == REGISTERED, check if user have access to view
// yes => goto page
// no => goto NO ACCESS page

// router.beforeEach((to, from, next) => {
//   console.log('path = %s', to.path)
//
//   var regex = /\/login\/github\/[\w]+/
//   if (regex.test(to.path)) {
//     console.log('in /login/github/:token')
//     // here we do login process
//     return next({ path: '/profile' })
//   }
//   next()
// })

// if (t.match(regex)) {
//   alert("Successful match");
// } else {
//   alert("No match");
// }
// router.beforeEach((to, from, next) => {
//   var { auth = false } = to.meta
//   var isLogin = Boolean(localStorage.getItem('loginStatus')) // true用户已登录， false用户未登录
//   console.log('isLogin=%s', isLogin)
//   if (auth && !isLogin && to.path !== '/login') {
//     return next({ path: '/login' })
//   }
//   next()
// })

export default router
