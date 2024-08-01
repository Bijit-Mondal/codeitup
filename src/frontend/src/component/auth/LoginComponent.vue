<template>
  <div class="login-box">
    <form @submit.prevent="submitForm">
      <vs-input
          v-for="(input, index) in inputs"
          :key="index"
          class="login-box__input"
          :danger="v$[input.field].$error"
          :danger-text="v$[input.field].$errors[0]?.$message"
          val-icon-danger="error"
          :placeholder="input.placeholder"
          :icon="input.icon"
          :type="input.type"
          v-model="auth[input.field]"
          @blur="v$[input.field].$touch()"
      />
      <button type="submit" style="display: none;"></button>
      <vs-button :disabled="isPending" @click="submitForm" color="primary" type="filled">Login</vs-button>
    </form>
  </div>
</template>

<script setup>
import {reactive, watchEffect} from 'vue';

import { loginValidation } from '@/lib/vuelidate/validate.syntax';
import useVuelidate from '@vuelidate/core';
const auth = reactive({
  email: '',
  password: ''
});
const v$ = useVuelidate(loginValidation, auth);

import { useNotification } from "@/lib/composable/notification";
import {inject} from "vue";
const vs = inject('$vs')
const { successMessage, errorMessage, loading } = useNotification()


import { authQueries } from '@/lib/tanstack/auth.queries';
const { loginMutation } = authQueries();
const { mutateAsync: login, isError, isPending, isSuccess, error } = loginMutation();

const inputs = [
  {
    placeholder: "Enter your email address",
    icon: "mail",
    type: "email",
    field: "email"
  },
  {
    placeholder: "Enter your password",
    icon: "lock",
    type: "password",
    field: "password"
  }
];

const submitForm = () => {
  v$.value.$touch();
  if (v$.value.$invalid) {
    console.log('Form is invalid')
    return
  }
  login(auth)
};

watchEffect(() => {
  if (isSuccess.value)
    successMessage(vs, 'Logged in successfully')
  if (isPending.value)
    loading(vs)
  if(!isPending.value)
    vs.loading.close()
  if (isError.value)
    errorMessage(vs, error.value)
})

</script>
<style scoped>
.login-box__input {
  margin-bottom: 1rem;
}
</style>
<style>
.login-box {
  & .vs-input--placeholder {
    color: var(--text-disable);
  }
  & .vs-input--input{
    background-color: var(--dark);
    color: var(--text);
  }
  & .vs-input--icon{
    color: var(--text-disable);
  }
  & .vs-button {
    width: 100%;
  }
}
</style>