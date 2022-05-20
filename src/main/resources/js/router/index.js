import VueRouter from "vue-router"

import StockScreen from "../pages/StockScreen.vue"
import CompanyProfile from "../pages/CompanyProfile.vue"

export default new VueRouter({
    routes: [
        {
            path: '/',
            component: StockScreen
        },
        {
            path: '/company/:ticker',
            component: CompanyProfile
        },
    ]
})