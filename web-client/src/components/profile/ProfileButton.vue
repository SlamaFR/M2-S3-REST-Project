<script lang="ts" setup>
import { useUserStore } from "@/stores/user";

const { user, isConnected, login, logout } = useUserStore();

function onClickLogin() {
  login("notKamui", "password");
  console.log(user);
}

function onClickLogout() {
  logout();
}
</script>

<template>
  <label tabindex="0" class="btn btn-ghost btn-circle avatar">
    <div class="w-10 rounded-full">
      <img src="@/assets/avatar.jpg" alt="user-avatar" />
    </div>
  </label>
  <ul
    v-if="isConnected"
    tabindex="0"
    class="profile-menu tooltip tooltip-bottom"
    :data-tip="user.username"
  >
    <li><a>Profile</a></li>
    <li><a @click.prevent="onClickLogout">Logout</a></li>
  </ul>
  <ul v-else tabindex="0" class="profile-menu">
    <li><a>Sign in</a></li>
    <li><a @click.prevent="onClickLogin">Sign up</a></li>
  </ul>
</template>

<style scoped>
.profile-menu {
  @apply menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52;
}
</style>
