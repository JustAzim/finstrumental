import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
    user: null,
    authToken: null
}

const store = new Vuex.Store({
    state,
    getters: {
        user: (state) => {
            return state.user
        },
        authToken: (state) => {
            return state.authToken
        }
    },
    actions: {
        user(context, user) {
            context.commit('user', user)
        },
        authToken(context, authToken) {
            context.commit('authToken', authToken)
        }
    },
    mutations: {
        user(state, user) {
            state.user = user
        },
        authToken(state, authToken) {
            state.authToken = authToken
        }
    }
})

export default store
