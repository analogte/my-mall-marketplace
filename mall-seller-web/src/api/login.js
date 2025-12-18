import request from '@/utils/request'

export function login(username, password) {
    return request({
        url: '/seller/login',
        method: 'post',
        data: {
            username,
            password
        }
    })
}

export function getInfo() {
    return request({
        url: '/seller/info',
        method: 'get'
    })
}

export function logout() {
    return request({
        url: '/seller/logout',
        method: 'post'
    })
}
