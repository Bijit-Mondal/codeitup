<template>
  <div class="otp-box">
    <form @submit.prevent="submitOTP">
      <vs-input
          placeholder="Enter your OTP"
          class="login-box__input"
          :danger="v$.otp.$error"
          :danger-text="v$.otp.$errors[0]?.$message"
          val-icon-danger="error"
          type="number"
          v-model="otp.otp"
          @blur="v$.otp.$touch()"
      />
      <button style="display: none" type="submit" ></button>
      <vs-button color="primary" type="filled" @click="submitOTP">Submit</vs-button>
    </form>
  </div>
</template>
<style scoped>
.login-box__input {
  margin-bottom: 1rem;
}
</style>
<style>
.otp-box {
  & .vs-input--placeholder {
    color: var(--text-disable);
  }
  & .vs-input--input{
    background-color: var(--dark);
    color: var(--text);
  }
  & .vs-button {
    width: 100%;
  }
}
</style>
<script setup>
/* eslint-disable */
import {reactive, watchEffect} from 'vue';
import { useRouter } from "vue-router";

import { useNotification } from "@/lib/composable/notification";
import {inject} from "vue";
const vs = inject('$vs')
const { successMessage, errorMessage, warningMessage,loading } = useNotification()

const router = useRouter();

import { otpValidation} from "@/lib/vuelidate/validate.syntax";
import useVuelidate from '@vuelidate/core';
const otp = reactive({
  otp: '',
  email: router.currentRoute.value.query.email
});
const v$ = useVuelidate(otpValidation, otp);

import { authQueries } from '@/lib/tanstack/auth.queries';
const { otpValidateMutation } = authQueries();
const { mutateAsync: validate, isError, isPending, isSuccess, data, error } = otpValidateMutation()

const submitOTP = () => {
  v$.value.$touch()
  if (v$.value.$invalid) {
    warningMessage(vs,'Something went wrong')
    return
  }
  validate(otp)
};

watchEffect(() => {
  if (isSuccess.value) {
    successMessage(vs, 'OTP verified successfully, Login now!')
  }
  if (isPending.value)
    loading(vs)
  if(!isPending.value)
    vs.loading.close()
  if (isError.value) {
    errorMessage(vs, error.value)
  }
})
</script>