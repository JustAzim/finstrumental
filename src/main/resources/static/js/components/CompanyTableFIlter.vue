<template>
    <v-container>
        <v-row>
            <v-col cols="2">
                Price to Sale
            </v-col>
            <v-col cols="1">
                <v-combobox solo dense :items="itemsComparators" v-model="psComparator"/>
            </v-col>
            <v-col cols="2">
                <v-text-field solo dese v-model="psComparatorValue"/>
            </v-col>
            <v-col cols="2">
                Price to Earn
            </v-col>
            <v-col cols="1">
                <v-combobox solo dense :items="itemsComparators" v-model="peComparator"/>
            </v-col>
            <v-col cols="2">
                <v-text-field solo dese v-model="peComparatorValue"/>
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
                newCompanyList = this.value.filter(c => {
                    let value = Number(c.priceToSale),
                        compareValue = Number(this.psComparatorValue)
                    if (this.psComparator.value < 0) {
                        return value < compareValue
                    } else if (this.psComparator.value === 0) {
                        return value == compareValue
                    } else if (this.psComparatorValue.value > 0) {
                        return value > compareValue
                    }
                })
            }
            if (this.peComparator && this.peComparatorValue) {
                newCompanyList = this.value.filter(c => {
                    let value = Number(c.priceToEarn),
                        compareValue = Number(this.peComparatorValue)
                    if (this.peComparator.value < 0) {
                        return value < compareValue
                    } else if (this.peComparator.value === 0) {
                        return value == compareValue
                    } else if (this.peComparatorValue.value > 0) {
                        return value > compareValue
                    }
                })
            }
            this.$emit("input", newCompanyList);
        }
    }
}
</script>

<style scoped>

</style>