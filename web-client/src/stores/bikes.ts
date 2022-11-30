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
  orderer: string; // orderer username
  comment: Comment;
  returnState: ReturnState;
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
    bikes.value = [
      {
        id: "1fae73a1-2521-4383-88fa-e626b16bbd74",
        owner: "user1",
        history: [
          {
            date: new Date(),
            orderer: "user2",
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
        availability: "available",
        value: 100,
      },
      {
        id: "83981c19-fb86-4f05-9db4-aff610b266a9",
        owner: "user3",
        history: [
          {
            date: new Date(),
            orderer: "user1",
            comment: {
              content: "me not likey vroom vroom",
              stars: 5,
            },
            returnState: {
              details: "tehee",
              state: "broken",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
          {
            date: date,
            orderer: "user4",
            comment: {
              content: "good",
              stars: 5,
            },
            returnState: {
              details: "good",
              state: "as new",
            },
          },
        ],
        availability: "rented",
        value: 40,
      },
      {
        id: "77743145-cccc-4e30-93ed-1212dd7df3c9",
        owner: "user3",
        history: [],
        availability: "available",
        value: 150,
      },
    ];
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
