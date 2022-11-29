import { defineStore } from "pinia";
import { computed, ref } from "vue";

export interface Comment {
  content: string;
  stars: 0 | 1 | 2 | 3 | 4 | 5;
}

export interface ReturnState {
  details: string;
  state: "as new" | "good" | "slightly damaged" | "bad" | "broken" | "lost";
}

export interface Order {
  date: Date;
  ordered: string; // orderer username
  comment: Comment;
  returnState: ReturnState;
}

export interface Bike {
  id: string; // uuid
  owner: string; // username
  history: Order[];
  state: "available" | "rented";
}
export const useBikesStore = defineStore("bikes", () => {
  const bikes = ref<Bike[]>([]);

  // TODO actual fetch
  async function fetch() {
    bikes.value = [
      {
        id: "1fae73a1-2521-4383-88fa-e626b16bbd74",
        owner: "user1",
        history: [
          {
            date: new Date(),
            ordered: "user2",
            comment: {
              content: "god awfully ugly, but fast af",
              stars: 4,
            },
            returnState: {
              details: "some scratches",
              state: "slightly damaged",
            },
          },
        ],
        state: "available",
      },
      {
        id: "83981c19-fb86-4f05-9db4-aff610b266a9",
        owner: "user3",
        history: [
          {
            date: new Date(),
            ordered: "user1",
            comment: {
              content: "me not likey vroom vroom",
              stars: 5,
            },
            returnState: {
              details: "tehee",
              state: "broken",
            },
          },
        ],
        state: "rented",
      },
    ];
  }

  const all = computed(async () => {
    await fetch();
    return bikes.value;
  });
  const available = computed(async () => {
    await fetch();
    return bikes.value.filter((bike) => bike.state === "available");
  });
  async function get(id: string) {
    await fetch();
    return bikes.value.find((bike) => bike.id === id);
  }

  return {
    all,
    available,
    get,
  };
});
