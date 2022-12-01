<script lang="ts" setup>
import { useUrlSearchParams } from "@vueuse/core";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";

const params = useUrlSearchParams("history");
delete params.mode;

const { user } = storeToRefs(useUserStore());
</script>

<template>
  <div class="hero min-h-full bg-base-200">
    <div class="hero-content flex-col gap-20 lg:flex-row-reverse">
      <div class="text-center">
        <h1 class="text-5xl font-bold">
          Welcome back,
          <span class="font-bold text-primary">{{ user.username }}</span>
          !
        </h1>
        <p class="py-6">
          Now that you're connected, you can order bikes
          <router-link class="link link-secondary" to="/bikes"
            >over there</router-link
          >
        </p>
      </div>
    </div>
  </div>
</template>
