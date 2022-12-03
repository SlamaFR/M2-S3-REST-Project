import { defineStore, storeToRefs } from "pinia";
import { axiosInstance } from "@/utils/axiosConfig";
import { useUserStore } from "@/stores/user";
import type { Moment } from "moment";
import moment from "moment";

export interface Order {
  date: Moment;
  ordererName: string; // orderer username
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
  bikeId: string; // uuid
  ownerName: string; // username
  history: Order[];
  availability: "available" | "rented";
  value: number; // in USD
}

function convert(bike: Bike): Bike {
  bike.history = bike.history.map((order) => {
    order.date = moment(order.date);
    return order;
  });
  return bike;
}

function sortHistory(bikes: Bike[]) {
  bikes.forEach((bike: Bike) =>
    bike.history.sort(
      (a, b) => moment(b.date).valueOf() - moment(a.date).valueOf()
    )
  );
}

export const useBikesStore = defineStore("bikes", () => {
  const { user } = storeToRefs(useUserStore());

  async function getAllBikes(): Promise<Bike[]> {
    const response = await axiosInstance.get("/bike/all");
    if (response.status !== 200) throw new Error("Could not get bikes");
    sortHistory(response.data);
    return response.data;
  }
  async function getBike(id: string): Promise<Bike> {
    const response = await axiosInstance.get(`/bike/${id}`);
    if (response.status !== 200) throw new Error("Could not get bike");
    response.data = convert(response.data);
    sortHistory([response.data]);
    return response.data;
  }
  async function getRentedBikes(): Promise<Bike[]> {
    const response = await axiosInstance.get("/bike/rented", {
      headers: {
        "Session-Token": user.value.token,
      },
    });
    if (response.status !== 200) throw new Error("Could not get bikes");
    sortHistory(response.data);
    return response.data;
  }
  async function rentedContains(id: string): Promise<boolean> {
    const bikes = await getRentedBikes();
    return bikes.map((bike) => bike.bikeId).includes(id);
  }
  async function orderBikes(bikesIds: string[]): Promise<void> {
    const response = await axiosInstance.post(
      "/bike/order",
      { bikesIds },
      {
        headers: {
          "Session-Token": user.value.token,
        },
      }
    );
    if (response.status !== 200) throw new Error("Could not order bikes");
  }
  async function returnBike(bike: Bike, returnState: string, comment: string) {
    const response = await axiosInstance.post(
      `/bike/return/${bike.bikeId}`,
      { returnState, comment },
      {
        headers: {
          "Session-Token": user.value.token,
        },
      }
    );
    if (response.status !== 200) throw new Error("Could not return bike");
  }

  return {
    getAllBikes,
    getBike,
    getRentedBikes,
    rentedContains,
    orderBikes,
    returnBike,
  };
});
