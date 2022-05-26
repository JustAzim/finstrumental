import VueRouter from "vue-router"

import StockScreen from "../pages/StockScreen.vue"
import CompanyProfile from "../pages/CompanyProfile.vue"
import NotFoundPage from "../pages/NotFoundPage.vue"
import NotFoundCompany from "../pages/NotFoundCompany.vue"

export default new VueRouter({
    routes: [
        {
            path: '/',
            name: 'stockScreen',
            component: StockScreen
        },
        {
            path: '/company/:ticker',
            name: 'companyProfile',
            component: CompanyProfile
        },
        {
            path: '/company/notFoundCompany',
            name: 'notFoundCompany',
            component: NotFoundCompany
        },
        {
            path: '*',
            name: "pageNotFound",
            component: NotFoundPage,
        },
    ]
})