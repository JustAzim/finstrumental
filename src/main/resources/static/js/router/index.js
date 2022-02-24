import VueRouter from "vue-router"

import StockScreen from "../pages/StockScreen.vue"

export default new VueRouter({
    routes: [
        {
            path: '/',
            component: StockScreen
        }
    ]
})