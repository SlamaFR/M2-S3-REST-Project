<script setup lang="ts">
import { RouterView } from "vue-router";
import NavBar from "@/components/scaffold/NavBar.vue";
import { computed, ref } from "vue";
import { useWindowSize } from "@vueuse/core";

const { height: appHeight } = useWindowSize();

const header = ref<HTMLElement | null>(null);
const headerHeight = computed(() => header.value?.clientHeight ?? 0);
</script>

<template>
  <header class="absolute w-screen" ref="header">
    <nav-bar />
  </header>

  <main
    :style="`--header-height: ${headerHeight}px; --app-height: ${appHeight}px;`"
  >
    <router-view />
  </main>
</template>

<style scoped>
* {
  --app-height: 0px;
  --header-height: 0px;
}

main {
  height: var(--app-height);
  padding-top: var(--header-height);
}
</style>