<script lang="ts" setup>
import { useCartStore } from "@/stores/cart";
import { storeToRefs } from "pinia";
import DataTable from "@/components/scaffold/table/DataTable.vue";
import { computed } from "vue";
import type { Bike } from "@/stores/bikes";
import { useRouter } from "vue-router";
import { TrashIcon } from "vue-tabler-icons";
import { useToast } from "@/stores/toasts";
import { useBikesStore } from "@/stores/bikes";

const router = useRouter();

const { orderBikes } = useBikesStore();

const cartStore = useCartStore();
const { cart, count, subtotal } = storeToRefs(cartStore);
const { removeFromCart, clearCart } = cartStore;

const { pushToast } = useToast();

function onRemove(event: MouseEvent, bike: Bike) {
  removeFromCart(bike.bikeId);
  pushToast("info", "Removed from cart");
}

const data = computed<Bike[]>(() => cart.value.bikes);
const columns = [
  { name: "ID", accessor: "bikeId" },
  { name: "Owner", accessor: "ownerName" },
  {
    name: "State",
    accessor: (row: Bike) => row.history[0]?.returnState ?? "as new",
  },
  { name: "Availability", accessor: "availability" },
  { name: "Remove", icon: TrashIcon, onClick: onRemove },
];

function onRowClick(event: MouseEvent, bike: Bike) {
  router.push(`/bikes/${bike.bikeId}`);
}

function onOrder() {
  orderBikes(cart.value.bikes.map((bike) => bike.bikeId))
    .then(() => {
      pushToast("success", "Order placed");
      clearCart();
    })
    .catch((error) => {
      pushToast("error", error.message);
    });
}
</script>

<template>
  <div class="card min-w-[90vw] sm:min-w-[50vw] bg-base-100 shadow-xl">
    <div class="card-body">
      <h2 class="card-title font-bold text-[24pt] mb-5">Cart :</h2>
      <div class="flex flex-col gap-4">
        <div
          class="stats stats-vertical md:stats-horizontal w-full bg-primary text-primary-content shadow"
        >
          <div class="stat">
            <div class="stat-title">Size</div>
            <div class="stat-value">{{ count }}</div>
          </div>
          <div class="stat">
            <div class="stat-title">Subtotal</div>
            <div class="stat-value">${{ subtotal }}</div>
          </div>
        </div>
        <div class="my-5 collapse-content max-h-[30vh] overflow-y-auto">
          <data-table
            v-if="data.length > 0"
            :data="data"
            :columns="columns"
            clickable
            @row-click="onRowClick"
          />
          <div v-else class="text-xl font-medium text-center">
            Your cart is empty
          </div>
        </div>
      </div>

      <div class="card-actions justify-end">
        <button
          class="btn btn-primary"
          @click="onOrder"
          :disabled="count === 0"
        >
          Order
        </button>
      </div>
    </div>
  </div>
</template>
