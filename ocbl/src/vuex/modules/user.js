import * as types from '../types'

const state = {
  // user login status = ture / false
  loginStatus: JSON.parse(localStorage.getItem('loginStatus')) || false,
  // user Info = { accesstoken: token, user: user }
  userInfo: JSON.parse(localStorage.getItem('userInfo')) || {},
  // user data = []
  userData: []
}

const actions = {
  /**
  * 用户登录
  * @param {any} {commit}
  * @param {any} accesstoken
  */
  setUserInfo ({ commit }, res) {
    localStorage.setItem('userInfo', JSON.stringify(res))
    localStorage.setItem('loginStatus', true)
    commit(types.SET_USER_INFO, res)
    commit(types.SET_LOGIN_STATUS, true)
  },

  /**
  * 退出登录
  * @param {any} {commit}
  */
  signOut ({ commit }) {
    localStorage.removeItem('loginStatus')
    localStorage.removeItem('userInfo')
    commit(types.SET_LOGIN_STATUS, false)
    commit(types.SET_USER_INFO, {})
  }
}

const getters = {
  getUserData: state => state.userData,
  getloginStatus: state => state.loginStatus,
  getUserInfo: state => state.userInfo
}

const mutations = {
  [types.SET_USER_INFO] (state, res) {
    state.userInfo = res
  },
  [types.SET_LOGIN_STATUS] (state, status) {
    state.loginStatus = status
  },
  [types.GET_USER_DATA] (state, res) {
    state.userData = res
  }
}

export default {
  state,
  actions,
  getters,
  mutations
}
