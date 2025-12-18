import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/seller/login',
        method: 'post',
        data: data
    })
}

export function register(data) {
    return request({
        url: '/seller/register',
        method: 'post',
        data: data
    })
}
