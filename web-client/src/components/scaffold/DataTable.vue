<script lang="ts" setup>
import { computed } from "vue";

interface Column {
  name: string;
  accessor: string | ((row: any) => any);
}

const props = defineProps<{
  data: object[];
  columns: Column[];
}>();

const columnNames = computed(() => props.columns.map((c) => c.name));

function getCellValue(row: any, accessor: string | ((row: any) => any)) {
  if (typeof accessor === "string") {
    return row[accessor];
  } else {
    return accessor(row);
  }
}
</script>

<template>
  <div class="overflow-x-auto">
    <table class="table table-zebra w-full">
      <thead>
        <tr>
          <th></th>
          <th v-for="columnName of columnNames" :key="columnName">
            {{ columnName }}
          </th>
        </tr>
      </thead>
      <tbody>
        <!-- row 1 -->
        <tr v-for="(item, index) in data" :key="index">
          <th>{{ index + 1 }}</th>
          <td v-for="column of columns" :key="column.accessor">
            {{ getCellValue(item, column.accessor) }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
