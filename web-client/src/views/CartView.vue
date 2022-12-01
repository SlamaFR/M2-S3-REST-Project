<script lang="ts" setup>
import { useRouter } from "vue-router";
import CartDisplay from "@/components/cart/CartDisplay.vue";

const router = useRouter();
</script>

<template>
  <div class="hero w-full h-full">
    <div class="hero-content">
      <cart-display />
    </div>
  </div>
  <div class="absolute bottom-10 right-10">
    <button class="btn btn-secondary" @click="router.back()">Back</button>
  </div>
</template>
