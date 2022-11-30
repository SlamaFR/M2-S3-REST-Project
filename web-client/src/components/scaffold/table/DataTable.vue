<script lang="ts" setup>
import { computed, defineComponent } from "vue";
import type { Component } from "vue";
import CellButton from "@/components/scaffold/table/CellButton.vue";

interface Accessor {
  name: string;
  accessor: string | ((row: unknown) => any);
}

interface Button {
  name: string;
  icon: Component;
  onClick: (event: MouseEvent, row: unknown) => void;
}

const props = withDefaults(
  defineProps<{
    data: object[];
    columns: (Accessor | Button)[];
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
      <tbody v-if="clickable">
        <tr
          v-for="(item, index) in data"
          :key="index"
          class="hover hover:cursor-pointer"
          @click.prevent="$emit('row-click', $event, item)"
        >
          <th>{{ index + 1 }}</th>
          <td v-for="column of columns" :key="column.name">
            <span v-if="'accessor' in column">{{
              getCellValue(item, column.accessor)
            }}</span>
            <CellButton
              v-else
              :item="item"
              :icon="column.icon"
              @click="column.onClick"
            />
          </td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr v-for="(item, index) in data" :key="index">
          <th>{{ index + 1 }}</th>
          <td v-for="column of columns" :key="column.name">
            <span v-if="'accessor' in column">{{
              getCellValue(item, column.accessor)
            }}</span>
            <cell-button
              v-else
              :item="item"
              :icon="column.icon"
              @click="column.onClick"
            />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
