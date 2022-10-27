<template>
    <v-container>
        <v-row justify="center">
            <h1>Company not found</h1>
        </v-row>
    </v-container>
</template>

<script>
import {consts} from "../constants";

export default {
    created() {
        document.title = `Company not found | ${consts.AppName}`
    }
}
</script>