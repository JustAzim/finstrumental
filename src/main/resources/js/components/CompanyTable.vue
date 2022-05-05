<template>
    <v-container>

        <v-data-table
            :headers="headers"
            :items="companyList"
            :items-per-page="50"
            @update:expanded="onExpanded"
            class="elevation-1"
            show-expand
            single-expand
            :expanded.sync="expanded"
            item-key="ticker"
        >
            <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length" :key="item.ticker">
                    <company-chart :ticker="selectedTicker"/>
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
      onExpanded(items) {
          if(items.length > 0) {
            let c = items[0]
            if(this.selectedTicker === c.ticker) return
            this.selectedTicker = c.ticker
          }
      }
    }

}
</script>

<style scoped>

</style>