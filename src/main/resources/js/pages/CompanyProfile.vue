<template>
    <v-container :key="ticker">
        <v-row justify="center">
            <h1>{{ upperTicker }}</h1>
        </v-row>
        <v-row justify="center">
            <h3>{{ companyData.companyName }} || {{companyData.sector}}</h3>
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
            <double-row-label-value label="Country" :value="companyData.country"/>
            <double-row-label-value label="Market cap" :value="companyData.marketCap"/>
            <double-row-label-value label="Price to sale" :value="companyData.priceToSale"/>
            <double-row-label-value label="Price to earn" :value="companyData.priceToEarn"/>
            <double-row-label-value label="Divident yield" :value="companyData.dividentYield"/>
            <double-row-label-value label="Gross margin" :value="companyData.grossMargin"/>
            <double-row-label-value label="Insider own" :value="companyData.insiderOwn"/>
            <double-row-label-value label="Debit/Eq" :value="companyData.debtEq"/>
            <double-row-label-value label="Price" :value="companyData.price"/>
        </v-row>

        <v-divider class="mt-10 mb-3"/>

        <v-row>
            <company-chart :ticker="ticker"/>
        </v-row>

      <dcf-analysis-table v-if="firstAnalisisData" :analysis-data="firstAnalisisData"/>

    </v-container>
</template>

<script>
import DoubleRowLabelValue from "../components/DoubleRowLabelValue.vue"
import CompanyChart from "../components/CompanyChart.vue"
import http from "../http-common"
import {consts} from "../constants";
import DcfAnalysisTable from "../components/DcfAnalysisTable.vue";

export default {
    name: "CompanyProfile",
    components: {DcfAnalysisTable, CompanyChart, DoubleRowLabelValue},
    data() {
        return {
            ticker: this.$route.params.ticker,
            companyData: null,
            description: "",
            firstAnalisisData: null
        }
    },
    created() {
        http.get(`companyData/${this.ticker}`).then(res => {
            this.companyData = res.data
        }).catch((reason) => {
            console.log(reason)
            this.$router.replace({name: 'notFoundCompany'})
        }).finally(() => {
            console.log(this.companyData)
        })

        http(`dcfAnalysis/getFundamental/${this.ticker}`).then(res => {
          this.firstAnalisisData = res.data
        })

        http(`companyData/getCompanyDescription/${this.ticker}`).then(res => {
            this.description = res.data
        }).catch((reason) => {
            console.log(reason)
        })

        document.title = `${this.ticker.toUpperCase()} | ${consts.AppName}`
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