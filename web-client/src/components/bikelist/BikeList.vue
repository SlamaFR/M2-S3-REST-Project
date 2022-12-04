<script lang="ts" setup>
import DataTable from "@/components/scaffold/table/DataTable.vue";
import type { Bike } from "@/stores/bikes";
import { useBikesStore } from "@/stores/bikes";
import { computedAsync, useAsyncState, useToggle } from "@vueuse/core";
import { useRouter } from "vue-router";
import { computed, onMounted, ref, watch } from "vue";

const router = useRouter();

const [filterAvailable, toggleFilterAvailable] = useToggle(false);
const { getAllBikes } = useBikesStore();

const state = ref<Bike[]>([]);
onMounted(async () => {
  state.value = await getAllBikes();
});
const data = computedAsync<Bike[]>(
  async () =>
    filterAvailable.value
      ? state.value.filter((bike) => bike.availability === "available")
      : state.value,
  []
);

function onRowClick(event: MouseEvent, bike: Bike) {
  router.push(`/bikes/${bike.bikeId}`);
}

const columns = [
  { name: "ID", accessor: "bikeId" },
  { name: "Owner", accessor: "ownerName" },
  {
    name: "State",
    accessor: (row: Bike) => row.history[0]?.returnState ?? "as new",
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
    <div class="max-h-[80vh] p-3 overflow-y-scroll">
      <data-table
        :data="data"
        :columns="columns"
        clickable
        @row-click="onRowClick"
      />
    </div>
  </div>
</template>
