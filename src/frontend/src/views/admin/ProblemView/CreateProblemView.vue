<template>
  <div class="split problem-box">
    <div id="split-0">
      <ProblemMDView
          :title="title"
          :slug="slug"
          :description="description"
          :testcases-link="testcasesLink"
          :difficulty="difficulty"
      />
    </div>
    <div id="split-1">
      <ProblemMDEditor
          v-model:title="title"
          v-model:slug="slug"
          v-model:description="description"
          v-model:testcases-link="testcasesLink"
          v-model:difficulty="difficulty"
      />
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { defineAsyncComponent, onMounted } from "vue";

const title = ref("");
const slug = ref("");
const description = ref("");
const testcasesLink = ref("");
const difficulty = ref("");

const ProblemMDEditor = defineAsyncComponent(() => import("../../../component/admin/ProblemMDEditor.vue"));
const ProblemMDView = defineAsyncComponent(() => import("../../../component/admin/ProblemMDView.vue"));

import Split from 'split.js';
onMounted(() => {
  Split(['#split-0', '#split-1'], {
    minSize: [40, 300],
    snapOffset: 20,
    gutterSize: 4,
  });
});
</script>

<style>
.problem-box {
  margin: 0.3rem;
  background-color: var(--dark);
  border-radius: 5px;
}
.split {
  display: flex;
  flex-direction: row;
  min-height: calc(100vh - 55px);
}
.gutter {
  background-color: var(--dark);
  background-repeat: no-repeat;
  background-position: 5%;
  border-radius: 10px;
  transition: background-color 0.2s;
}
.gutter:hover {
  background-color: var(--background);
}
.gutter.gutter-horizontal {
  background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAYAAADkftS9AAAAIklEQVQoU2M4c+bMfxAGAgYYmwGrIIiDjrELjpo5aiZeMwF+yNnOs5KSvgAAAABJRU5ErkJggg==');
  cursor: col-resize;
}
@media (max-width: 768px) {
  .split {
    flex-direction: column;
  }
  #split-0, #split-1 {
    width: 100% !important;
  }
}
</style>
