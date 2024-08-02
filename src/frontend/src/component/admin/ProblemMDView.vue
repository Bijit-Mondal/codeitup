<template>
  <div class="problem-definition">
    <h2 class="problem-definition__heading">{{ title }}</h2>
    <vs-row class="problem-definition__chip">
      <vs-chip transparent :color="difficultyColor">
        {{ difficulty }}
      </vs-chip>
    </vs-row>
    <div class="problem-definition__content" v-html="renderedDescription"></div>
  </div>
</template>

<script setup>
import {computed, defineProps, nextTick, watch} from "vue";
import {marked} from "marked";
import hljs from 'highlight.js';

const loadHighlightJSTheme = async () => {
  const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)").matches;
  if (prefersDarkScheme) {
    await import('highlight.js/styles/base16/tender.min.css');
  } else {
    await import('highlight.js/styles/base16/github.min.css');
  }
};
loadHighlightJSTheme();

const props = defineProps({
  title: String,
  slug: String,
  description: String,
  testcasesLink: String,
  difficulty: String,
});

const renderedDescription = computed(() => marked(props.description));

watch(() => props.description, () => {
  nextTick(() => {
    document.querySelectorAll("pre code").forEach((block) => {
      hljs.highlightBlock(block);
    });
  })
});

const difficultyColor = computed(() => {
  switch (props.difficulty) {
    case "EASY":
      return "success";
    case "MODERATE":
      return "primary";
    case "HARD":
      return "danger";
    default:
      return "default";
  }
});
</script>
<style>
/*for code box*/
.problem-definition {
  padding: 0.3rem;
  max-height: 80vh;
  overflow-y: auto;
  line-height: 1.2rem;
}
.problem-definition > .problem-definition__chip {
  margin : 0.4rem 0;
}
.problem-definition > .problem-definition__heading {
  font-size: 1.6rem;
}
code {
  border-radius: 0.5rem;
  font-family: "Fira-Code",monospace;
}
.problem-definition__content > ul, ol {
  margin-left: 1.4rem;
}
.problem-definition__content > blockquote {
  margin-left: 1.4rem;
  margin-bottom: 0.5rem;
  border-left: 0.25em solid var(--primary);
  padding-left: 0.5em;
  background-color: var(--background);
  border-radius: 0.1rem;
}
</style>