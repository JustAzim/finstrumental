<template>
  <v-container>
    <v-data-table
        :headers="headers"
        :items="items"
        class="elevation-1"
        hide-default-footer
        multi-sort
    >
      <template v-slot:item.ticker="{ item }">
        <a target="_blank" :href="`#/company/${item.ticker}`">{{ item.ticker }}</a>
      </template>


    </v-data-table>
  </v-container>
</template>

<script>
import {formatMoneyToMln} from "../utils/formatter";

export default {
  props: {
    analysisData: {}
  },
  data() {
    return {
      indexes: [
        {
          text: "Grothe Rate",
          property: "grotheRate"
        },
        {
          text: "Total Revenue",
          property: "totalRevenue"
        },
        {
          text: "Cost of Revenue",
          property: "costOfRevenue"
        },
        {
          text: "Amortization",
          property: "reconciledDepreciation"
        },
        {
          text: "Capital Expenditure",
          property: "capitalExpenditure"
        },
        {
          text: "Free Cash Flow",
          property: "freeCashFlow"
        },
        {
          text: "EBIT",
          property: "ebit"
        },
        {
          text: "EBITDA",
          property: "ebitda"
        },
      ],
      headers: [],
      items:[],
    }
  },
  created() {
    this.normalizeData()
  },
  methods: {
    normalizeData() {
      let dates = Object.getOwnPropertyNames(JSON.parse(JSON.stringify(this.analysisData.fundamentals)))
      dates.sort()
      this.createHeaders(dates);
      this.createItems(dates)
    },
    createHeaders(dates) {
      this.headers.push({
        text: "Name",
        value: "name"
      })
      this.headers.push({
        text: "Value",
        value: "value"
      })
      dates.forEach((d) => {
        this.headers.push({
          text: d,
          value: d
        })
      })
    },
    createItems(dates) {
      for(let index of this.indexes) {
        let row = {}
        row["name"] = index.text
        let val = this.analysisData[index.property]

        if(val) {
          if(index.property == "reconciledDepreciation" || index.property == "grotheRate" ) {
            val = val.toFixed(2)
          } else {
            val = Number(val.toFixed()).toLocaleString('en-US')
          }
        }
        row["value"] = val ? val : null

        for(let d of dates) {
          let val = this.analysisData.fundamentals[d][index.property]
          if(val) {
            if(index.property == "grotheRate" ) {
              row[d] = val.toFixed(2)
            } else {
              row[d] = Number(val.toFixed()).toLocaleString('en-US')
            }
          }
        }
        this.items.push(row)
      }
    }
  }
}
</script>

<style scoped>

</style>