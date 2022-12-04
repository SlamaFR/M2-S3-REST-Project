<script lang="ts" setup>
import CartButton from "@/components/cart/CartButton.vue";
import CartMenu from "@/components/cart/CartMenu.vue";
import ProfileButton from "@/components/profile/ProfileButton.vue";
import { PlusIcon, CurrencyDollarIcon, CurrencyEuroIcon, CurrencyYenIcon } from "vue-tabler-icons";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import { useBikesStore } from "@/stores/bikes";
import NotificationsButton from "@/components/notifications/NotificationsButton.vue";
import { computedAsync } from "@vueuse/core";
import NotificationsMenu from "@/components/notifications/NotificationsMenu.vue";
import {computed} from "vue";
import CurrencyButton from "@/components/currency/CurrencyButton.vue";
import CurrencyMenu from "@/components/currency/CurrencyMenu.vue";

const destinations = [
  { name: "Home", path: "/" },
  { name: "All Bikes", path: "/bikes" },
  { name: "Rented Bikes", path: "/mybikes", connected: true },
];

const { user, isConnected } = storeToRefs(useUserStore());
const { addBikeToListings, getNotifications } = useBikesStore();

const notifications = computedAsync<string[]>(async () => await getNotifications(), []);

const currencyIcon = computed(() => {
  if (!isConnected || user.value.currency === "USD") return CurrencyDollarIcon;
  else if (user.value.currency === "EUR") return CurrencyEuroIcon;
  else if (user.value.currency === "YEN") return CurrencyYenIcon;
  else return CurrencyDollarIcon;
})

</script>

<template>
  <div class="navbar z-50 bg-base-100">
    <div class="flex-1">
      <router-link to="/" class="btn btn-ghost normal-case text-xl">
        Eiffel Bikes (Demo version)
      </router-link>
    </div>
    <div class="flex-none gap-5">
      <div class="dropdown dropdown-end btn-group">
        <router-link
          v-for="destination in destinations.filter((d) =>
            d['connected'] ? isConnected : true
          )"
          :key="destination.name"
          :to="destination.path"
          class="btn normal-case"
          active-class="btn-active"
        >
          {{ destination.name }}
        </router-link>
      </div>
      <div v-if="isConnected" class="dropdown dropdown-end">
        <cart-button/>
        <cart-menu />
      </div>
      <div v-if="isConnected" class="dropdown dropdown-end tooltip tooltip-bottom" data-tip="Add Bike to Listings">
        <button class="btn btn-circle btn-ghost" @click="addBikeToListings"><plus-icon /></button>
      </div>
      <div v-if="isConnected" class="dropdown dropdown-end">
        <notifications-button :count="notifications.length"/>
        <notifications-menu :notifications="notifications"/>
      </div>
      <div v-if="isConnected" class="dropdown dropdown-end">
        <currency-button :icon="currencyIcon"/>
        <currency-menu />
      </div>
      <div v-if="isConnected" class="dropdown dropdown-end">
        <span>
          {{ user.username }}
        </span>
      </div>
      <div class="dropdown dropdown-end">
        <profile-button />
      </div>
    </div>
  </div>
</template>
