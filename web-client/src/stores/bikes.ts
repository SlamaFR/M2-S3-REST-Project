import { defineStore } from "pinia";
import { computed, ref } from "vue";

export interface Order {
  date: Date;
  orderer: string; // orderer username
  comment: string;
  returnState:
    | "as new"
    | "good"
    | "slightly damaged"
    | "bad"
    | "broken"
    | "lost";
}

export interface Bike {
  id: string; // uuid
  owner: string; // username
  history: Order[];
  availability: "available" | "rented";
  value: number; // in USD
}

export const useBikesStore = defineStore("bikes", () => {
  const bikes = ref<Bike[]>([]);

  // TODO actual fetch
  async function fetch() {
    const date = new Date();
    date.setDate(date.getDate() - 1);
    bikes.value = [];
    bikes.value.forEach((bike) =>
      bike.history.sort((a, b) => b.date.getTime() - a.date.getTime())
    );
  }

  const all = computed(async () => {
    await fetch();
    return bikes.value;
  });
  const available = computed(async () => {
    await fetch();
    return bikes.value.filter((bike) => bike.availability === "available");
  });
  async function getBike(id: string) {
    await fetch();
    return bikes.value.find((bike) => bike.id === id);
  }

  return {
    all,
    available,
    getBike,
  };
});
