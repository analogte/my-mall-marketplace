import axios from 'axios'
import { Message } from 'element-ui'

// Create axios instance
const service = axios.create({
    baseURL: 'http://localhost:8086', // Backend address
    timeout: 15000 // Request timeout
})

// Request interceptor
service.interceptors.request.use(
    config => {
        // Add Authorization header if token exists
        // TODO: Connect with Vuex store for token retrieval
        // if (store.getters.token) {
        //   config.headers['Authorization'] = getToken()
        // }
        return config
    },
    error => {
        console.log(error)
        Promise.reject(error)
    }
)

// Response interceptor
service.interceptors.response.use(
    response => {
        const res = response.data
        // If the custom code is not 200, it is judged as an error.
        if (res.code !== 200) {
            Message({
                message: res.message || 'Error',
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject(new Error(res.message || 'Error'))
        } else {
            return res
        }
    },
    error => {
        console.log('err' + error)
        Message({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        })
        return Promise.reject(error)
    }
)

export default service
