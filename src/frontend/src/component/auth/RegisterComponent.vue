<template>
  <div class="login-box">
    <div v-if="isError">
      <vs-alert active="true" color="danger">
        {{ error }}
      </vs-alert>
    </div>
    <div v-if="isSuccess">
      <vs-alert active="true" color="success">
        {{  data }}
      </vs-alert>
    </div>
    <form @submit.prevent="submitForm">
      <vs-input
          v-for="(input, index) in inputs"
          :key="index"
          class="login-box__input"
          :danger="v$[input.field].$error"
          :danger-text="v$[input.field].$errors[0]?.$message"
          :placeholder="input.placeholder"
          :icon="input.icon"
          :type="input.type"
          :disabled="isPending"
          v-model="auth[input.field]"
          @blur="v$[input.field].$touch()"
      />
      <button type="submit" style="display: none;"></button>
      <vs-button color="primary" type="filled" @click="submitForm">Register</vs-button>
    </form>
  </div>
</template>


<script setup>
/* eslint-disable */
import { reactive, ref } from 'vue';

import { registerValidation } from '@/lib/vuelidate/validate.syntax';
import useVuelidate from '@vuelidate/core';

import { authQueries } from '@/lib/tanstack/auth.queries';

const { registerMutation } = authQueries();

const { mutateAsync: register, isError, isPending, isSuccess, data, error } = registerMutation();

const auth = reactive({
  name: '',
  profileName: '',
  email: '',
  password: ''
});


const v$ = useVuelidate(registerValidation, auth);

const inputs = ref([
  { placeholder: "Enter your full name", icon: "person", type: "text", field: "name" },
  { placeholder: "Enter your username", icon: "face", type: "text", field: "profileName" },
  { placeholder: "Enter your email address", icon: "mail", type: "email", field: "email" },
  { placeholder: "Enter your password", icon: "lock", type: "password", field: "password" }
]);


const submitForm = () => {
  v$.value.$touch();

  if (v$.value.$invalid) {
    console.log('Form is invalid');
    return;
  }
  console.log('Form is valid');
  register(auth);
};

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