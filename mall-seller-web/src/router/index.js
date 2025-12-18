import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/views/layout/Layout'

Vue.use(Router)

export const constantRouterMap = [
    { path: '/login', component: () => import('@/views/login/index'), hidden: true },
    { path: '/register', component: () => import('@/views/login/register'), hidden: true },
    {
        path: '',
        component: Layout,
        redirect: '/home',
        children: [{
            path: 'home',
            name: 'Home',
            component: () => import('@/views/home/index'),
            meta: { title: 'Dashboard', icon: 'home' }
        }]
    },
    {
        path: '/product',
        component: Layout,
        redirect: '/product/list',
        name: 'Product',
        meta: { title: 'Product', icon: 'product' },
        children: [
            {
                path: 'list',
                name: 'ProductList',
                component: () => import('@/views/product/list'), // Placeholder
                meta: { title: 'Product List', icon: 'product-list' }
            },
            {
                path: 'add',
                name: 'AddProduct',
                component: () => import('@/views/product/add'), // Placeholder
                meta: { title: 'Add Product', icon: 'product-add' }
            }
        ]
    },
    {
        path: '/order',
        component: Layout,
        redirect: '/order/list',
        name: 'Order',
        meta: { title: 'Order', icon: 'order' },
        children: [
            {
                path: 'list',
                name: 'OrderList',
                component: () => import('@/views/order/list'), // Placeholder
                meta: { title: 'Order List', icon: 'product-list' }
            }
        ]
    }
]

export default new Router({
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap
})


