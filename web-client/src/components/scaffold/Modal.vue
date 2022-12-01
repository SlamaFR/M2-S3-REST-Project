<script lang="ts" setup>
import { XIcon } from "vue-tabler-icons";

defineProps<{
  title: string;
  confirmButton: {
    text: string;
    classes: string;
  };
  cancelButton: {
    text: string;
    classes: string;
  };
}>();
</script>

<template>
  <vue-final-modal
    v-slot="{ params, close }"
    v-bind="$attrs"
    classes="flex justify-center items-center"
    content-class="relative flex flex-col max-h-full mx-4 p-4 modal-box"
  >
    <span class="mr-8 text-2xl font-bold">
      {{ title }}
    </span>
    <div class="flex-grow overflow-y-auto">
      <slot :params="params"></slot>
    </div>
    <button class="absolute top-0 right-0 mt-2 mr-2" @click="close">
      <x-icon />
    </button>
  </vue-final-modal>
</template>
