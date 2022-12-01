<script lang="ts" setup>
import type { Bike } from "@/stores/bikes";
import { computed, reactive } from "vue";
import DataTable from "@/components/scaffold/table/DataTable.vue";
import { useCartStore } from "@/stores/cart";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";

const props = defineProps<{
  bike: Bike;
}>();
const { bike } = reactive(props);

interface BikeDisplayInfo {
  date: Date;
  details: string;
  state: "as new" | "good" | "slightly damaged" | "bad" | "broken" | "lost";
  comment: string;
  stars: 0 | 1 | 2 | 3 | 4 | 5;
}

const data = computed<BikeDisplayInfo[]>(() =>
  bike.history.map((order) => ({
    orderer: order.orderer,
    date: order.date,
    details: order.returnState.details,
    state: order.returnState.state,
    comment: order.comment.content,
    stars: order.comment.stars,
  }))
);

const columns = [
  { name: "Orderer", accessor: "orderer" },
  {
    name: "Date",
    accessor: (row: BikeDisplayInfo) => row.date.toISOString().split("T")[0],
  },
  { name: "State", accessor: "state" },
  { name: "Details", accessor: "details" },
  { name: "Comment", accessor: "comment" },
  { name: "Stars", accessor: "stars" },
];

const { isConnected } = storeToRefs(useUserStore());
const { addToCart, cartContains } = useCartStore();

function onAddToCart() {
  addToCart(bike.id)
    .then((res) => {
      if (!res) throw new Error();
      console.log("Added to cart"); // TODO: Add a toast
    })
    .catch(() => {
      console.log("Failed to add to cart"); // TODO: Add a toast
    });
}
</script>

<template>
  <div class="card min-w-[90vw] sm:min-w-[50vw] bg-base-100 shadow-xl">
    <div class="card-body">
      <h2 class="card-title font-bold text-[24pt] mb-5">
        Bike : {{ props.bike.id }}
      </h2>
      <div class="flex flex-col gap-4">
        <div
          class="stats stats-vertical md:stats-horizontal w-full bg-primary text-primary-content shadow"
        >
          <div class="stat">
            <div class="stat-title">Owner</div>
            <div class="stat-value">{{ props.bike.owner }}</div>
          </div>
          <div class="stat">
            <div class="stat-title">Current State</div>
            <div class="stat-value">
              {{ bike.history[0]?.returnState?.state ?? "N/A" }}
            </div>
          </div>
          <div class="stat">
            <div class="stat-title">Availability</div>
            <div class="stat-value">{{ bike.availability }}</div>
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
          :disabled="cartContains(bike.id)"
        >
          Add to cart
        </button>
      </div>
    </div>
  </div>
</template>
