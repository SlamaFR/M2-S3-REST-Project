<script lang="ts" setup>
import DataTable from "@/components/scaffold/table/DataTable.vue";
import type { Bike } from "@/stores/bikes";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import { ArrowBarDownIcon } from "vue-tabler-icons";

const userStore = useUserStore();
const { rentedBikes: data } = storeToRefs(userStore);
const { removeBike } = userStore;

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
  {
    name: "Give Back",
    icon: ArrowBarDownIcon,
    onClick: (event: MouseEvent, bike: Bike) => removeBike(bike),
  },
];
</script>

<template>
  <div class="flex flex-col items-center gap-4">
    <data-table v-if="data.length > 0" :data="data" :columns="columns" />
    <span v-else class="text-xl font-medium text-center">No bikes rented</span>
  </div>
</template>
