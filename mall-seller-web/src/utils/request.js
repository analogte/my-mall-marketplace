import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// Create Axios Instance
const service = axios.create({
    baseURL: 'http://localhost:8086', // Direct backend URL since we are local
    timeout: 15000 // Request timeout
})

// Request Interceptor
service.interceptors.request.use(
    config => {
        if (store.getters.token) {
            config.headers['Authorization'] = getToken()
        }
        return config
    },
    error => {
        console.log(error)
        Promise.reject(error)
    }
)

// Response Interceptor
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

            // 401: Unauthorized
            if (res.code === 401) {
                MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
                    confirmButtonText: 'Re-Login',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    store.dispatch('FedLogOut').then(() => {
                        location.reload()
                    })
                })
            }
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
