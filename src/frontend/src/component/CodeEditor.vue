<template>
  <div class="code-editor">
    <vs-row vs-type="flex" vs-justify="space-between" vs-w="12" class="code-editor__config">
      <vs-col vs-type="flex" vs-justify="center" type="color" color="primary" vs-align="center" vs-lg="2" vs-sm="4">
        <vs-select
            class="selectExample"
            v-model="select"
        >
          <vs-select-item :key="index" :modelValue="item.value" :text="item.text" v-for="item,index in options" />
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
          v-model:value="content"
          lang="java"
          :theme="theme"
          style="height: 100%; width: 100%; margin: 0.02rem; border-radius: 5px"
      />
    </div>
    <div class="code-editor__submit">
      <vs-row vs-type="flex" vs-justify="flex-end" vs-w="12">
        <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="2">
          <vs-button color="primary" type="border" @click="showOnConsole">Submit</vs-button>
        </vs-col>
      </vs-row>
    </div>
  </div>
</template>

<script setup>
import {ref, } from "vue";
import ace from "ace-builds";

import { VAceEditor } from "vue3-ace-editor";
import "ace-builds/src-noconflict/mode-java"; // Load the language definition file used below

import "ace-builds/src-noconflict/theme-pastel_on_dark";
import "ace-builds/src-noconflict/theme-clouds"
import "ace-builds/src-noconflict/theme-dracula"

import snippetsJavaUrl from "file-loader?esModule=false!ace-builds/src-noconflict/snippets/java";
ace.config.setModuleUrl("ace/snippets/java", snippetsJavaUrl);

const content = ref(
    `public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}`
);

const options = ref([
  {text:'Java',value:0},
  {text:'C++',value:2},
  {text:'JavaScript',value:3},
])

const select = ref(options.value[0].text);

const themes = ref([
  { text: 'Pastel on Dark', value: 'pastel_on_dark', index: 0 },
  { text: 'Clouds', value: 'clouds', index: 1 },
  { text: 'Dracula', value: 'dracula', index: 2}
])
const theme = ref(themes.value[0].value)

const showOnConsole = () => {
  console.log(content.value);
};
</script>

<style scoped>
.code-editor > .code-editor__submit {
  margin-top: 1rem;
}
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
