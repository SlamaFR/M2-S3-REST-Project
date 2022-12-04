<script lang="ts" setup>
import {ref, watch} from "vue";
import {useUserStore} from "@/stores/user";

const { setCurrency } = useUserStore();

const selected = ref("USD");
watch(selected, () => {
  setCurrency(selected.value);
});
</script>

<template>
  <div
      tabindex="0"
      class="mt-3 card card-compact dropdown-content w-[500px] bg-base-100 shadow"
  >
    <div class="card-body w-full">
      <span class="font-bold text-lg">
        Currency
      </span>
      <select v-model="selected" class="select select-bordered">
        <option value="USD">USD</option>
        <option value="EUR">EUR</option>
        <option value="JPY">JPY</option>
      </select>
    </div>
  </div>
</template>
