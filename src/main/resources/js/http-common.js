import axios from "axios"
const token = localStorage.getItem("token")
let bearer = token ? `Bearer_${localStorage.getItem("token")}` : ''
export const http = axios.create({
    headers: {
        Authorization: bearer
    }
})

export const httpNoToken = axios.create()
