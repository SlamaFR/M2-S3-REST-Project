<script lang="ts" setup>
import type {Component} from "vue";
import {storeToRefs} from "pinia";
import {useUserStore} from "@/stores/user";
import {onMounted, ref, watch} from "vue";
import {useTimeoutPoll} from "@vueuse/core";

defineProps<{
  icon: Component
}>();

const store = useUserStore();
const { getBalance } = store;
const { user } = storeToRefs(store)

const balance = ref(0)
onMounted(async () => {
  balance.value = await getBalance();
})
useTimeoutPoll(async () => balance.value = await getBalance(), 5000, { immediate: true })
watch(user, () => {
  getBalance()
      .then((value) => balance.value = value)
      .catch((err) => console.log(err));
})
</script>

<template>
  <button class="btn btn-ghost"><component :is="icon" /> {{ balance }}</button>
</template>