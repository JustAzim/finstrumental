<template>
    <v-container>
        <v-row justify="center">
            <h1>{{ upperTicker }}</h1>
        </v-row>
        <v-row justify="center">
            <h3>{{ companyData.companyName }}</h3>
        </v-row>


        <v-divider class="mt-10 mb-3"/>
        <v-row justify="center">
            <v-expansion-panels flat>
                <v-expansion-panel>
                    <v-expansion-panel-header>Description</v-expansion-panel-header>
                    <v-expansion-panel-content>
                        {{ description }}
                    </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
        </v-row>

        <v-divider class="mt-3 mb-10"/>

        <v-row>
            <double-row-label-value label="Market cap" :value="companyData.marketCap"/>
            <double-row-label-value label="Price to sale" :value="companyData.priceToSale"/>
            <double-row-label-value label="Price to earn" :value="companyData.priceToEarn"/>
            <double-row-label-value label="Debit/Eq" :value="companyData.debtEq"/>
            <double-row-label-value label="Price" :value="companyData.price"/>
        </v-row>

        <v-divider class="mt-10 mb-3"/>

        <v-row>
            <company-chart :ticker="ticker"/>
        </v-row>
    </v-container>
</template>

<script>
import VLabel from "../../static/js/main"
import DoubleRowLabelValue from "../components/DoubleRowLabelValue.vue"
import CompanyChart from "../components/CompanyChart.vue"
import {http} from "../http-common"
export default {
    name: "CompanyProfile",
    components: {CompanyChart, DoubleRowLabelValue, VLabel},
    data() {
        return {
            ticker: this.$route.params.ticker,
            companyData: null,
            description: "",
        }
    },
    created() {
        http.get(`companyData/${this.ticker}`).then(res => {
            this.companyData = res.data

        }).catch((reason) => {
            console.log(reason)

        }).finally(() => {

        })

        http(`companyData/getCompanyDescription/${this.ticker}`).then(res => {
            this.description = res.data
        }).catch((reason) => {
            console.log(reason)

        }).finally(() => {

        })
    },
    computed: {
        upperTicker() {
            return this.ticker.toUpperCase()
        }
    }
}
</script>

<style scoped>

</style>