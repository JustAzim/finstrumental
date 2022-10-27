import Vue from 'vue'
import store from './vuexStore'
import App from 'pages/App.vue'
import VueRouter from 'vue-router'
import Vuetify from "vuetify"
import 'vuetify/dist/vuetify.min.css'
import router from 'router/index'
import ECharts from 'vue-echarts'
// import ECharts modules manually to reduce bundle size
import {
    CanvasRenderer
} from 'echarts/renderers'
import {
    BarChart
} from 'echarts/charts'
import {
    GridComponent,
    TooltipComponent
} from 'echarts/components'

Vue.use(VueRouter)
Vue.use(Vuetify, {
    theme: {
        green: "#0F9D58",
    }
},)

Vue.use([
    CanvasRenderer,
    BarChart,
    GridComponent,
    TooltipComponent
]);
// register globally (or you can do it locally)
Vue.component('v-chart', ECharts)


export default new Vue({
    vuetify: new Vuetify(),
    el: '#app',
    router,
    store,
    render: a => a(App)
})
