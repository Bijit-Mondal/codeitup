<template>
  <v-ace-editor
      v-model:value="content"
      lang="java"
      theme="dracula"
      style="height: calc(100vh - 40px); width: calc(100% - 2px); margin: 0.02rem; border-radius: 1px"
  />
  <vs-button color="primary" @click="showOnConsole">Danger</vs-button>
</template>

<script setup>
import { ref } from "vue";
import ace from "ace-builds";

import { VAceEditor } from "vue3-ace-editor";
import "ace-builds/src-noconflict/mode-java"; // Load the language definition file used below
import "ace-builds/src-noconflict/theme-dracula"; // Load the theme definition file used below
import "ace-builds/src-noconflict/theme-clouds"; // Load the theme definition file used below

import snippetsJavaUrl from "file-loader?esModule=false!ace-builds/src-noconflict/snippets/java";
ace.config.setModuleUrl("ace/snippets/java", snippetsJavaUrl);

const content = ref(
    `public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}`
);

const showOnConsole = () => {
  console.log(content.value);
};
</script>

<style>
.ace_gutter{
  width: 0em !important;
}
.ace_scroller{
  left: 0px !important;
}
</style>
