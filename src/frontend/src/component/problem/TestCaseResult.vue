<template>
  <div>
    <h1 style="margin-bottom: 10px; margin-top: 20px">TestCases</h1>
    <div v-for="(localTestCase, index) in testCases" :key="index">
      <vs-chip :color="getChipColor(localTestCase.testCasesResult)" transparent>
        <vs-avatar :icon="getChipIcon(localTestCase.testCasesResult)" :color="getChipColor(localTestCase.testCasesResult)" />
          {{ localTestCase.testCasesResult }}  {{ localTestCase.ind }}
      </vs-chip>
    </div>
  </div>
</template>

<script setup>
// eslint-disable-next-line no-undef

// eslint-disable-next-line no-undef,no-unused-vars
const props = defineProps({
  testCases: {
    type: Array,
    required: true
  }
});
const getChipColor = (status) => {
  switch (status) {
    case 'ACCEPTED':
      return 'success';
    case 'WRONG_ANSWER':
      return 'danger';
    default:
      return 'primary';
  }
};

const getChipIcon = (status) => {
  switch (status) {
    case 'PASSED':
      return 'check_circle';
    case 'FAILED':
      return 'cancel';
    default:
      return 'hourglass_top';
  }
};
</script>