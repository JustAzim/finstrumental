import Vue from 'vue'
import App from 'pages/App.vue'
import VueRouter from 'vue-router'
import Vuetify from "vuetify"
import 'vuetify/dist/vuetify.min.css'
import router from 'router/index'
import VueSelect from "vue-select";

Vue.use(VueRouter)
Vue.use(Vuetify, {
    theme: {
        green: "#0F9D58",
    }
},)


Vue.component("v-select", VueSelect.VueSelect);
new Vue({
    vuetify: new Vuetify(),
    el: '#app',
    router,
    render: a => a(App)
})
