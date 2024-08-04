<template>
<!--  <SkeletonTheme background-color="#303030">-->
    <h1>
      <!--Skeleton will inherit <p> height -->
      <Skeleton :rows="10" />
    </h1>
<!--  </SkeletonTheme>-->
</template>
<script setup>
import { Skeleton } from '@brayamvalero/vue3-skeleton'
import '@brayamvalero/vue3-skeleton/dist/style.css'
</script>