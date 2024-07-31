<template>
  <div class="login-box">
    <div>
      <vs-input
          v-for="(input, index) in inputs"
          :key="index"
          class="login-box__input"
          :placeholder="input.placeholder"
          :icon="input.icon"
          :type="input.type"
          v-model="input.value"
      />

      <vs-button color="primary" type="filled">Login</vs-button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";


const inputs = ref([
  {
    placeholder: "Enter your email address",
    icon: "mail",
    type: "email",
    value: ""
  },
  {
    placeholder: "Enter your password",
    icon: "lock",
    type: "password",
    value: ""
  }
]);
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