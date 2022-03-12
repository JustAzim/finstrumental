<template>
    <v-container>

        <v-data-table
            :headers="headers"
            :items="companyList"
            :items-per-page="50"
            class="elevation-1"
        ></v-data-table>
    </v-container>
</template>

<script>
export default {
    props: {
        companyList: [],
    },
    data() {
        return {
            psComparatorValue: null,
            peComparatorValue: null,
            psComparator: null,
            peComparator: null,
            itemsComparators: [
                {
                    text: "<",
                    value: -1
                },
                {
                    text: "=",
                    value: 0
                },
                {
                    text: ">",
                    value: 1
                }
            ],
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
                    text: "Most Recent Quater Data",
                    value: "mostRecentQuaterData"
                },
                {
                    text: "Price To Sale",
                    value: "priceToSale"
                },
                {
                    text: "Price To Earn",
                    value: "priceToEarn"
                }
            ]
        }
    },

}
</script>

<style scoped>

</style>