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
      <ProblemMDView
          v-if="!isProblemLoading"
          :title="problemData?.title"
          :description="problemData?.description"
          :difficulty="problemData?.difficulty"
      />
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
            <vs-button color="primary" type="border">
              Submit
            </vs-button>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
</template>
<script setup>
import {onMounted, reactive, watchEffect} from "vue"
import Split from 'split.js'
import { useRoute } from 'vue-router';
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

</script>

<style scoped>
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
</style>
<style>
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