<template>
  <div class="problem-editor">
    <div class="problem-editor__input_title">
      <input class="title" type="text" placeholder="Enter problem title" v-model="localTitle" @input="updateTitle" />
      <input type="text" placeholder="Enter problem slug" v-model="localSlug" @input="updateSlug" />
    </div>
    <div class="problem-editor__edit-icons">
      <button v-for="(icon, index) in icons" :key="index" @click="applyMarkdown(icon.action)">
        <vs-tooltip :text="icon.tooltip">
          <i class="material-icons">{{ icon.name }}</i>
        </vs-tooltip>
      </button>
    </div>
    <div class="problem-editor__md-editor">
      <textarea
          v-model="localDescription"
          class="md-editor__textarea"
          placeholder="Enter problem markdown here"
          @input="updateDescription"
      ></textarea>
    </div>
    <div class="problem-editor__input_title">
      <input type="url" placeholder="Enter testcases link" v-model="localTestcasesLink" @input="updateTestcasesLink" />
      <select v-model="localDifficulty" @change="updateDifficulty">
        <option value="" disabled>Difficulty</option>
        <option value="EASY">Easy</option>
        <option value="MODERATE">Moderate</option>
        <option value="HARD">Hard</option>
      </select>
    </div>
  </div>
  <div class="problem-editor__submit">
    <vs-row vs-type="flex" vs-justify="flex-end" vs-w="12">
      <vs-col vs-type="flex" vs-justify="flex-end" vs-align="center" vs-w="2">
        <vs-button color="primary" type="border" @click="showOnConsole">Submit</vs-button>
      </vs-col>
    </vs-row>
  </div>
</template>

<script setup>
import { ref, watch, defineEmits, defineProps } from "vue";

const props = defineProps({
  title: String,
  slug: String,
  description: String,
  testcasesLink: String,
  difficulty: String,
});

const emit = defineEmits(["update:title", "update:slug", "update:description", "update:testcasesLink", "update:difficulty"]);

const localTitle = ref(props.title);
const localSlug = ref(props.slug);
const localDescription = ref(props.description);
const localTestcasesLink = ref(props.testcasesLink);
const localDifficulty = ref(props.difficulty);

const icons = [
  { name: "format_bold", action: "bold", tooltip: "Bold" },
  { name: "format_italic", action: "italic", tooltip: "Italic" },
  { name: "format_underlined", action: "underline", tooltip: "Underline" },
  { name: "format_list_bulleted", action: "bulletedList", tooltip: "Bulleted List" },
  { name: "format_list_numbered", action: "numberedList", tooltip: "Numbered List" },
  { name: "code", action: "codeBlock", tooltip: "Code Block" },
  { name: "format_quote", action: "quote", tooltip: "Quote" },
  { name: "link", action: "link", tooltip: "Link" },
  { name: "image", action: "image", tooltip: "Image" },
];

const updateTitle = () => {
  emit("update:title", localTitle.value);
};

const updateSlug = () => {
  emit("update:slug", localSlug.value);
};

const updateDescription = () => {
  emit("update:description", localDescription.value);
};

const updateTestcasesLink = () => {
  emit("update:testcasesLink", localTestcasesLink.value);
};

const updateDifficulty = () => {
  emit("update:difficulty", localDifficulty.value);
};

const applyMarkdown = (action) => {
  switch (action) {
    case "bold":
      localDescription.value = `**${localDescription.value}**`;
      break;
    case "italic":
      localDescription.value = `*${localDescription.value}*`;
      break;
    case "underline":
      localDescription.value = `<u>${localDescription.value}</u>`;
      break;
    case "bulletedList":
      localDescription.value = `- ${localDescription.value}`;
      break;
    case "numberedList":
      localDescription.value = `1. ${localDescription.value}`;
      break;
    case "codeBlock":
      localDescription.value = `\`\`\`\n${localDescription.value}\n\`\`\``;
      break;
    case "quote":
      localDescription.value = `> ${localDescription.value}`;
      break;
    case "link":
      localDescription.value = `[${localDescription.value}](url)`;
      break;
    case "image":
      localDescription.value = `![alt text](image_url)`;
      break;
    default:
      break;
  }
  updateDescription();
};

const showOnConsole = () => {
  console.log({
    title: localTitle.value,
    slug: localSlug.value,
    description: localDescription.value,
    testcasesLink: localTestcasesLink.value,
    difficulty: localDifficulty.value,
  });
};

watch(props, (newProps) => {
  localTitle.value = newProps.title;
  localSlug.value = newProps.slug;
  localDescription.value = newProps.description;
  localTestcasesLink.value = newProps.testcasesLink;
  localDifficulty.value = newProps.difficulty;
});
</script>

<style scoped>
.problem-editor {
  margin-left: 0.5rem;
  display: flex;
  flex-direction: column;
  background-color: var(--background);
  font-family: Gilroy-Regular, sans-serif;
  border-radius: 0.5rem;
  height: 95%;
& .problem-editor__input_title {
    padding: 0.5rem;
& input {
    width: 100%;
    font-family: inherit;
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
    font-family: inherit;
    resize: none;
  }
}
& .problem-editor__edit-icons {
    display: flex;
    overflow-x: auto;
    padding: 0.3rem;
    height: 15%;
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
  margin: 0.2rem 0;
}
</style>

<style>
/* vs button active */
.problem-editor__submit {
& .vs-button-border.isActive .vs-button--icon,
  .vs-button-border.isActive .vs-button--text,
  .vs-button-flat.isActive .vs-button--icon,
  .vs-button-flat.isActive .vs-button--text {
    color: var(--dark) !important;
  }
}
</style>