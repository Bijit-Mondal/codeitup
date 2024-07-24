<template>
  <v-ace-editor
    v-model:value="content"
    lang="java"
    theme="cloud9_night"
    style="height: calc(100vh - 30px); width: 50%"
  />
  <vs-button color="danger" @click="showOnConsole" type="filled"
    >Danger</vs-button
  >
</template>

<script setup>
import { ref } from "vue";
import ace from "ace-builds";

import { VAceEditor } from "vue3-ace-editor";
import "ace-builds/src-noconflict/mode-java"; // Load the language definition file used below
import "ace-builds/src-noconflict/theme-cloud9_night"; // Load the theme definition file used below

import snippetsJavaUrl from "file-loader?esModule=false!ace-builds/src-noconflict/snippets/java";
ace.config.setModuleUrl("ace/snippets/java", snippetsJavaUrl);
const content = ref();

const showOnConsole = () => {
  console.log(content.value);
};
</script>

<style></style>
