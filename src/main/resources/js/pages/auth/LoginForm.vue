<template>
  <v-row>
    <v-col/>
    <v-col cols="3">
      <h2>Log in</h2>
      <v-form
          ref="form"
          v-model="valid"
          lazy-validation

      >
        <v-text-field
            v-model="username"
            :counter="16"
            :rules="usernameRules"
            label="Username"
            required
        ></v-text-field>
        <v-text-field
            v-model="password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="passwordRules"
            :type="showPassword ? 'text' : 'password'"
            name="password"
            label="Password"
            hint="At least 8 characters"
            counter
            @click:append="showPassword = !showPassword"
        ></v-text-field>

        <v-btn
            :disabled="!valid"
            class="mr-4"
            @click="submit"
        >
          Submit
        </v-btn>
        <v-btn
            class="mr-4"
            @click="goRegister"
        >
          Register
        </v-btn>
      </v-form>
    </v-col>
    <v-col/>
  </v-row>
</template>

<script>


import {httpNoToken} from "../../http-common"

export default {
  data() {
    return {
      valid: true,
      showPassword: false,
      username: "",
      usernameRules: [
        v => !!v || 'Username is required',
        v => (v && v.length <= 10) || 'Username must be less than 10 characters',
      ],
      password: "",
      passwordRules: [
        v => !!v || 'Password is required',
        v => /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/.test(v) || 'Password must be valid',
      ],
    }
  },
  methods: {
    submit() {
      this.$refs.form.validate()
      if(this.valid) {
        let body = {
          username: this.username,
          password: this.password
        }

        httpNoToken.post('api/auth/login', body).then(res => {
          localStorage.setItem("token", res.data.token)
          this.$store.dispatch('user', res.data.user);
          this.$router.push("/")
        }).catch((reason) => {
          console.log(reason)
        })

      }

    },
    goRegister() {
      this.$router.push({name: 'registrationPage'})
    }
  },
  created() {
    document.title = 'Log in  | Finstrumental'
  }
}
</script>

<style scoped>

</style>