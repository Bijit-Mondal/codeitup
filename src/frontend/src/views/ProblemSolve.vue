<template>
  <div class="split">
    <div id="split-0">
      <h1>Two Sum</h1>
      <p>Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum
        Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ips
        Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum </p>
    </div>
    <div id="split-1">
      <CodeEditor/>
    </div>
  </div>
</template>
<script setup>
// import { ref } from "vue";
import CodeEditor from "../component/CodeEditor.vue";
import { onMounted } from "vue"
import Split from 'split.js'
onMounted(() => {
  Split(['#split-0', '#split-1'],
      {
        minSize: [40,300],
        snapOffset: 20,
        gutterSize: 5,

      })
})

</script>
<style>
.split {
  margin-top: 30px;
  display: flex;
  flex-direction: row;
}
.gutter {
  background-color: #eee;
  background-repeat: no-repeat;
  background-position: 50%;
}

.gutter.gutter-horizontal {
  background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAYAAADkftS9AAAAIklEQVQoU2M4c+bMfxAGAgYYmwGrIIiDjrELjpo5aiZeMwF+yNnOs5KSvgAAAABJRU5ErkJggg==');
  cursor: col-resize;
}
</style>