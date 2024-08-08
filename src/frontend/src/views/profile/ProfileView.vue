<template>
  <h1>You are Logged in</h1>
  <vs-button color="danger" @click="logout">Logout</vs-button>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from "@/lib/store/auth.store";

const router = useRouter();
const authStore = useAuthStore();

const logout = async () => {
  await authStore.logout();
  await router.push({name: 'auth'}); // Ensure the 'login' route name is correct
};
</script>