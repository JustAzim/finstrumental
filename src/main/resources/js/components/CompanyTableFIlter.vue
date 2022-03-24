<template>
  <v-container>
    <v-row class="filter-block">
      <v-col>
        <v-subheader>
          Price to Sale
        </v-subheader>
      </v-col>
      <v-col cols="2">
        <v-combobox solo dense :items="itemsComparators" v-model="psComparator" :height="inputHeight"/>
      </v-col>
      <v-col cols="2">
        <v-text-field solo dese v-model="psComparatorValue" :height="inputHeight" clearable @click:clear="psComparator=null"/>
      </v-col>
      <v-col>
        <v-subheader>
          Price to Earn
        </v-subheader>
      </v-col>
      <v-col cols="2">
        <v-combobox solo dense :items="itemsComparators" v-model="peComparator" :height="inputHeight" />
      </v-col>
      <v-col cols="2">
        <v-text-field solo dese v-model="peComparatorValue" :height="inputHeight" clearable @click:clear="peComparator=null"/>
      </v-col>
      <v-col cols="2">
        <v-btn @click="acceptFilters">Accept</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    value: {}
  },
  data() {
    return {
      psComparatorValue: null,
      peComparatorValue: null,
      psComparator: null,
      peComparator: null,
      inputHeight: '50px',
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
    }
  },
  methods: {
    acceptFilters() {
      let newCompanyList = Object.assign(this.value);
      if (this.psComparator && this.psComparatorValue) {
        if (this.psComparator.value < 0) {
          newCompanyList = newCompanyList.filter(c => {
            return Number(c.priceToSale) < Number(this.psComparatorValue)
          })
        } else if (this.psComparator.value === 0) {
          newCompanyList = newCompanyList.filter(c => {
            return Number(c.priceToSale) === Number(this.psComparatorValue)
          })
        } else if (this.psComparator.value > 0) {
          newCompanyList = newCompanyList.filter(c => {
            return Number(c.priceToSale) > Number(this.psComparatorValue)
          })
        }
      }

      //

      if (this.peComparator && this.peComparatorValue) {
        if (this.peComparator.value < 0) {
          newCompanyList = newCompanyList.filter(c => {
            return Number(c.priceToEarn) < Number(this.peComparatorValue)
          })
        } else if (this.peComparator.value === 0) {
          newCompanyList = newCompanyList.filter(c => {
            return Number(c.priceToEarn) === Number(this.peComparatorValue)
          })
        } else if (this.peComparator.value > 0) {
          newCompanyList = newCompanyList.filter(c => {
            return Number(c.priceToEarn) > Number(this.peComparatorValue)
          })
        }
      }
      this.$emit("filter", newCompanyList);
    }
  }
}
</script>

<style scoped>
.filter-block {
  /*height: 10px;*/
}

/*div.btn__content {*/
/*  padding: 0;*/
/*}*/

/*div.card__actions .btn {*/
/*  min-width: 0;*/
/*}*/

/*.main {*/
/*  display: flex;*/
/*  flex-flow: column;*/
/*}*/
/*.block {*/
/*  display:flex;*/
/*  flex-flow: row;*/
/*  gap: 20px;*/
/*  width:fit-content;*/
/*  background-color: #4285F4;*/
/*  border-radius: 20px;*/
/*  width: fit-content;*/
/*}*/
/*/deep/ .v-combobox{*/
/*  width: 200px;*/
/*  height: 50px;*/
/*}*/
/*/deep/ .v-text-field {*/
/*  width: 150px;*/
/*  height: 150px;*/
/*  min-width: 150px !important;*/
/*  min-height: 40px !important;*/
/*  max-width: 150px !important;*/
/*  max-height: 40px !important;*/
/*  margin: 0;*/
/*  padding: 0;*/
/*}*/
/*.label {*/
/*  white-space: nowrap;*/
/*  color: white;*/
/*}*/

/*@media only screen and (max-width: 680px) {*/
/*  !* styles for browsers larger than 1440px; *!*/
/*  .block {*/
/*    display: flex;*/
/*    flex-flow: column;*/
/*    gap: 0px;*/
/*  }*/
/*}*/


</style>