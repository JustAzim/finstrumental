<template>
    <v-container>
        <chart option="chartOpt"/>
    </v-container>
</template>

<script>
import Chart from "./chart/Chart"
import {option} from "./_chartoptions/statementsChartOption"

export default {
    components: {Chart},
    props: {
        ticker: {},
    },
    created() {
        this.chartOpt = option;
    }
}
</script>

<style scoped>

</style>