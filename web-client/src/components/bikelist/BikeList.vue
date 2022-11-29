<script lang="ts" setup>
import DataTable from "@/components/scaffold/DataTable.vue";
import type { Bike } from "@/stores/bikes";
import { useBikesStore } from "@/stores/bikes";
import { storeToRefs } from "pinia";
import { computedAsync, useToggle } from "@vueuse/core";

const [filterAvailable, toggleFilterAvailable] = useToggle(false);
const { all, available } = storeToRefs(useBikesStore());
const data = computedAsync<Bike[]>(
  async () => (filterAvailable.value ? await available.value : await all.value),
  []
);

const columns = [
  { name: "ID", accessor: "id" },
  { name: "Owner", accessor: "owner" },
  {
    name: "Stars",
    accessor: (row: Bike) => row.history[0]?.comment?.stars.toString() ?? "N/A",
  },
  {
    name: "State",
    accessor: (row: Bike) => row.history[0]?.returnState?.state ?? "as new",
  },
  { name: "Availability", accessor: "availability" },
];
</script>

<template>
  <div class="flex flex-col items-center gap-4">
    <span class="flex items-center gap-4">
      <label class="font-bold">Show Available Only</label>
      <input
        type="checkbox"
        class="toggle toggle-lg"
        :class="{ 'toggle-secondary': filterAvailable }"
        @click="toggleFilterAvailable()"
        :checked="filterAvailable"
      />
    </span>
    <data-table :data="data" :columns="columns" />
  </div>
</template>
