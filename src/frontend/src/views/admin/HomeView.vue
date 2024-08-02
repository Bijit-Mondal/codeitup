<template>
  <div class="admin-home">
    <HomeComponent/>
  </div>
</template>
<script setup>
import HomeComponent from "@/component/admin/HomeComponent"
</script>
<style scoped>
.admin-home{
  margin: 0.5rem;
  background-color: var(--dark);
  border-radius: 5px;
  padding: 0.8rem 0.7rem;
}
</style>