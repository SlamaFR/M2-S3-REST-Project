<script lang="ts" setup>
import DataTable from "@/components/scaffold/DataTable.vue";
import type { Bike } from "@/stores/bikes";
import { useBikesStore } from "@/stores/bikes";
import { storeToRefs } from "pinia";
import { ref } from "vue";

const { all } = storeToRefs(useBikesStore());
const data = ref<Bike[]>([]);

all.value.then((bikes) => {
  data.value = bikes;
});

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
  <data-table :data="data" :columns="columns" />
</template>
