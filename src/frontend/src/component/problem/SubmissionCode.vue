<template>
  <vs-popup :title="title" v-model:active="popupActivo">
    <code>
      {{ data }}
    </code>
  </vs-popup>
</template>

<script setup>
import { ref, watch } from 'vue';

// eslint-disable-next-line no-undef
const props = defineProps({
  title: String,
  isActive: Boolean,
  submissionId: String
});
const popupActivo = ref(false);

watch(() => props.isActive, () => {
  popupActivo.value = true;
});


import { getSubmission } from "@/lib/api/submission.api";
const data = ref(null);

watch(() => props.submissionId, async (submissionId) => {
  if (submissionId) {
    const response = await getSubmission(submissionId);
    data.value = response.code;
  }
});

</script>