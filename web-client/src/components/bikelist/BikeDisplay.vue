<script lang="ts" setup>
import type { Bike } from "@/stores/bikes";
import { computed, reactive } from "vue";
import DataTable from "@/components/scaffold/DataTable.vue";

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
    date: order.date,
    details: order.returnState.details,
    state: order.returnState.state,
    comment: order.comment.content,
    stars: order.comment.stars,
  }))
);

const columns = [
  {
    name: "Date",
    accessor: (row: BikeDisplayInfo) => row.date.toISOString().split("T")[0],
  },
  { name: "State", accessor: "state" },
  { name: "Details", accessor: "details" },
  { name: "Comment", accessor: "comment" },
  { name: "Stars", accessor: "stars" },
];

function onAddToCart() {
  // TODO: Add bike to cart
}
</script>

<template>
  <div class="card w-[90vw] sm:w-[50vw] bg-base-100 shadow-xl">
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

      <div class="card-actions justify-end">
        <button class="btn btn-primary" @click="onAddToCart">
          Add to cart
        </button>
      </div>
    </div>
  </div>
</template>
