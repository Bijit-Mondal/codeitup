<template>
  <vs-alert
      v-model:active="isProblemGettingError"
      title="Error fetching problems"
      color="danger"
      close-icon="close">
    {{ problemGettingError }}
  </vs-alert>

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
            <vs-col vs-type="flex" vs-justify="flex-end" vs-align="center" vs-w="6">
              <vs-button color="primary" :to="{ name: 'default-code', params: { id: problemData?.id } }" icon="add" size="small">Default Code</vs-button>
            </vs-col>
            <vs-col vs-type="flex" vs-justify="flex-end" vs-align="center" vs-w="4">
              <vs-button color="primary" type="border" @click="submitProblem">Submit</vs-button>
            </vs-col>
          </vs-row>
        </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, watchEffect, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import Split from 'split.js';
import ProblemMDEditor from '@/component/admin/ProblemMDEditor.vue';
import ProblemMDView from '@/component/admin/ProblemMDView.vue';
import { problemValidation } from '@/lib/vuelidate/validate.syntax';
import useVuelidate from '@vuelidate/core';
import { problemAdminQueries } from '@/lib/tanstack/problem.admin.queries';
import { useNotification } from '@/lib/composable/notification';
import { inject } from 'vue';

const route = useRoute();
const slug = route.params.slug;

const { updateProblemMutation, getProblemBySlugAdminQuery } = problemAdminQueries();
const { mutateAsync: updateProblem, isError, isPending, isSuccess, error } = updateProblemMutation();

const { data: problemData, isError: isProblemGettingError, error: problemGettingError } = getProblemBySlugAdminQuery(slug);

const problem = reactive({
  title: '',
  slug: '',
  description: '',
  testCases: '',
  difficulty: '',
});

watchEffect(() => {
  if (problemData.value) {
    console.log(problemData.value)
    problem.title = problemData.value.title;
    problem.slug = problemData.value.slug;
    problem.description = problemData.value.description;
    problem.testCases = problemData.value.testCases;
    problem.difficulty = problemData.value.difficulty;
  }
});

const v$ = useVuelidate(problemValidation, problem);

const vs = inject('$vs');
const { successMessage, errorMessage, warningMessage, loading } = useNotification();

const submitProblem = async () => {
  v$.value.$touch();
  if (v$.value.$invalid) {
    for (let key in v$.value.$errors) {
      warningMessage(vs, `${v$.value.$errors[key]?.$propertyPath} ${v$.value.$errors[key]?.$message}`);
    }
    return;
  }
  updateProblem({ slug, problem });
};

watchEffect(() => {
  if (isSuccess.value) {
    successMessage(vs, 'Problem updated successfully');
  }
  if (isPending.value) {
    loading(vs);
  }
  if (!isPending.value) {
    vs.loading.close();
  }
  if (isError.value) {
    errorMessage(vs, error.value);
  }
});

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
  background-position: 50%;
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
