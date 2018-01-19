import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'

Vue.use(Vuex)
export default new Vuex.Store({
  modules: {
    user
  },
  strict: process.env.NODE_ENV !== 'production' // 是否开启严格模式
})
