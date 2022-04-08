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

}
</script>

<style scoped>

</style>