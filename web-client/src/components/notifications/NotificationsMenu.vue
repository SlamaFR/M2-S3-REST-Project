<script lang="ts" setup>
defineProps<{
  notifications: string[];
}>();
</script>

<template>
  <div
    tabindex="0"
    class="mt-3 card card-compact dropdown-content w-[500px] bg-base-100 shadow"
  >
    <div class="card-body w-full">
      <span class="font-bold text-lg">
        {{ notifications.length }} Notifications
      </span>
      <ul class="w-full">
        <li
          v-for="notification in notifications"
          :key="notification"
          class="text-info bg-base-200 w-full my-1 p-2 rounded"
        >
          {{ notification }}
        </li>
      </ul>
    </div>
  </div>
</template>
