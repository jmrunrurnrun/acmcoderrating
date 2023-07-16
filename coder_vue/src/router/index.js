import Vue from 'vue'
import VueRouter from 'vue-router'
import StudentIndex from '../views/StudentIndex.vue'
import SelectAll from '../views/studentBox/SelectAll.vue'
import StuInfo from '../views/studentBox/StuInfo'
import VisualHistogram from '../views/visualBox/VisualHistogram.vue'
import SelectAllcf from '../views/studentBox/SelectAllcf.vue'
import VisualB from '../views/visualBox/VisualB.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'

Vue.use(VueRouter)

// 解决在使用ElementUI中的导航时，默认情况下如果重复点击某选项，会报错。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
const originalReplace = VueRouter.prototype.replace
VueRouter.prototype.replace = function replace (location) {
  return originalReplace.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    // redirect: '/Login',
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
  path: '/',
  component: StudentIndex,
  redirect: '/SelectAll',
  children: [{
    path: '/SelectAll',
    name: 'SelectAll',
    component: SelectAll
  },{
    path: '/SelectAllcf',
    name: 'SelectAllcf',
    component: SelectAllcf
  }, {
    path: '/StuInfo',
    name: 'StuInfo',
    component: StuInfo
  }, {
    path: '/VisualHistogram',
    name: 'VisualHistogram',
    component: VisualHistogram
  }, {
    path: '/VisualB',
    name: 'VisualB',
    component: VisualB
  }
  ]
}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
