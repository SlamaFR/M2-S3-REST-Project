<script lang="ts" setup>
import { computed } from "vue";
import type { Column } from "@/components/scaffold/table/column";
import DataTableRow from "@/components/scaffold/table/DataTableRow.vue";

const props = withDefaults(
  defineProps<{
    data: object[];
    columns: Column[];
    clickable?: boolean;
  }>(),
  {
    clickable: false,
  }
);

defineEmits<{
  (e: "row-click", row: object): void;
}>();

const columnNames = computed(() => props.columns.map((c) => c.name));
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
      <tbody v-if="clickable">
        <tr
          v-for="(item, index) in data"
          :key="index"
          class="hover hover:cursor-pointer"
          @click.prevent="$emit('row-click', $event, item)"
        >
          <data-table-row :line="index + 1" :item="item" :columns="columns" />
        </tr>
      </tbody>
      <tbody v-else>
        <tr v-for="(item, index) in data" :key="index">
          <data-table-row :line="index + 1" :item="item" :columns="columns" />
        </tr>
      </tbody>
    </table>
  </div>
</template>
