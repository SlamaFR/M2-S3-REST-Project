import { defineStore } from "pinia";
import { computed, ref } from "vue";
import { useCookies } from "@vueuse/integrations/useCookies";

export interface User {
  id: number;
  username: string;
  token: string;
}

export const useUserStore = defineStore("user", () => {
  const cookies = useCookies(["user"]);
  const user = computed<User>(() => cookies.get("user")!!);
  const isConnected = computed(() => cookies.get("user") !== undefined);

  async function login(username: string, password: string): Promise<boolean> {
    await logout();
    // TODO actual login
    cookies.set(
      "user",
      { id: 1, username, token: "token" },
      {
        sameSite: "strict",
        maxAge: 60 * 60 * 24 * 7,
        secure: true,
      }
    );
    return true;
  }

  async function register(
    username: string,
    password: string
  ): Promise<boolean> {
    // TODO actual register
    return await login(username, password);
  }

  async function logout() {
    // TODO actual logout
    cookies.remove("user", { sameSite: "strict", secure: true });
  }

  return { user, isConnected, login, register, logout };
});
