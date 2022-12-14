<script lang="ts" setup>
import type { Bike } from "@/stores/bikes";
import { useBikesStore } from "@/stores/bikes";
import { computed, reactive } from "vue";
import DataTable from "@/components/scaffold/table/DataTable.vue";
import { useCartStore } from "@/stores/cart";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import { useToast } from "@/stores/toasts";
import { computedAsync } from "@vueuse/core";
import {useRouter} from "vue-router";

const props = defineProps<{
  bike: Bike;
}>();
const { bike } = reactive(props);

interface BikeDisplayInfo {
  orderer: string;
  date: string;
  returnState:
    | "as new"
    | "good"
    | "slightly damaged"
    | "bad"
    | "broken"
    | "lost";
  comment: string;
}

const data = computed<BikeDisplayInfo[]>(() =>
  bike.history.map((order) => ({
    orderer: order.ordererName,
    date: order.date.format("DD-MM-YYYY"),
    returnState: order.returnState,
    comment: order.comment,
  }))
);

const columns = [
  { name: "Orderer", accessor: "orderer" },
  {
    name: "Date",
    accessor: (row: BikeDisplayInfo) => row.date, //.toISOString().split("T")[0],
  },
  { name: "State", accessor: "returnState" },
  { name: "Comment", accessor: "comment" },
];

const { user, isConnected } = storeToRefs(useUserStore());
const { addToCart, cartContains } = useCartStore();
const { rentedContains, buyBike } = useBikesStore();

const { pushToast } = useToast();
const router = useRouter();

const orderDisabled = computedAsync(async () => {
  if (!isConnected.value) return true;
  return cartContains(bike.bikeId) || await rentedContains(bike.bikeId);
}, true);

const buyDisabled = computed(() => {
  if (!isConnected.value) return true;
  return bike.history.length < 1 || bike.ownerName == user.username;
})

function onAddToCart() {
  addToCart(bike.bikeId)
    .then((res) => {
      if (!res) throw new Error();
      pushToast("success", "Added to cart");
    })
    .catch(() => {
      pushToast("error", "Failed to add to cart");
    });
}

function onBuy(){
  buyBike(bike.bikeId)
      .then(() => {
        pushToast("success", "Bike bought successfully")
        router.push("/bikes")
      })
      .catch((err) => {
        pushToast("error", `Failed to buy bike (${err})`)
      })
}
</script>

<template>
  <div class="card min-w-[90vw] sm:min-w-[50vw] bg-base-100 shadow-xl">
    <div class="card-body">
      <h2 class="card-title font-bold text-[24pt] mb-5">
        Bike : {{ props.bike.bikeId }}
      </h2>
      <div class="flex flex-col gap-4">
        <div
          class="stats stats-vertical md:stats-horizontal w-full bg-primary text-primary-content shadow"
        >
          <div class="stat">
            <div class="stat-title">Owner</div>
            <div class="stat-value">{{ props.bike.ownerName }}</div>
          </div>
          <div class="stat">
            <div class="stat-title">Current State</div>
            <div class="stat-value">
              {{ bike.history[0]?.returnState ?? "N/A" }}
            </div>
          </div>
          <div class="stat">
            <div class="stat-title">Availability</div>
            <div class="stat-value">{{ bike.availability }}</div>
          </div>
          <div class="stat">
            <div class="stat-title">Value</div>
            <div class="stat-value">${{ bike.value }}</div>
          </div>
        </div>
        <div class="border border-base-300 bg-base-100 rounded-box">
          <div class="collapse-title text-xl font-medium">History</div>
          <div class="collapse-content max-h-[30vh] overflow-y-auto">
            <data-table :data="data" :columns="columns" />
          </div>
        </div>
      </div>

      <div v-if="isConnected" class="card-actions justify-end">
        <button
          class="btn btn-primary"
          @click="onAddToCart"
          :disabled="orderDisabled"
        >
          Add to cart
        </button>
        <button
            class="btn btn-primary"
            @click="onBuy"
            :class="{ hidden: buyDisabled }"
        >
          Buy it now (and become the owner)
        </button>
      </div>
    </div>
  </div>
</template>
