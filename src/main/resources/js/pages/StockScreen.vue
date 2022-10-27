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
import http from "../http-common"
import CompanyTable from "../components/CompanyTable.vue"
import CompanyTableFilter from "../components/CompanyTableFIlter.vue"
import {consts} from "../constants";

export default {
  components: {CompanyTable, CompanyTableFilter},
  data() {
    return {
      cap: 500,
      companyList: null,
      loading: false,
      snackbar: false,
      timeout: 2000,
      filteredCompanyList: null,
    }
  },
  methods: {
    getStock() {
      this.loading = true
      http.get(`companyData/getFiftyCompanies/${this.cap}`).then(res => {
        this.filteredCompanyList = this.companyList = res.data

      }).catch((reason) => {
        console.log(reason)
        this.snackbar = true;

      }).finally(() => {
        this.loading = false
      })
    },
    loadUser() {
      http.get('api/users').then((res) => {
        this.$store.dispatch('user', res.data)
      }).catch((reason) => {
        console.log(reason)
      })
    },
  },
  computed: {
    showTable() {
      return this.companyList && this.companyList.length > 0
    }
  },
  created() {
    this.loadUser()
    document.title = `Stock screener | ${consts.AppName}`
  }
}
</script>

<style scoped>

</style>