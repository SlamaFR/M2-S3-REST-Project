<script lang="ts" setup>
import { storeToRefs } from "pinia";
import { useToast } from "@/stores/toasts";
import Toast from "@/components/scaffold/toast/Toast.vue";

const { toasts } = storeToRefs(useToast());
</script>

<template>
  <div class="toast toast-start toast-bottom" v-if="toasts.length > 0">
    <toast v-for="toast in toasts" :key="toast.id" :toast="toast" />
  </div>
</template>
