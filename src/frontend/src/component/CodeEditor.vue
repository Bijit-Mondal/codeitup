<template>
  <div class="code-editor">
    <vs-row vs-type="flex" vs-justify="space-between" vs-w="12" class="code-editor__config">
      <vs-col vs-type="flex" vs-justify="center" type="color" color="primary" vs-align="center" vs-lg="2" vs-sm="4">
        <vs-select
            class="selectExample"
            v-model="localLanguage"
        >
          <vs-select-item :key="item.id" :modelValue="item.id" :text="item.name" v-for="item in languages" />
        </vs-select>
      </vs-col>
      <vs-col vs-type="flex" vs-justify="center" type="color" color="primary" vs-align="center" vs-lg="2" vs-sm="4">
        <vs-select
            class="selectExample"
            v-model="theme"
        >
          <vs-select-item :key="index" :modelValue="item.value" :text="item.text" v-for="item,index in themes" />
        </vs-select>
      </vs-col>
    </vs-row>
    <div class="code-editor__ace">
      <v-ace-editor
          v-model:value="localContent"
          :lang="selectedLanguageMode"
          :theme="theme"
          style="height: 100%; width: 100%; margin: 0.02rem; border-radius: 5px"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";
import { VAceEditor } from "vue3-ace-editor";

// Load the language definition files used below
import "ace-builds/src-noconflict/mode-javascript";
import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/mode-groovy";
import "ace-builds/src-noconflict/mode-c_cpp";

import "ace-builds/src-noconflict/theme-tomorrow_night_eighties";
import "ace-builds/src-noconflict/theme-pastel_on_dark";
import "ace-builds/src-noconflict/theme-clouds";
import "ace-builds/src-noconflict/theme-dracula";

const props = defineProps({
  content: String,
  language: Number,
  languages: {
    type: Array,
    default: () => []
  }
});

const localContent = ref(props.content);
const localLanguage = ref(props.language);
const selectedLanguageMode = ref(getLanguageMode(props.language));

const emit = defineEmits(['update:language', 'update:content']);

watch(() => props.language, (newLanguage) => {
  localLanguage.value = newLanguage;
  selectedLanguageMode.value = getLanguageMode(newLanguage);
});

watch(() => props.content, (newContent) => {
  localContent.value = newContent;
});

watch(localLanguage, (newLanguage) => {
  emit('update:language', newLanguage);
  selectedLanguageMode.value = getLanguageMode(newLanguage);
});

watch(localContent, (newContent) => {
  emit('update:content', newContent);
});

function getLanguageMode(languageId) {
  const language = props.languages.find((language) => language.id === languageId);
  return language ? language.aceEditor : 'java';
}

const themes = ref([
  { text: 'Tomorrow Night', value: 'tomorrow_night_eighties', index: 0},
  { text: 'Pastel on Dark', value: 'pastel_on_dark', index: 1 },
  { text: 'Clouds', value: 'clouds', index: 2 },
  { text: 'Dracula', value: 'dracula', index: 3}
]);
const theme = ref(themes.value[0].value);
</script>


<style scoped>
.code-editor > .code-editor__config {
  margin-bottom: 0.5rem;
}
.code-editor > .code-editor__ace {
  height: 62vh;
}
</style>

<style>
.ace_content {
  padding-top: 0.8rem;
}
.ace_gutter {
  padding-top: 0.8rem;
}
.code-editor {
  padding: 0 0 0 0.4rem;
}
@media (max-width: 768px) {
  .code-editor {
    padding: 0.4rem 0 0 0;
  }
}
/* vs-select menu */
.vs-select--input {
  color: var(--text);
  background-color: var(--background);
}
.con-select {
  width: 130px;
}

.con-select-example {
  display: flex;
  align-items: center;
  justify-content: center;
}
.con-select .vs-select {
  width: 100%
}
.vs-select--options {
  background: var(--background) !important;
}
.vs-select--item {
  color: var(--text) !important;
  border: 0 !important;
}
.vs-select--item:focus, .vs-select--item:hover {
  background: var(--primary);
  color: var(--dark) !important;

}

/* vs button active */
.vs-button-border.isActive .vs-button--icon, .vs-button-border.isActive .vs-button--text, .vs-button-flat.isActive .vs-button--icon, .vs-button-flat.isActive .vs-button--text{
  color: var(--dark) !important;
}
@media (max-width: 550px) {
  .con-select {
    flex-direction: column;
  }
  .con-select .vs-select {
    width: 100%
  }
}


</style>
