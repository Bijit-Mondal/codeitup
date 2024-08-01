<template>
  <div class="auth-box">
    <ButtonGroup :options="options" :currentComponent="currentComponent" @update:currentComponent="toggleComponent" />
    <div class="auth-box__component">
      <keep-alive>
        <component :is="currentComponent"></component>
      </keep-alive>
    </div>
  </div>
</template>

<script setup>
import Login from '../../component/auth/LoginComponent.vue'
import ButtonGroup from '../../component/base/ButtonGroup.vue'
import { defineAsyncComponent, markRaw, ref, shallowRef } from "vue";


const Register = defineAsyncComponent(() => import('../../component/auth/RegisterComponent.vue'));

const options = ref([
  { label: 'Register', value: markRaw(Register) },
  { label: 'Login', value: markRaw(Login) },
]);

const currentComponent = shallowRef(options.value[1].value);

function toggleComponent(com) {
  currentComponent.value = com;
}
</script>

<style scoped>
.auth-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 80vh;
  & .auth-box__component{
    margin-top: 2rem;
  }
}
</style>