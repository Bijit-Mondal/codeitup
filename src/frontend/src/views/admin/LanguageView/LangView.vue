<template>
  <vs-alert
      v-model:active="isError"
      title="Error adding language"
      color="danger"
      close-icon="close">
    {{ error }}
  </vs-alert>
  <vs-alert
      v-model:active="isSuccess"
      title="Language added successfully"
      color="success"
      close-icon="close">
    Language added successfully
  </vs-alert>
  <div class="language">
    <vs-row vs-w="12">
      <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="3">
        <vs-input v-model="lang.name" placeholder="Name" />
      </vs-col>
      <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="3">
        <vs-input v-model="lang.aceEditor" placeholder="Ace editor id" />
      </vs-col>
      <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="3">
        <vs-input v-model="lang.judge0id" placeholder="judge0 id" />
      </vs-col>
      <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="3">
        <vs-button color="primary" type="filled" @click="addLang" :disabled="isPending" icon="add">Add</vs-button>
      </vs-col>
    </vs-row>
  </div>
</template>
<script setup>
import {reactive} from "vue";

const lang = reactive({
  name: '',
  aceEditor: '',
  judge0id: null,
})

import { languageQueries } from "@/lib/tanstack/language.queries";
const { addLanguageMutation } = languageQueries()

const { mutateAsync: addLanguage, isSuccess, isPending, isError, error } = addLanguageMutation()

const addLang = () => {
  addLanguage(lang)
}

</script>
<style>
.language {
  & .vs-input--placeholder {
    color: var(--text-disable);
  }
  & .vs-input--input{
    background-color: var(--background);
    color: var(--text);
  }
}
</style>