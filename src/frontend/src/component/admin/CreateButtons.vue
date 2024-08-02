<template>
  <vs-row vs-w="12">
    <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="4">
      <vs-button color="danger" type="filled" icon="add" :to="{ }">Language</vs-button>
    </vs-col>
    <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="4">
      <vs-button color="success" type="filled" icon="add" @click="showOnConsole">Contest</vs-button>
    </vs-col>
    <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="4">
      <vs-button color="primary" type="filled" icon="add" :to="{ name: 'create-problem' }">Problem</vs-button>
    </vs-col>
  </vs-row>
</template>
<script setup>

</script>