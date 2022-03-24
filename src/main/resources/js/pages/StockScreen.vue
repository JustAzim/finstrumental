<template>
  <v-container>
    <v-row>
      <v-col cols="4">
        <v-subheader>Minimum Market Cap</v-subheader>
      </v-col>
      <v-col cols="4">
        <v-text-field
            label="Enter the value"
            suffix="million"
            v-model="cap"
            dense
            autofocus
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-btn class="ml-7" small :loading="loading" @click="getStock">
        Get Stocks
      </v-btn>
    </v-row>
    <v-row>
      <v-divider inset class="my-5"/>
    </v-row>
    <company-table-filter v-if="showTable" :value="companyList" @filter="filteredCompanyList = $event"/>
    <company-table v-if="showTable" :company-list="filteredCompanyList"/>
    <v-snackbar
        v-model="snackbar"
        :timeout="timeout"
    >
      Ошибка

      <template v-slot:action="{ attrs }">
        <v-btn
            color="blue"
            text
            v-bind="attrs"
            @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import {http} from "../http-common"
import CompanyTable from "../components/CompanyTable.vue"
import CompanyTableFilter from "../components/CompanyTableFIlter.vue"

export default {
  components: {CompanyTable, CompanyTableFilter},
  data() {
    return {
      cap: 500,
      companyList: [{"companyName":"Academy Sports and Outdoors Inc","ticker":"ASO","marketCap":"3,269.16","priceFrom":"03/21","mostRecentQuaterData":"10/31","priceToSale":"0.47","priceToEarn":"5.69"},{"companyName":"AMC Networks Inc","ticker":"AMCX","marketCap":"1,651.82","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.53","priceToEarn":"6.74"},{"companyName":"Atea Pharmaceuticals Inc","ticker":"AVIR","marketCap":"575.90","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.53","priceToEarn":"5.10"},{"companyName":"Atkore Inc","ticker":"ATKR","marketCap":"4,889.39","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.44","priceToEarn":"7.28"},{"companyName":"Buckle Inc. (The)","ticker":"BKE","marketCap":"1,854.89","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"1.45","priceToEarn":"7.23"},{"companyName":"Centrus Energy Corp","ticker":"LEU","marketCap":"542.57","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.81","priceToEarn":"51.58"},{"companyName":"Crocs Inc","ticker":"CROX","marketCap":"4,748.54","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.97","priceToEarn":"7.14"},{"companyName":"Dick's Sporting Goods Inc.","ticker":"DKS","marketCap":"10,865.11","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.75","priceToEarn":"7.84"},{"companyName":"Fulgent Genetics Inc","ticker":"FLGT","marketCap":"1,897.06","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.83","priceToEarn":"3.84"},{"companyName":"Gilead Sciences Inc","ticker":"GILD","marketCap":"74,873.30","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"2.73","priceToEarn":"12.09"},{"companyName":"GoPro Inc","ticker":"GPRO","marketCap":"1,254.92","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.06","priceToEarn":"3.53"},{"companyName":"Heidrick & Struggles International Inc","ticker":"HSII","marketCap":"789.36","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.80","priceToEarn":"11.23"},{"companyName":"HP Inc","ticker":"HPQ","marketCap":"39,788.65","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.57","priceToEarn":"6.68"},{"companyName":"Innoviva Inc","ticker":"INVA","marketCap":"1,289.06","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"3.34","priceToEarn":"6.67"},{"companyName":"Ironwood Pharmaceuticals Inc","ticker":"IRWD","marketCap":"1,999.52","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"4.78","priceToEarn":"3.82"},{"companyName":"Korn Ferry","ticker":"KFY","marketCap":"3,518.08","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"1.39","priceToEarn":"11.81"},{"companyName":"Louisiana-Pacific Corp","ticker":"LPX","marketCap":"5,942.28","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.29","priceToEarn":"4.98"},{"companyName":"Moderna Inc","ticker":"MRNA","marketCap":"70,678.14","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"3.60","priceToEarn":"6.21"},{"companyName":"Perdoceo Education Corp","ticker":"PRDO","marketCap":"799.55","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.16","priceToEarn":"7.54"},{"companyName":"Quidel Corp","ticker":"QDEL","marketCap":"4,640.07","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"2.62","priceToEarn":"6.86"},{"companyName":"SIGA Technologies Inc","ticker":"SIGA","marketCap":"519.22","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"3.74","priceToEarn":"7.96"},{"companyName":"Sleep Number Corp","ticker":"SNBR","marketCap":"1,405.89","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.63","priceToEarn":"10.20"},{"companyName":"Smith & Wesson Brands Inc","ticker":"SWBI","marketCap":"751.84","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.74","priceToEarn":"3.34"},{"companyName":"Stelco Holdings Inc.","ticker":"STZHF","marketCap":"3,162.18","priceFrom":"03/21","mostRecentQuaterData":"09/30","priceToSale":null,"priceToEarn":null},{"companyName":"Sturm Ruger & Co Inc.","ticker":"RGR","marketCap":"1,264.87","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.71","priceToEarn":"8.19"},{"companyName":"Topicus.Com Inc","ticker":"TOITF","marketCap":"2,856.97","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":null,"priceToEarn":null},{"companyName":"Unisys Corp","ticker":"UIS","marketCap":"1,528.38","priceFrom":"03/21","mostRecentQuaterData":"09/30","priceToSale":"0.74","priceToEarn":"-"},{"companyName":"Vanda Pharmaceuticals Inc","ticker":"VNDA","marketCap":"645.66","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"2.37","priceToEarn":"19.81"},{"companyName":"WW International Inc","ticker":"WW","marketCap":"717.46","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.59","priceToEarn":"10.89"},{"companyName":"Zumiez Inc","ticker":"ZUMZ","marketCap":"858.15","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.67","priceToEarn":"8.23"}],
      loading: false,
      snackbar: false,
      timeout: 2000,
      filteredCompanyList: [{"companyName":"Academy Sports and Outdoors Inc","ticker":"ASO","marketCap":"3,269.16","priceFrom":"03/21","mostRecentQuaterData":"10/31","priceToSale":"0.47","priceToEarn":"5.69"},{"companyName":"AMC Networks Inc","ticker":"AMCX","marketCap":"1,651.82","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.53","priceToEarn":"6.74"},{"companyName":"Atea Pharmaceuticals Inc","ticker":"AVIR","marketCap":"575.90","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.53","priceToEarn":"5.10"},{"companyName":"Atkore Inc","ticker":"ATKR","marketCap":"4,889.39","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.44","priceToEarn":"7.28"},{"companyName":"Buckle Inc. (The)","ticker":"BKE","marketCap":"1,854.89","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"1.45","priceToEarn":"7.23"},{"companyName":"Centrus Energy Corp","ticker":"LEU","marketCap":"542.57","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.81","priceToEarn":"51.58"},{"companyName":"Crocs Inc","ticker":"CROX","marketCap":"4,748.54","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.97","priceToEarn":"7.14"},{"companyName":"Dick's Sporting Goods Inc.","ticker":"DKS","marketCap":"10,865.11","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.75","priceToEarn":"7.84"},{"companyName":"Fulgent Genetics Inc","ticker":"FLGT","marketCap":"1,897.06","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.83","priceToEarn":"3.84"},{"companyName":"Gilead Sciences Inc","ticker":"GILD","marketCap":"74,873.30","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"2.73","priceToEarn":"12.09"},{"companyName":"GoPro Inc","ticker":"GPRO","marketCap":"1,254.92","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.06","priceToEarn":"3.53"},{"companyName":"Heidrick & Struggles International Inc","ticker":"HSII","marketCap":"789.36","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.80","priceToEarn":"11.23"},{"companyName":"HP Inc","ticker":"HPQ","marketCap":"39,788.65","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.57","priceToEarn":"6.68"},{"companyName":"Innoviva Inc","ticker":"INVA","marketCap":"1,289.06","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"3.34","priceToEarn":"6.67"},{"companyName":"Ironwood Pharmaceuticals Inc","ticker":"IRWD","marketCap":"1,999.52","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"4.78","priceToEarn":"3.82"},{"companyName":"Korn Ferry","ticker":"KFY","marketCap":"3,518.08","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"1.39","priceToEarn":"11.81"},{"companyName":"Louisiana-Pacific Corp","ticker":"LPX","marketCap":"5,942.28","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.29","priceToEarn":"4.98"},{"companyName":"Moderna Inc","ticker":"MRNA","marketCap":"70,678.14","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"3.60","priceToEarn":"6.21"},{"companyName":"Perdoceo Education Corp","ticker":"PRDO","marketCap":"799.55","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.16","priceToEarn":"7.54"},{"companyName":"Quidel Corp","ticker":"QDEL","marketCap":"4,640.07","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"2.62","priceToEarn":"6.86"},{"companyName":"SIGA Technologies Inc","ticker":"SIGA","marketCap":"519.22","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"3.74","priceToEarn":"7.96"},{"companyName":"Sleep Number Corp","ticker":"SNBR","marketCap":"1,405.89","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.63","priceToEarn":"10.20"},{"companyName":"Smith & Wesson Brands Inc","ticker":"SWBI","marketCap":"751.84","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.74","priceToEarn":"3.34"},{"companyName":"Stelco Holdings Inc.","ticker":"STZHF","marketCap":"3,162.18","priceFrom":"03/21","mostRecentQuaterData":"09/30","priceToSale":null,"priceToEarn":null},{"companyName":"Sturm Ruger & Co Inc.","ticker":"RGR","marketCap":"1,264.87","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"1.71","priceToEarn":"8.19"},{"companyName":"Topicus.Com Inc","ticker":"TOITF","marketCap":"2,856.97","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":null,"priceToEarn":null},{"companyName":"Unisys Corp","ticker":"UIS","marketCap":"1,528.38","priceFrom":"03/21","mostRecentQuaterData":"09/30","priceToSale":"0.74","priceToEarn":"-"},{"companyName":"Vanda Pharmaceuticals Inc","ticker":"VNDA","marketCap":"645.66","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"2.37","priceToEarn":"19.81"},{"companyName":"WW International Inc","ticker":"WW","marketCap":"717.46","priceFrom":"03/21","mostRecentQuaterData":"12/31","priceToSale":"0.59","priceToEarn":"10.89"},{"companyName":"Zumiez Inc","ticker":"ZUMZ","marketCap":"858.15","priceFrom":"03/21","mostRecentQuaterData":"01/31","priceToSale":"0.67","priceToEarn":"8.23"}],
    }
  },
  methods: {
    getStock() {
      this.loading = true
      http.get(`companyData/mfi/${this.cap}`).then(res => {
        this.filteredCompanyList = this.companyList = res.data

      }).catch((reason) => {
        console.log(reason)
        this.snackbar = true;

      }).finally(() => {
        this.loading = false
      })
    }
  },
  computed: {
    showTable() {
      return this.companyList && this.companyList.length > 0
    }
  }
}
</script>

<style scoped>

</style>