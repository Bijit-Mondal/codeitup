<template>
    <div class="otp-box">
        <OTPComponent/>
    </div>
</template>
<style scoped>
.otp-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 80vh;
}
</style>
<script setup>
import OTPComponent from '@/component/auth/OTPComponent.vue';
</script>