<template>
  <div class="con-type-example">

    <vs-navbar type="flat" v-model="indexActive" class="nabarx">

      <template #title>
        <vs-navbar-title>
          <span class="clip_codedhyan clip">CodeDhyan</span>
          <span class="clip_codeitup_x_codedhyan clip"> X </span>
          <span class="clip_codeitup clip">CodeitUp</span>
        </vs-navbar-title>
      </template>

      <vs-navbar-item index="0">
        <a href="#">Home</a>
      </vs-navbar-item>
      <vs-navbar-item index="1">
        <a href="#">News</a>
      </vs-navbar-item>
      <vs-navbar-item index="2">
        <a href="#">Update</a>
      </vs-navbar-item>
    </vs-navbar>
  </div>
</template>
<style>
@import "../../styles/clip.css";
.nabarx{
 background-color: var(--dark) !important;
}
.vs-navbar--item {
  color: var(--text-disable) !important;
}
.btn-responsive-line {
  background: var(--text) !important;
}
.vs-navbar-flat {
  border-radius: 0 0 10px 10px;
}
.is-active-item {
  font-family: "Gilroy-Bold",sans-serif;
  color: var(--text) !important;
}
</style>
<script setup>
import {ref} from "vue";
const indexActive = ref(0);
</script>