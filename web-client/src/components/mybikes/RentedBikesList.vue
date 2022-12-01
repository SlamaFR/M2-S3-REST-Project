<script lang="ts" setup>
import DataTable from "@/components/scaffold/table/DataTable.vue";
import type { Bike } from "@/stores/bikes";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import { ArrowBarDownIcon } from "vue-tabler-icons";
import Modal from "@/components/scaffold/Modal.vue";
import { ref } from "vue";
import GiveBackForm from "@/components/mybikes/GiveBackForm.vue";

const userStore = useUserStore();
const { rentedBikes: data } = storeToRefs(userStore);
const { removeBike } = userStore;

const showGiveBackModal = ref(false);
const bikeToGiveBack = ref<Bike | null>(null);

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
    onClick: (event: MouseEvent, bike: Bike) => {
      showGiveBackModal.value = true;
      bikeToGiveBack.value = bike;
    },
  },
];

function onSubmit(order: { bike: Bike; returnState: string; comment: string }) {
  removeBike(order.bike, order.returnState, order.comment);
  onClose();
}

function onClose() {
  bikeToGiveBack.value = null;
  showGiveBackModal.value = false;
}
</script>

<template>
  <div class="flex flex-col items-center gap-4">
    <data-table v-if="data.length > 0" :data="data" :columns="columns" />
    <span v-else class="text-xl font-medium text-center">No bikes rented</span>
  </div>

  <modal
    title="Give Back Bike"
    v-model="showGiveBackModal"
    :confirm-button="{ text: 'Give Back', classes: 'btn-secondary' }"
    :cancel-button="{ text: 'Cancel', classes: '' }"
  >
    <div class="flex flex-col items-center gap-4 m-10">
      <span class="text-xl font-medium text-center">
        Please give information about the bike
      </span>
      <give-back-form
        :bike="bikeToGiveBack"
        @submit="onSubmit"
        @cancel="onClose"
      />
    </div>
  </modal>
</template>
