<template>
  <div class="split default-box">
    <div id="split-0">
      <p style="margin-bottom: 10px">Runner Code</p>
      <CodeEditor
          v-model:content="defaultCode.runnerCode"
          v-model:language="defaultCode.languageId"
          :languages="languages"
      />
    </div>
    <div id="split-1">
      <p style="margin-bottom: 10px">Default User Code</p>
      <CodeEditor
          v-model:content="defaultCode.code"
          v-model:language="defaultCode.languageId"
          :languages="languages"
      />
      <div class="code-editor__submit">
        <vs-row vs-type="flex" vs-justify="flex-end" vs-w="12">
          <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="2">
            <vs-button color="primary" type="border" @click="submit">Add</vs-button>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, watchEffect} from "vue";
import Split from 'split.js';

onMounted(() => {
  Split(['#split-0', '#split-1'], {
    minSize: [300, 300],
    snapOffset: 20,
    gutterSize: 4,
  });
});


import CodeEditor from "@/component/CodeEditor.vue";
import { languageQueries } from "@/lib/tanstack/language.queries";
import { defaultCodeAdminQueries } from "@/lib/tanstack/defaultCode.admin.queries";
import { useRoute } from "vue-router";

const { getAllLanguageQuery } = languageQueries();
const { data: languages } = getAllLanguageQuery();

const { addDefaultCodeMutation } = defaultCodeAdminQueries();
const { mutateAsync: addDefaultCode, isError, isPending, isSuccess, error } = addDefaultCodeMutation();

const route = useRoute();
const problemId = route.params.id;

import { useNotification } from "@/lib/composable/notification";
import {inject} from "vue";
const vs = inject('$vs')
const { successMessage, errorMessage, warningMessage, loading } = useNotification()


import { defaultCodeValidation } from "@/lib/vuelidate/validate.syntax";
import useVuelidate from "@vuelidate/core";
const defaultCode = reactive({
  problemId: problemId,
  runnerCode: '',
  code: '',
  languageId: 1,
});
const v$ = useVuelidate(defaultCodeValidation,defaultCode);

const submit = () => {
  v$.value.$touch()
  if(v$.value.$invalid) {
    for(let key in v$.value.$errors) {
      warningMessage(vs,`${v$.value.$errors[key]?.$propertyPath} ${v$.value.$errors[key]?.$message}`)
    }
    return
  }
  addDefaultCode(defaultCode)
}
watchEffect(() => {
  if (isSuccess.value)
    successMessage(vs, 'Default code is added successfully')
  if (isPending.value)
    loading(vs)
  if(!isPending.value)
    vs.loading.close()
  if (isError.value)
    errorMessage(vs, error.value)
})

</script>

<style>
.default-box {
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
.code-editor__submit {
  margin-top: 1rem;
}
</style>
