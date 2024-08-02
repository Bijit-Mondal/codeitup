<template>
  <div class="problem-editor">
    <div class="problem-editor__input_title">
      <input class="title" type="text" placeholder="Enter problem title" v-model="title">
      <input type="text" placeholder="Enter problem slug" v-model="title">
    </div>
    <div class="problem-editor__edit-icons">
      <button>
        <vs-tooltip text="Tooltip Default">
          <i class="material-icons">
            format_bold
          </i>
        </vs-tooltip>
      </button>
      <button>
        <i class="material-icons">
          format_italic
        </i>
      </button>
      <button>
        <i class="material-icons">
          format_underlined
        </i>
      </button>
      <button>
        <i class="material-icons">
          format_list_bulleted
        </i>
      </button>
      <button>
        <i class="material-icons">
          format_list_numbered
        </i>
      </button>
      <button>
        <i class="material-icons">
          code
        </i>
      </button>
      <button>
        <i class="material-icons">
          format_quote
        </i>
      </button>
      <button>
        <i class="material-icons">
          link
        </i>
      </button>
      <button>
        <i class="material-icons">
          image
        </i>
      </button>
    </div>
    <div class="problem-editor__md-editor">
      <textarea v-model="description" class="md-editor__textarea" placeholder="Enter problem markdown here"></textarea>
    </div>
    <div class="problem-editor__input_title">
      <input type="url" placeholder="Enter testcases link" v-model="title">
      <select>
        <option value="" disabled selected>Difficulty</option>
        <option value="EASY">Easy</option>
        <option value="MODERATE">Moderate</option>
        <option value="HARD">Hard</option>
      </select>
    </div>
  </div>
  <div class="problem-editor__submit">
    <vs-row vs-type="flex" vs-justify="flex-end" vs-w="12">
      <vs-col vs-type="flex" vs-justify="center" vs-align="center" vs-w="2">
        <vs-button color="primary" type="border" @click="showOnConsole">Submit</vs-button>
      </vs-col>
    </vs-row>
  </div>
</template>
<script setup>
import { ref } from "vue";
const description = ref("'# Hello, CodeDhyan!\\n' +\n" +
    "          '```javascript\\n' +\n" +
    "          'console.log(\"Hello, World!\");\\n' +\n" +
    "          '```'")
const title = ref()
</script>
<style scoped>
.problem-editor {
  margin-left: 0.5rem;
  display: flex;
  flex-direction: column;
  background-color: var(--background);
  border-radius: 0.5rem;
  height: 95%;
  & .problem-editor__input_title{
    padding: 0.5rem;
    & input {
      width: 100%;
      font-family: Gilroy-Regular, sans-serif;
      padding: 0.3rem;
      border: 0;
      background-color: inherit;
      color: var(--text);
      &.title {
         font-size: 1.5rem;
       }
    }
    & select {
      width: 100%;
      font-family: Gilroy-Regular, sans-serif;
      padding: 0.3rem;
      border: 0;
      background-color: inherit;
      color: var(--text);
    }
  }
  & .problem-editor__md-editor {
    padding: 0.3rem;
    height: 75%;
    & .md-editor__textarea {
      width: 100%;
      height: 100%;
      border: 0;
      padding: 0.3rem;
      background-color: inherit;
      color: var(--text);
      font-size: 1rem;
      resize: none;
    }
  }
  & .problem-editor__edit-icons {
    display: flex;
    overflow-x: auto;
    padding: 0.3rem;
    max-height: 15%;
    & button {
      margin: 0.2rem;
      padding: 0.2rem 0.5rem;
      border: 0;
      border-radius: 0.2rem;
      background-color: transparent;
      color: var(--text-disable);
      border: none;
      cursor: pointer;
      &:hover {
        border-bottom: 1px solid var(--text);
        background-color: rgb(var(--vs-dark));
      }
    }
  }
}
.problem-editor__submit {
  margin-top: 0.5rem;
}
</style>

<style>
/* vs button active */
.problem-editor__submit{
  & .vs-button-border.isActive .vs-button--icon, .vs-button-border.isActive .vs-button--text, .vs-button-flat.isActive .vs-button--icon, .vs-button-flat.isActive .vs-button--text{
      color: var(--dark) !important;
    }
}
</style>