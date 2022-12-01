<script lang="ts" setup>
import type { Column } from "@/components/scaffold/table/column";
import CellButton from "@/components/scaffold/table/CellButton.vue";

defineProps<{
  line: number;
  item: any;
  columns: Column[];
}>();

function getCellValue(row: any, accessor: string | ((row: any) => any)) {
  if (typeof accessor === "string") {
    return row[accessor];
  } else {
    return accessor(row);
  }
}
</script>

<template>
  <th>{{ line }}</th>
  <td v-for="column of columns" :key="column.name">
    <span v-if="'accessor' in column">{{
      getCellValue(item, column.accessor)
    }}</span>
    <cell-button
      v-else-if="'onClick' in column"
      :item="item"
      :icon="column.icon"
      @click="column.onClick"
    />
  </td>
</template>
