import { defineStore } from "pinia";
import { ref } from "vue";

export interface Toast {
  id: string;
  type: "success" | "error" | "info";
  message: string;
}

export const useToast = defineStore("toast", () => {
  const toasts = ref<Toast[]>([]);

  function pushToast(type: "success" | "error" | "info", message: string) {
    toasts.value.push({ id: crypto.randomUUID(), type, message });
  }

  function clearToast(id: string) {
    toasts.value = toasts.value.filter((toast) => toast.id !== id);
  }

  return {
    toasts,
    pushToast,
    clearToast,
  };
});
