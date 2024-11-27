<template>
  <vs-popup :title="title" v-model:active="popupActivo">
    <pre><code>{{ formattedCode }}</code></pre>
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
const formattedCode = ref('');

watch(() => props.submissionId, async (submissionId) => {
  if (submissionId) {
    const response = await getSubmission(submissionId);
    // Directly using the raw code, formatting using replace to handle newlines and escape characters
    formattedCode.value = response.code.replace(/\\n/g, '\n'); // Replace escaped newlines with actual newlines
  }
});
</script>
