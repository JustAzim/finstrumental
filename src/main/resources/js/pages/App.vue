<template>
    <v-app>
        <v-app-bar dense app>
            <router-link :to="{name: 'stockScreen'}">
                <v-toolbar-title>{{ appName }}</v-toolbar-title>
            </router-link>

            <v-row v-if="user" class="ml-10">
                <v-col cols="2" class="mt-6">
                    <v-text-field
                        v-model="searchTicker"
                        clearable
                        label=""
                        placeholder="Enter ticker"
                        type="text"
                        append-icon="mdi-magnify"
                        @click:append="findCompany"
                        @keydown.enter="findCompany"
                    ></v-text-field>
                </v-col>
                <v-spacer></v-spacer>
                <v-col v-if="user">
                  <v-row class="mt-5 mr-6" justify="end">
                    <v-menu>
                      <template v-slot:activator="{ attrs, on }">
                        <v-btn
                            v-bind="attrs"
                            v-on="on"
                        >
                          Hello, {{user.firstName}}
                        </v-btn>
                      </template>

                      <v-list>
                        <v-list-item
                            v-for="item in items"
                            :key="item"
                            @click="logOut"
                            link
                        >
                          <v-list-item-title v-text="item"></v-list-item-title>
                        </v-list-item>
                      </v-list>
                    </v-menu>
                  </v-row>
                </v-col>
            </v-row>
        </v-app-bar>
        <v-main class="px-sm-1">
            <v-container>
                <router-view/>
            </v-container>
        </v-main>

        <v-footer app>
            <v-card
                flat
                tile
                width="100%"
                class="lighten-3 text-center"
            >
                <v-card-text class="py-1">
                    {{ new Date().getFullYear() }} — <strong>Finstrumental alfa</strong>
                </v-card-text>
            </v-card>
        </v-footer>
    </v-app>
</template>

<script>
import StockScreen from "./StockScreen.vue"
import http from "../http-common"
import {mapGetters} from 'vuex'
import {consts} from "../constants";

export default {
    name: "App",
    components: {StockScreen},
    data() {
        return {
            searchTicker: "",
          items: ['Log out']
        }
    },
    computed: {
        ...mapGetters(['user']),
        appName() {
          return consts.AppName
        },
        sideWidth() {
            if (this.$vuetify.breakpoint.xl || this.$vuetify.breakpoint.lg) {
                return 3
            } else if(this.$vuetify.breakpoint.md) {
                return 2
            }
            return 1
        }
    },
    methods: {
        findCompany() {
            let routeData = this.$router.resolve({name: 'companyProfile', params: {ticker: this.searchTicker}});
            window.open(routeData.href, '_blank');
        },
        loadUser() {
            http.get('api/users').then((res) => {
              this.$store.dispatch('user', res.data)
            }).catch( () => {
              this.$router.push({name: 'loginPage'})
            })
        },
        logOut() {
          localStorage.setItem("token", "")
          this.$store.dispatch('user', null)
          this.$router.push({name: 'loginPage'})
        }
    },
    created() {
        this.loadUser()
    }
}
</script>

<style scoped>

</style>