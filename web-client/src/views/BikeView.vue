<script lang="ts" setup>
import { useRouter } from "vue-router";
import { useBikesStore } from "@/stores/bikes";
import { computedAsync } from "@vueuse/core";
import BikeDisplay from "@/components/bikelist/BikeDisplay.vue";

const router = useRouter();
const { getBike } = useBikesStore();
const id = router.currentRoute.value.params.id.toString();
const bike = computedAsync(async () => await getBike(id));
</script>

<template>
  <div class="hero w-full h-full">
    <div class="hero-content">
      <bike-display v-if="bike" :bike="bike" />
    </div>
  </div>
  <div class="absolute bottom-10 right-10">
    <button class="btn btn-secondary" @click="router.push('/bikes')">
      Back to list
    </button>
  </div>
</template>
