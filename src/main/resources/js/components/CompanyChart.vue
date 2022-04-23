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
      <v-col/>
      <v-col>
        <v-chart key="cc" ref="cashFlowChart" class="chart-c"/>
      </v-col>
    </v-row>
  </v-container>
  <!--  <div style="width: 100%; height: 900px">-->
  <!--    <div style="width: 100%; height: 300px">-->
  <!--      <v-chart key="sc" ref="statementsChart" class="chart-a"/>-->
  <!--    </div>-->
  <!--    <div style="width: 100%; height: 300px">-->

  <!--      <v-chart key="bc" ref="balanceSheetChart" class="chart-b"/>-->
  <!--    </div>-->

  <!--    <div style="width: 100%; height: 300px">-->

  <!--      <v-chart key="cc" ref="cashFlowChart" class="chart-c"/>-->
  <!--    </div>-->
  <!--  </div>-->
</template>

<script>
import "echarts";
import VChart from "vue-echarts";
import {getgetBalanceSheetOption, getStatementsOption, getCashFlowOptin} from "./_chartoptions/chartOption";
import {http} from "../http-common";

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
      http.get(`companyData/chart/getStatements/${this.ticker}`).then(res => {
        console.log(res.data)

        this.$refs.statementsChart.setOption(getStatementsOption(res.data))
      }).catch((reason) => {
        console.log(reason)
      })

      // balanceSheetChart
      http.get(`companyData/chart/getBalanceSheet/${this.ticker}`).then(res => {
        console.log(res.data)

        this.$refs.balanceSheetChart.setOption(getgetBalanceSheetOption(res.data))
      }).catch((reason) => {
        console.log(reason)
      })

      // cashflowChart
      http.get(`companyData/chart/getCashFlow/${this.ticker}`).then(res => {
        console.log(res.data)

        this.$refs.cashFlowChart.setOption(getCashFlowOptin(res.data))
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