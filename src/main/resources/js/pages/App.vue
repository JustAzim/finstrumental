<template>
    <v-app>
        <v-app-bar dense app>
            <v-toolbar-title>Finstrumental</v-toolbar-title>
        </v-app-bar>
        <v-main class="px-sm-1">
<!--            <v-row>-->
<!--                <v-col :cols="sideWidth"/>-->
<!--                <v-col>-->
                    <v-container>
                        <router-view/>
                    </v-container>
<!--                </v-col>-->
<!--                <v-col :cols="sideWidth"/>-->

<!--            </v-row>-->
        </v-main>

        <v-footer app>
            <v-card
                flat
                tile
                width="100%"
                class="lighten-1 text-center"
            >
                <v-card-text class="py-1">
                    {{ new Date().getFullYear() }} — <strong>Finstrumental alfa</strong>
                </v-card-text>
                <v-card-text class="py-1">
                    Author: <strong>Azim</strong>
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
    computed: {
        sideWidth() {
            if (this.$vuetify.breakpoint.xl || this.$vuetify.breakpoint.lg) {
                return 3
            } else if(this.$vuetify.breakpoint.md) {
                return 2
            }
            return 1
        }
    }
}
</script>

<style scoped>

</style>