<template>
    <v-app>
        <v-app-bar dense app>
            <v-toolbar-title>Finstrumental</v-toolbar-title>

<!--            <v-spacer></v-spacer>-->
<!--                <v-row justify="end" class="mr-5">-->
<!--                    <v-col cols="4" class="mt-6">-->
<!--                        <v-text-field-->
<!--                            v-model="searchTicker"-->
<!--                            clearable-->
<!--                            label=""-->
<!--                            placeholder="Enter ticker"-->
<!--                            type="text"-->
<!--                            append-icon="mdi-magnify"-->
<!--                            @click:append="findCompany"-->
<!--                            @keydown.enter="findCompany"-->
<!--                        ></v-text-field>-->
<!--                    </v-col>-->
<!--                </v-row>-->
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

export default {
    name: "App",
    components: {StockScreen},
    data() {
        return {
            searchTicker: ""
        }
    },
    computed: {
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
            this.$router.replace(`company/${this.searchTicker}`)
        }
    }
}
</script>

<style scoped>

</style>