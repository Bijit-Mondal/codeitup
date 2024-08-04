<template>
  <div class="split problem-box">
    <div id="split-0">
      <ProblemMDView
          :title="problem.title"
          :slug="problem.slug"
          :description="problem.description"
          :testcases-link="problem.testCases"
          :difficulty="problem.difficulty"
      />
    </div>
    <div id="split-1">
      <ProblemMDEditor
          v-model:title="problem.title"
          v-model:slug="problem.slug"
          v-model:description="problem.description"
          v-model:testcases-link="problem.testCases"
          v-model:difficulty="problem.difficulty"
      />
      <div class="problem-editor__submit">
        <vs-row vs-type="flex" vs-justify="flex-end" vs-w="12">
          <vs-col vs-type="flex" vs-justify="flex-end" vs-align="center" vs-w="4">
            <vs-button color="primary" type="border" @click="submitProblem">Submit</vs-button>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive, watchEffect} from "vue";
import { onMounted } from "vue";

import ProblemMDEditor from "@/component/admin/ProblemMDEditor.vue";
import  ProblemMDView from "@/component/admin/ProblemMDView.vue";
import Split from 'split.js';
onMounted(() => {
  Split(['#split-0', '#split-1'], {
    minSize: [40, 300],
    snapOffset: 20,
    gutterSize: 4,
  });
});

import { problemValidation } from "@/lib/vuelidate/validate.syntax";
import useVuelidate from "@vuelidate/core";
const problem = reactive({
  title: '',
  slug: '',
  description: '',
  testCases: '',
  difficulty: '',
})
const v$ = useVuelidate(problemValidation, problem);

import { useNotification } from "@/lib/composable/notification";
import {inject} from "vue";
const vs = inject('$vs')
const { successMessage, errorMessage, warningMessage, loading } = useNotification()

import { problemAdminQueries } from "@/lib/tanstack/problem.admin.queries";
const { addProblemMutation } = problemAdminQueries();
const { mutateAsync: addProblem, isError, isPending, isSuccess, error } = addProblemMutation();

const submitProblem = () => {
  v$.value.$touch();
  if (v$.value.$invalid) {
    for(let key in v$.value.$errors) {
      warningMessage(vs,`${v$.value.$errors[key]?.$propertyPath} ${v$.value.$errors[key]?.$message}`)
    }
    return
  }
  addProblem(problem)
}

watchEffect(() => {
  if (isSuccess.value)
    successMessage(vs, 'Problem created successfully')
  if (isPending.value)
    loading(vs)
  if(!isPending.value)
    vs.loading.close()
  if (isError.value)
    errorMessage(vs, error.value)
})


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
