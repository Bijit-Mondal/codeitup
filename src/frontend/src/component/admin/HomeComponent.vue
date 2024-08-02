<template>
  <div class="admin-home-component">
    <div class="admin-home-component__buttons">
      <vs-row vs-w="12">
        <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="4">
          <vs-button color="danger" type="filled" icon="add" @click="showOnConsole">Language</vs-button>
        </vs-col>
        <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="4">
          <vs-button color="success" type="filled" icon="add" @click="showOnConsole">Contest</vs-button>
        </vs-col>
        <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="4">
          <vs-button color="primary" type="filled" icon="add" @click="showOnConsole">Problem</vs-button>
        </vs-col>
      </vs-row>
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
import ProblemsTable from "./ProblemsTable";
</script>