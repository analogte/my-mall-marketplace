import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import App from './App'
import router from './router'

Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

// Global Currency Filter (THB)
Vue.filter('currency', function (value) {
    if (!value) return '฿ 0.00'
    return '฿ ' + parseFloat(value).toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
})

new Vue({
    el: '#app',
    router,
    render: h => h(App)
})

