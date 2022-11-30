<script lang="ts" setup>
import type { Component } from "vue";

defineProps<{
  item: any;
  icon: Component;
}>();

defineEmits<{
  (e: "click", event: MouseEvent): void;
}>();
</script>

<template>
  <button
    class="btn btn-circle"
    @click.prevent.stop="$emit('click', $event, item)"
  >
    <component v-bind:is="icon" />
  </button>
</template>
