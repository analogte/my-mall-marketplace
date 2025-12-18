import request from '@/utils/request'

export function createProduct(data) {
    return request({
        url: '/product/create',
        method: 'post',
        data: data
    })
}
