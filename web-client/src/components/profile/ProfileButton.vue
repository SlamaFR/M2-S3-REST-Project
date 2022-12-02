<script lang="ts" setup>
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";

const router = useRouter();

const store = useUserStore();
const { isConnected } = storeToRefs(store);
const { logout } = store;

function onClickLogin(mode: string) {
  router.push({ path: "/", query: { mode } });
}

function onClickLogout() {
  logout().then(() => {
    router.push({ path: "/" });
  });
}
</script>

<template>
  <div v-if="isConnected">
    <label tabindex="0" class="btn btn-ghost btn-circle avatar">
      <div class="w-10 rounded-full">
        <img src="@/assets/avatar.jpg" alt="user-avatar" />
      </div>
    </label>
    <ul tabindex="0" class="profile-menu">
      <li><a @click.prevent="onClickLogout">Logout</a></li>
    </ul>
  </div>
  <div v-else>
    <label tabindex="0" class="btn btn-ghost btn-circle avatar">
      <div class="w-10 rounded-full">
        <img src="@/assets/avatar.jpg" alt="user-avatar" />
      </div>
    </label>
    <ul tabindex="0" class="profile-menu">
      <li><a @click.prevent="onClickLogin('login')">Login</a></li>
      <li><a @click.prevent="onClickLogin('register')">Register</a></li>
    </ul>
  </div>
</template>

<style scoped>
.profile-menu {
  @apply menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52;
}
</style>
