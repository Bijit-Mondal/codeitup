<template>
  <div class="admin-home-component">
    <div class="admin-home-component__buttons">
      <CreateButtons/>
    </div>
    <div class="admin-home-component__problems">
      <ProblemsTable/>
    </div>
  </div>
</template>
<style scoped>
.admin-home-component {
  & .admin-home-component__problems{
    margin-top: 1rem;
  }
}
</style>
<script setup>
import CreateButtons from "@/component/admin/CreateButtons.vue";
import ProblemsTable from "@/component/admin/ProblemsTable.vue";
</script>