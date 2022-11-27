<script lang="ts" setup>
import { ShoppingCartIcon } from "vue-tabler-icons";

defineProps<{
  size: string;
}>();
</script>

<template>
  <div class="tooltip tooltip-bottom" data-tip="Cart">
    <label tabindex="0" class="btn btn-ghost btn-circle">
      <div class="indicator">
        <shopping-cart-icon />
        <span class="badge badge-sm indicator-item">{{ size }}</span>
      </div>
    </label>
  </div>
</template>
