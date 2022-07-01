<template>
    <v-container style="height: 800px">
        <v-row style="height: 400px">
            <v-col>
                <v-chart key="sc" ref="statementsChart" class="chart-a"/>
            </v-col>
            <v-col>
                <v-chart key="bc" ref="balanceSheetChart" class="chart-b"/>
            </v-col>
        </v-row>
        <v-row style="height: 400px">
            <v-col>
              <v-chart key="cc" ref="dividentsChart" class="chart-c"/>
            </v-col>
            <v-col>
                <v-chart key="cc" ref="cashFlowChart" class="chart-c"/>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import "echarts";
import VChart from "vue-echarts";
import {getBalanceSheetOption, getStatementsOption, getCashFlowOption} from "./_chartoptions/chartOption";
import {http} from "../http-common";
import {getDividentsOptions} from "./_chartoptions/dividentsChartOption";

export default {
    components: {
        VChart
    },
    props: {
        ticker: null
    },

    data() {
        return {
            statementsJson: null,

        }
    },
    methods: {
        updateOptions() {
            // statementChart
            http.get(`chart/getStatements/${this.ticker}`).then(res => {

                this.$refs.statementsChart.setOption(getStatementsOption(res.data))
            }).catch((reason) => {
                console.log(reason)
            })

            // balanceSheetChart
            http.get(`chart/getBalanceSheet/${this.ticker}`).then(res => {

                let bs = Object.assign(res.data)
                this.$refs.balanceSheetChart.setOption(getBalanceSheetOption(res.data))

                // cashflowChart
                http.get(`chart/getCashFlow/${this.ticker}`).then(res => {
                    let cf = res.data
                    cf.data['Cash'] = []
                    for(let dt of cf.data['Period End Date']) {
                        let index = bs.data['Period End Date'].indexOf(dt)
                        if(index >= 0) {
                            cf.data['Cash'].push(bs.data['Cash and Equivalents'][index])
                        } else {
                            cf.data['Cash'].push('0')
                        }
                    }
                    this.$refs.cashFlowChart.setOption(getCashFlowOption(cf))
                }).catch((reason) => {
                    console.log(reason)
                })


            }).catch((reason) => {
                console.log(reason)
            })


          // dividents
          http.get(`chart/getDividents/${this.ticker}`).then(res => {

            this.$refs.dividentsChart.setOption(getDividentsOptions(res.data))
          }).catch((reason) => {
            console.log(reason)
          })

        },
    },
    created() {
        this.updateOptions()
    }
}
</script>

<style scoped>
.chart {
    height: 400px;
}
</style>