<template>
  <div class="problem-definition">
    <h2 class="problem-definition__heading">Sum of Array</h2>
    <vs-row class="problem-definition__chip">
      <vs-chip transparent color="danger">
        Hard
      </vs-chip>
    </vs-row>
    <div class="problem-definition__content">
      <span v-html="problem">
      </span>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";

const problem = ref('Given an array of integers array, return the sum of all elements in the array.<br>\n' +
    '        You should implement logic inside the function that takes the array as input and returns a single integer representing the sum.<br>\n' +
    '        You are given an array arr. Your task is to find the longest length of a good sequence. A good sequence {x1, x2, .. xn} is an alternating sequence if its elements satisfy one of the following relations :<br>\n' +
    '\n' +
    '        1.  x1 < x2 > x3 < x4 > x5. . . . . and so on<br>\n' +
    '        2.  x1 >x2 < x3 > x4 < x5. . . . . and so on<br>');
problem.value += problem.value;
</script>
<style scoped>
.problem-definition {
  padding: 0.3rem;
  max-height: 80vh;
  overflow-y: auto;
}
.problem-definition > .problem-definition__chip {
  margin : 0.4rem 0;
}
.problem-definition > .problem-definition__heading {
  font-size: 18px;
}
.problem-definition > .problem-definition__content {
  font-size: 15px;
  /*Disable user select  */
  user-select: none;
  -webkit-user-select: none;
}
.problem-definition__content {
  a {
    color: var(--primary);
  }
}
</style>