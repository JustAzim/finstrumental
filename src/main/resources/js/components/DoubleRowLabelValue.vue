<template>
    <v-col>
        <div class="text-md-center">
            {{ label }}
        </div>
        <v-divider class="my-2"/>
        <div class="text-md-center">
            {{ value }}
        </div>
    </v-col>
</template>

<script>
export default {
    name: "DoubleRowLabelValue",
    props: {
        label: {},
        value: {}
    }
}
</script>

<style scoped>

</style>