<script lang="ts" setup>
import { useCartStore } from "@/stores/cart";
import { storeToRefs } from "pinia";

const { count, subtotal } = storeToRefs(useCartStore());
</script>

<template>
  <div
    tabindex="0"
    class="mt-3 card card-compact dropdown-content w-52 bg-base-100 shadow"
  >
    <div class="card-body">
      <span class="font-bold text-lg">{{ count }} Items</span>
      <span class="text-info">Subtotal: ${{ subtotal }}</span>
      <div class="card-actions">
        <router-link class="btn btn-primary btn-block" to="/cart">
          View cart
        </router-link>
      </div>
    </div>
  </div>
</template>
