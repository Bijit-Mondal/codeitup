<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HomeView',
});
</script>

<style scoped>
.container {
  display: flex;
}
.left-half {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<template>
  <vs-row class="container">
    <vs-col class="left-half">
      <object class="no-pointers" data="https://bijit.xyz/cockpit-master/storage/uploads/2024/11/28/FeaturePreviewRealTimeNew_uid_674888e721374.svg">Banner image</object>
    </vs-col>
    <vs-col>
      Home Component
    </vs-col>
  </vs-row>
</template>