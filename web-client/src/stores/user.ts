import { defineStore } from "pinia";
import { computed, reactive, ref } from "vue";
import { useRouter } from "vue-router";

export interface User {
  id: number;
  username: string;
  token: string;
}

export const useUserStore = defineStore("user", () => {
  const inner = ref<User | undefined>();
  const user = computed<User>(() => inner.value!!);
  const isConnected = computed(() => inner.value !== undefined);

  function login(username: string, password: string) {
    // TODO actual login
    inner.value = { id: 1, username, token: "token" };
  }

  function logout() {
    // TODO actual logout
    inner.value = undefined;
  }

  return { user, isConnected, login, logout };
});
