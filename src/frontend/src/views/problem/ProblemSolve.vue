<template>
  <vs-alert
      v-model:active="isProblemGettingError"
      title="Error fetching problems"
      color="danger"
      close-icon="close">
    {{ problemGettingError }}
  </vs-alert>
  <vs-alert
      v-model:active="isLanguageGettingError"
      title="Error fetching problems"
      color="danger"
      close-icon="close">
    {{ languageGettingError }}
  </vs-alert>
  <div class="split problem-box">
    <div id="split-0">
      <vs-tabs v-if="!isProblemLoading" class="problem-tabs__tabs" alignment="fixed" hover-line hover-text>
        <vs-tab label="Problem">
          <div>
            <ProblemMDView
                :title="problemData?.title"
                :description="problemData?.description"
                :difficulty="problemData?.difficulty"
            />
          </div>
        </vs-tab>
        <vs-tab label="Solution">
          <div>
            <h1>Solution</h1>
          </div>
        </vs-tab>
        <vs-tab label="Submission">
          <div>
            <h1>Submission</h1>
          </div>
        </vs-tab>
      </vs-tabs>
      <div v-else>
        <SkeletonList/>
      </div>
    </div>
    <div id="split-1">
      <CodeEditor
          v-if="!isLanguageLoading || !isProblemLoading"
          :languages="languages"
          v-model:language="submissionCode.languageId"
          v-model:content="submissionCode.code"
      />
      <div v-else>
        <SkeletonList/>
      </div>
      <div class="code-editor__submit">
        <vs-row vs-type="flex" vs-justify="flex-end" vs-w="12">
          <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="2">
            <vs-button color="primary" type="border" :disabled="isDrawerOpen" @click="submitProblem">
              Submit
            </vs-button>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
  <Drawer :is-open="isDrawerOpen" @update:isOpen="isDrawerOpen = $event">
    <TestCaseResult :test-cases="testCases"/>
  </Drawer>
</template>
<script setup>
/* eslint-disable */
import {defineAsyncComponent, onMounted, reactive, ref, watchEffect} from "vue"
import Split from 'split.js'
import { useRoute } from 'vue-router';
import Drawer from '@/component/base/DrawerComponent.vue';

const TestCaseResult = defineAsyncComponent(()=> import("@/component/problem/TestCaseResult.vue"))

onMounted(() => {
  Split(['#split-0', '#split-1'],
      {
        minSize: [40,300],
        snapOffset: 20,
        gutterSize: 4,
      })
})
import ProblemMDView from "@/component/ProblemMDView.vue"
import CodeEditor from "@/component/CodeEditor.vue"
import SkeletonList from "@/component/skeleton/SkeletonList";

import { problemUserQueries } from "@/lib/tanstack/problem.user.queries"
import { languageQueries } from "@/lib/tanstack/language.queries";
const route = useRoute();
const slug = route.params.slug;
const { getProblemBySlugQuery } = problemUserQueries();
const { data: problemData, isError: isProblemGettingError, error: problemGettingError, isLoading: isProblemLoading } = getProblemBySlugQuery(slug);
const { getAllLanguageQuery } = languageQueries();
const { data: languages, isError: isLanguageGettingError, error: languageGettingError, isLoading: isLanguageLoading } = getAllLanguageQuery();

const submissionCode = reactive({
  code : "",
  languageId : 1,
  problemSlug : slug
})

watchEffect(() => {
  if (submissionCode.languageId && problemData.value?.defaultCode) {
    const defaultCode = problemData.value.defaultCode.find(code => code.languageId === submissionCode.languageId);
    if (defaultCode) {
      submissionCode.code = defaultCode.code;
    } else {
      submissionCode.code = '';
    }
  }
})

import { submissionQueries } from "@/lib/tanstack/submission.queries";
const { makeSubmissionMutation } = submissionQueries();

import { useNotification } from "@/lib/composable/notification";
import {inject} from "vue";
import {getSubmission} from "@/lib/api/submission.api";
const vs = inject('$vs')
const { successMessage, errorMessage, loading } = useNotification()

const {
  mutate: makeSubmission,
  isPending :isSubmissionPending,
  isSuccess: isSubmissionSuccess,
  isError: isSubmissionError,
  error: submissionError
} = makeSubmissionMutation();

const isDrawerOpen = ref(false);
let testCases = ref([]);

const submitProblem = () => {
  makeSubmission(submissionCode, {
    onSuccess(data) {
      isDrawerOpen.value = true;
      pollWithBackOff(data.submissionId, 10);
    },
  });

}

// Idea taken from Harkirat Singh
const pollWithBackOff = async (id,retries) =>{
  if(retries === 0){
    errorMessage(vs, "Not able to get status. See Submissions")
    return
  }
  const response = await getSubmission(id)
  if(response.submissionResult === "PENDING"){
    testCases.value = response.testCases
    console.log(testCases.value)
    await new Promise((resolve) => setTimeout(resolve, 2.5 * 1000));
    pollWithBackOff(id,retries-1)
  } else {
    if(response.submissionResult === "ACCEPTED"){
      successMessage(vs, "Accepted!")
      testCases.value = response.testCases
    } else if(response.submissionResult === "REJECTED"){
      errorMessage(vs, "Not Accepted!")
      testCases.value = response.testCases
    }
  }
}

watchEffect(() => {
  if (isSubmissionSuccess.value)
    successMessage(vs, 'Evaluation starts...')
  if (isSubmissionPending.value)
    loading(vs)
  if(!isSubmissionPending.value)
    vs.loading.close()
  if (isSubmissionError.value)
    errorMessage(vs, submissionError.value)
})
</script>

<style>
.problem-box {
  margin: 0.5rem;
  background-color: var(--dark);
  border-radius: 5px;
  padding: 0.8rem 0.7rem;
}
.split {
  display: flex;
  flex-direction: row;
  min-height: calc(100vh - 55px);
}
.code-editor__submit {
  margin-top: 1rem;
}
.gutter {
  background-color: var(--dark);
  background-repeat: no-repeat;
  background-position: 5%;
  border-radius: 10px;
  transition: background-color 0.2s;
  &:hover {
    background-color: var(--background);
  }
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