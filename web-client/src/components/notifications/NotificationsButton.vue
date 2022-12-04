<script lang="ts" setup>
import { BellIcon } from "vue-tabler-icons";

defineProps<{
  count: number;
}>();
</script>

<template>
  <div class="tooltip tooltip-bottom" data-tip="Notifications">
    <label tabindex="0" class="btn btn-ghost btn-circle">
      <div class="indicator">
        <bell-icon />
        <span class="badge badge-sm indicator-item">{{ count }}</span>
      </div>
    </label>
  </div>
</template>