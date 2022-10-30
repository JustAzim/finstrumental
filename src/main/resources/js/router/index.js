import VueRouter from "vue-router"

import StockScreen from "../pages/StockScreen.vue"
import CompanyProfile from "../pages/CompanyProfile.vue"
import NotFoundPage from "../pages/NotFoundPage.vue"
import NotFoundCompany from "../pages/NotFoundCompany.vue"
import Auth from "../pages/auth/LoginForm.vue"
import RegistrationForm from "../pages/auth/RegistrationForm.vue"

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
            path: '/notFoundCompany',
            name: 'notFoundCompany',
            component: NotFoundCompany
        },
        {
            path: '/auth/login',
            name: 'loginPage',
            component: Auth
        },
        {
            path: '/auth/registration',
            name: 'registrationPage',
            component: RegistrationForm
        },
        {
            path: '*',
            name: "pageNotFound",
            component: NotFoundPage,
        },
    ]
})