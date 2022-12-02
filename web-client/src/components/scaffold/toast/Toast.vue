<script lang="ts" setup>
import type { Toast } from "@/stores/toasts";
import { onMounted, reactive, ref } from "vue";
import { useToast } from "@/stores/toasts";
import { useTimeoutFn } from "@vueuse/core";

const props = defineProps<{
  toast: Toast;
}>();
const {
  toast: { id, type, message },
} = reactive(props);

const it = ref<HTMLElement>();

const { clearToast } = useToast();
const { start: startDismiss } = useTimeoutFn(() => {
  clearToast(id);
}, 2000);

const { start: startFadeOut } = useTimeoutFn(() => {
  it.value?.classList.add("toast-fade-out");
}, 1500);

onMounted(() => {
  startDismiss();
  startFadeOut();
});
</script>

<template>
  <div ref="it" class="alert" :class="`${type}`">
    <div>
      <span>{{ message }}</span>
    </div>
  </div>
</template>

<style scoped>
.alert {
  opacity: 1;
  transition: opacity 500ms ease-in-out;
}

.toast-fade-out {
  opacity: 0;
}

.success {
  background-color: #d4edda;
  color: #155724;
}

.error {
  background-color: #f8d7da;
  color: #721c24;
}

.info {
  background-color: #d1ecf1;
  color: #0c5460;
}
</style>
