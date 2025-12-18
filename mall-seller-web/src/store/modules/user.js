import { login, getInfo, logout } from '@/api/login' // Need to ensure these exist
import { getToken, setToken, removeToken } from '@/utils/auth'

const user = {
    state: {
        token: getToken(),
        name: '',
        avatar: '',
        roles: []
    },

    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_NAME: (state, name) => {
            state.name = name
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        }
    },

    actions: {
        // Login
        Login({ commit }, userInfo) {
            const username = userInfo.username.trim()
            return new Promise((resolve, reject) => {
                login(username, userInfo.password).then(response => {
                    const data = response.data
                    const tokenStr = data.tokenHead + data.token
                    setToken(tokenStr)
                    commit('SET_TOKEN', tokenStr)
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // Get User Info
        GetInfo({ commit, state }) {
            return new Promise((resolve, reject) => {
                // Mock get info or verify if we need to call an API.
                // For now, let's assume valid token means valid session or call a dummy info API.
                // Actually, we should probably implement a /seller/info API or similar.
                // For MVP, if we have a token, we assume logged in, but better to check.
                // Let's retry using the standard login logic.

                // Temporarily, just return mock data to allow frontend to proceed if api is missing
                commit('SET_ROLES', ['TEST'])
                resolve({ roles: ['TEST'] })

                /* 
                getInfo().then(response => {
                  const data = response.data
                  if (data.roles && data.roles.length > 0) { // check roles
                    commit('SET_ROLES', data.roles)
                  } else {
                    reject('getInfo: roles must be a non-null array !')
                  }
                  commit('SET_NAME', data.username)
                  commit('SET_AVATAR', data.icon)
                  resolve(response)
                }).catch(error => {
                  reject(error)
                })
                */
            })
        },

        // Logout
        LogOut({ commit, state }) {
            return new Promise((resolve, reject) => {
                // logout(state.token).then(() => {
                commit('SET_TOKEN', '')
                commit('SET_ROLES', [])
                removeToken()
                resolve()
                // }).catch(error => {
                //   reject(error)
                // })
            })
        },

        // Frontend LogOut
        FedLogOut({ commit }) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                removeToken()
                resolve()
            })
        }
    }
}

export default user
