import axios from "axios"
import app from './main'

const http = axios.create()
http.interceptors.request.use(config => {
    let token = localStorage.getItem("token")
    let bearer = token ? `Bearer_${token}` : ''
    config.headers = {
        'Authorization': bearer
    }
    return config
}, error => {})
http.interceptors.response.use(config => {
}, error => {
    if(error.response.status == 500) {
        app.$nextTick(() => {
            app.$router.push({name: 'loginPage'})
        })
    }
})
export default http

export const httpNoToken = axios.create()
