<script lang="ts" setup>
import LogHero from "@/components/main/LogHero.vue";
import {useUserStore} from "@/stores/user";
import {storeToRefs} from "pinia";
import MainHero from "@/components/main/MainHero.vue";

const { isConnected } = storeToRefs(useUserStore());
</script>

<template>
  <main-hero v-if="isConnected" />
  <log-hero v-else />
</template>
