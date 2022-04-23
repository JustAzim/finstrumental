<template>
    <v-container>

        <v-data-table
            :headers="headers"
            :items="companyList"
            :items-per-page="50"
            @click:row="rowClick"
            class="elevation-1"
            show-expand
            single-expand
            :expanded.sync="expanded"
            item-key="ticker"
        >
            <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length">
                    <company-chart/>
                </td>

            </template>


        </v-data-table>
    </v-container>
</template>

<script>
import CompanyChart from "./CompanyChart.vue"

export default {
    components: {CompanyChart},
    props: {
        companyList: [],
    },
    data() {
        return {
            dialog: false,
            selectedTicker: null,
            expanded: [],
            headers: [
                {
                    text: "Ticker",
                    value: "ticker"
                },
                {
                    text: "Company Name",
                    value: "companyName"
                },
                {
                    text: "Market Cap ($ Millions)",
                    value: "marketCap"
                },
                {
                    text: "Price From",
                    value: "priceFrom"
                },
                {
                    text: "Most Recent Quarter Data",
                    value: "mostRecentQuarterData"
                },
                {
                    text: "Price To Sale",
                    value: "priceToSale"
                },
                {
                    text: "Price To Earn",
                    value: "priceToEarn"
                },
                {
                    text: "Debt/Eq",
                    value: "debtEq"
                },
                {
                    text: "Price",
                    value: "price"
                },
            ]
        }
    },
    methods: {
        rowClick(item) {
            this.selectedTicker = item.ticker
            this.dialog = true
        }
    }

}
</script>

<style scoped>

</style>