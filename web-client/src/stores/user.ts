import { defineStore } from "pinia";
import { computed } from "vue";
import { useCookies } from "@vueuse/integrations/useCookies";
import type { Bike } from "@/stores/bikes";
import { axiosInstance } from "@/utils/axiosConfig";

export interface User {
  username: string;
  token: string;
  currency: string;
}

function distinct(bikes: Bike[]): Bike[] {
  const distinct: Bike[] = [];
  for (const bike of bikes) {
    if (!distinct.map((b) => b.bikeId).includes(bike.bikeId)) {
      distinct.push(bike);
    }
  }
  return distinct;
}

export const useUserStore = defineStore("user", () => {
  const cookies = useCookies(["user"]);
  const user = computed<User>(() => cookies.get("user")!!);
  const isConnected = computed(() => cookies.get("user") !== undefined);

  function setUser(user: User) {
    cookies.set("user", user, {
      sameSite: "strict",
      maxAge: 60 * 60 * 24 * 365,
      secure: true,
    });
  }

  async function login(username: string, password: string): Promise<boolean> {
    await logout();
    const response = await axiosInstance.post("/auth/login", {
      username,
      password,
    });
    if (response.status !== 200) throw new Error("Login failed");
    setUser({
      username,
      token: response.data.sessionToken,
    });
    return true;
  }

  async function register(
    username: string,
    password: string
  ): Promise<boolean> {
    const response = await axiosInstance.put("/auth/register", {
      username,
      password,
    });
    if (response.status !== 200) throw new Error("Register failed");
    return await login(username, password);
  }

  async function logout() {
    if (!isConnected.value) return;
    const response = await axiosInstance.post(
      "/auth/logout",
      {},
      {
        headers: {
          "Session-Token": user.value.token,
        },
      }
    );
    if (response.status !== 200) throw new Error("Logout failed");
    cookies.remove("user", { sameSite: "strict", secure: true });
  }

  return {
    user,
    isConnected,
    login,
    register,
    logout,
  };
});
