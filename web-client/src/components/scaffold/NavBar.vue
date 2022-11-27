<script lang="ts" setup>
import CartButton from "@/components/cart/CartButton.vue";
import CartMenu from "@/components/cart/CartMenu.vue";
import ProfileButton from "@/components/profile/ProfileButton.vue";

const destinations = [
  { name: "Home", path: "/" },
  { name: "Bikes", path: "/bikes" },
];
</script>

<template>
  <div class="navbar bg-base-100">
    <div class="flex-1">
      <router-link to="/" class="btn btn-ghost normal-case text-xl">
        Eiffel Bikes
      </router-link>
    </div>
    <div class="flex-none gap-5">
      <div class="dropdown dropdown-end btn-group">
        <router-link
          v-for="destination in destinations"
          :key="destination.name"
          :to="destination.path"
          class="btn normal-case"
          active-class="btn-active"
        >
          {{ destination.name }}
        </router-link>
      </div>
      <div class="dropdown dropdown-end">
        <cart-button size="8" />
        <cart-menu />
      </div>
      <div class="dropdown dropdown-end">
        <profile-button />
      </div>
    </div>
  </div>
</template>
