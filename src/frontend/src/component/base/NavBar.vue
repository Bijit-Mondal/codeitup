<template>
  <div class="con-type-example">

    <vs-navbar type="flat" v-model="indexActive" class="nabarx">

      <template #title>
        <router-link :to="{ name:'home' }">
          <vs-navbar-title>
            <span class="clip_codedhyan clip">CodeDhyan</span>
            <span class="clip_codeitup_x_codedhyan clip"> X </span>
            <span class="clip_codeitup clip">CodeitUp</span>
          </vs-navbar-title>
        </router-link>
      </template>
      <vs-navbar-item v-for="(item, index) in navbarItems" :key="index" :index="index">
        <router-link :to="{ name: item.routeName }">{{ item.title }}</router-link>
      </vs-navbar-item>
      <vs-navbar-item index="3">
        <router-link v-if="!authStore.isLoggedIn" :to="{ name:'auth' }">Login</router-link>
        <router-link v-else :to="{ name:'auth' }">
          <vs-avatar size="small" />
        </router-link>
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
import {ref, watchEffect} from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/lib/store/auth.store";

const router = useRouter();
const authStore = useAuthStore();

const indexActive = ref(0);
const navbarItems = ref([
  { title: 'Home', routeName: 'home' },
  { title: 'Problems', routeName: 'problem' },
  { title: 'Contest', routeName: 'auth' },
]);

watchEffect(() => {
  const newIndex = navbarItems.value.findIndex((item) => item.routeName === router.currentRoute.value.name);
  if(newIndex !== -1){
    indexActive.value = newIndex;
  }
});

</script>