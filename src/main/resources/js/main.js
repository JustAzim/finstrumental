import Vue from 'vue'
import App from 'pages/App.vue'
import VueRouter from 'vue-router'
import Vuetify from "vuetify"
import 'vuetify/dist/vuetify.min.css'
import router from 'router/index'

Vue.use(VueRouter)
Vue.use(Vuetify)

new Vue({
    vuetify: new Vuetify(),
    el: '#app',
    router,
    render: a => a(App)
})
