<template>
  <div class="admin-view">
    <RouterView/>
  </div>
</template>
<script setup>
import { RouterView } from 'vue-router'
</script>
<style scoped>
.admin-view{
  margin: 0.5rem;
  background-color: var(--dark);
  border-radius: 5px;
  padding: 0.8rem 0.7rem;
}
</style>