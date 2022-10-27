<template>
  <v-row>
    <v-col/>
    <v-col cols="3">
      <h2>Registration</h2>
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
        <v-text-field
            v-model="retypedPassword"
            :append-icon="showRetypedPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="retypedPasswordRules"
            :type="showRetypedPassword ? 'text' : 'password'"
            label="Repeat Password"
            counter
            @click:append="showRetypedPassword = !showRetypedPassword"
        ></v-text-field>
        <v-text-field
            v-model="firstName"
            :rules="firstNameRules"
            label="First name"
            required
        ></v-text-field>
        <v-text-field
            v-model="lastName"
            :rules="lastNameRules"
            label="Last name"
            required
        ></v-text-field>
        <v-text-field
            v-model="email"
            :rules="emailRules"
            label="Email"
            required
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
            @click="goLogin"
        >
          Login
        </v-btn>
      </v-form>
    </v-col>
    <v-col/>
  </v-row>
</template>

<script>

import {httpNoToken} from "../../http-common";

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
      retypedPassword: "",
      showRetypedPassword: false,
      retypedPasswordRules: [
        v => !!v || 'Repeat password is required',
        v => /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/.test(v) || 'Password must be valid',
        v => v === this.password || 'Passwords do not match'
      ],
      firstName: "",
      firstNameRules: [
        v => !!v || 'First name is required',
        v => (v && v.length <= 16) || 'First name must be less than 16 characters',
      ],
      lastName: "",
      lastNameRules: [
        v => !!v || 'Last name is required',
        v => (v && v.length <= 16) || 'Last name must be less than 16 characters',
      ],
      email: "",
      emailRules: [
        v => !!v || 'Email is required',
        v => (v && v.length <= 50) || 'Email must be less than 50 characters',
      ],
    }
  },
  methods: {
    submit() {
      this.$refs.form.validate()
      if(this.valid) {
        let body = {
          username: this.username,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
        }


        httpNoToken.post('api/auth/register', body).then(res => {
          this.$router.push({name: 'loginPage'})
        }).catch((reason) => {
          console.log(reason)
        })

      }

    },
    goLogin() {
      this.$router.push({name: 'loginPage'})
    }
  },
  created() {
    document.title = 'Registration | Finstrumental'
  }
}
</script>

<style scoped>

</style>