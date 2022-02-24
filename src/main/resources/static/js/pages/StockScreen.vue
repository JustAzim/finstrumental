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
        <company-table v-if="showTable" :company-list="companyList"/>
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

export default {
    components: {CompanyTable},
    data() {
        return {
            cap: 50,
            companyList: null,
            loading: false,
            snackbar: false,
            timeout: 2000
        }
    },
    methods: {
        getStock() {
            this.loading = true
            http.get(`companyData/mfi/${this.cap}`).then(res => {
                this.companyList = res.data
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