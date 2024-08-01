<template>
  <div class="btn-group">
    <button
        v-for="(option, index) in props.options"
        :key="index"
        @click="toggleComponent(option.value)"
        :class="{ 'is-active': props.currentComponent === option.value }">
      {{ option.label }}
    </button>
  </div>
</template>

<script setup>
/* eslint-disable */
const props = defineProps({
  options: {
    type: Array,
    required: true
  },
  currentComponent: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['update:currentComponent']);

function toggleComponent(com) {
  emit('update:currentComponent', com);
}

</script>

<style scoped>
.btn-group {
  display: inline-flex;
  justify-content: center;
  margin-bottom: 0.6rem;
  background-color: var(--dark);
  border-radius: 5px;
  & button {
    cursor: pointer;
    background: transparent;
    color: var(--text-disable);
    margin: 0.3rem;
    font-weight: bolder;
    font-size: 1rem;
    padding: 0.3rem;
    border: 0;
    border-radius: 5px;
    transition: all 0.1s ease-in-out;
  &:hover {
   color: var(--text);
  }
  &.is-active {
   color: var(--text);
   background-color: var(--background);
   border: 1px solid var(--primary);
  }
 }
}
</style>
