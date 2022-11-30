import { defineStore } from "pinia";
import { computed } from "vue";
import { useCookies } from "@vueuse/integrations/useCookies";
import type { Bike } from "@/stores/bikes";

export interface User {
  id: string;
  username: string;
  token: string;
  rentedBikes: Bike[];
}

function distinct(bikes: Bike[]): Bike[] {
  const distinct: Bike[] = [];
  for (const bike of bikes) {
    if (!distinct.map((b) => b.id).includes(bike.id)) {
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
      maxAge: 60 * 60 * 24 * 7,
      secure: true,
    });
  }

  async function login(username: string, password: string): Promise<boolean> {
    await logout();
    // TODO actual login
    setUser({
      id: "a381e5b8-06c6-4166-9fe0-01339b36ef63",
      username,
      token: "token",
      rentedBikes: [],
    });
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

  const rentedBikes = computed(() => distinct(user.value.rentedBikes));

  function addBike(bike: Bike) {
    // TODO actual add bike
    user.value.rentedBikes.push(bike);
    const rentedBikes = [...user.value.rentedBikes, bike];
    setUser({ ...user.value, rentedBikes: distinct(rentedBikes) });
  }

  function removeBike(bike: Bike) {
    // TODO actual remove bike
    const rentedBikes = user.value.rentedBikes.filter((b) => b.id !== bike.id);
    setUser({ ...user.value, rentedBikes: distinct(rentedBikes) });
  }

  return {
    user,
    isConnected,
    login,
    register,
    logout,
    rentedBikes,
    addBike,
    removeBike,
  };
});
