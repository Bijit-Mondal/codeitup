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
            <vs-button color="primary" type="border" @click="handleSubmit">
              {{ showUpdateButton ? 'Update' : 'Add' }}
            </vs-button>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, reactive, watchEffect } from "vue";
import { useRoute } from "vue-router";
import { inject } from "vue";
import Split from 'split.js';
import CodeEditor from "@/component/CodeEditor.vue";
import { languageQueries } from "@/lib/tanstack/language.queries";
import { defaultCodeAdminQueries } from "@/lib/tanstack/defaultCode.admin.queries";
import { useNotification } from "@/lib/composable/notification";
import { defaultCodeValidation } from "@/lib/vuelidate/validate.syntax";
import useVuelidate from "@vuelidate/core";

onMounted(() => {
  Split(['#split-0', '#split-1'], {
    minSize: [300, 300],
    snapOffset: 20,
    gutterSize: 4,
  });
});

const route = useRoute();
const problemId = route.params.id;

const { getAllLanguageQuery } = languageQueries();
const { data: languages } = getAllLanguageQuery();

const {
  addDefaultCodeMutation,
  getDefaultCodeByProblemAdminQuery,
  updateDefaultCodeMutation,
} = defaultCodeAdminQueries();

const { mutateAsync: addDefaultCode, isError, isPending, isSuccess, error } = addDefaultCodeMutation();
const { mutateAsync: updateDefaultCode, isError: isUpdateError, isPending: isUpdatePending, isSuccess: isUpdateSuccess, error: updateError } = updateDefaultCodeMutation();

const vs = inject('$vs');
const { successMessage, errorMessage, warningMessage, loading } = useNotification();

const defaultCode = reactive({
  problemId,
  runnerCode: '',
  code: '',
  languageId: 1,
});

const { data: existingDefaultCode } = getDefaultCodeByProblemAdminQuery(problemId);

const v$ = useVuelidate(defaultCodeValidation, defaultCode);

const showUpdateButton = computed(() => {
  return existingDefaultCode.value?.some((code) => code.languageId === defaultCode.languageId);
});

const updateDefaultCodeState = () => {
  if (existingDefaultCode.value) {
    const existingCode = existingDefaultCode.value.find((code) => code.languageId === defaultCode.languageId);
    if (existingCode) {
      defaultCode.runnerCode = existingCode.runnerCode;
      defaultCode.code = existingCode.code;
    } else {
      defaultCode.runnerCode = '';
      defaultCode.code = '';
    }
  }
};

watchEffect(() => {
  updateDefaultCodeState();
});

const handleSubmit = async () => {
  v$.value.$touch();
  if (v$.value.$invalid) {
    v$.value.$errors.forEach((error) => {
      warningMessage(vs, `${error.$propertyPath} ${error.$message}`);
    });
    return;
  }
  if (showUpdateButton.value) {
    await updateDefaultCode({ problemId, languageId: defaultCode.languageId, data: defaultCode });
  } else {
    await addDefaultCode(defaultCode);
  }
};

watchEffect(() => {
  if (isSuccess.value) successMessage(vs, 'Default code is added successfully');
  if (isUpdateSuccess.value) successMessage(vs, 'Default code is updated successfully');
  if (isPending.value || isUpdatePending.value) loading(vs);
  if (!isPending.value && !isUpdatePending.value) vs.loading.close();
  if (isError.value) errorMessage(vs, error.value);
  if (isUpdateError.value) errorMessage(vs, updateError.value);
});
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
