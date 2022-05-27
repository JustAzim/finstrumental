<template>
    <v-container>

        <v-data-table
            fixed-header
            height="59vh"
            :headers="headers"
            :items="companyList"
            :items-per-page="50"
            @update:expanded="onExpanded"
            class="elevation-1"
            show-expand
            single-expand
            :expanded.sync="expanded"
            item-key="ticker"
            hide-default-footer
            multi-sort
        >
            <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length" :key="item.ticker">
                    <company-chart :ticker="selectedTicker"/>
                </td>
            </template>

            <template v-slot:item.ticker="{ item }">
                <a target="_blank" :href="`#/company/${item.ticker}`">{{ item.ticker }}</a>
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
                    text: "Market Cap ($ Millions)",
                    value: "marketCap"
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
                {
                    text: "Divident Yield(%)",
                    value: "dividentYield"
                },
                {
                    text: "Gross Margin(%)",
                    value: "grossMargin"
                },
                {
                    text: "Sector",
                    value: "sector"
                },
                {
                    text: "Country",
                    value: "country"
                },
                {
                    text: "Insider Own(%)",
                    value: "insiderOwn"
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