import VueRouter from "vue-router"

import StockScreen from "../pages/StockScreen.vue"
import CompanyProfile from "../pages/CompanyProfile.vue"
import NotFoundPage from "../pages/NotFoundPage.vue"

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
        {
            path: '/:catchAll(.*)*',
            name: "PageNotFound",
            component: NotFoundPage,
        },
    ]
})