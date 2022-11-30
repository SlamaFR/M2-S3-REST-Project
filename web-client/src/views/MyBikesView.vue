<script>
import RentedBikesList from "@/components/mybikes/RentedBikesList.vue";
import { defineComponent } from "vue";

export default defineComponent({
  components: { RentedBikesList },
});
</script>

<template>
  <div class="hero">
    <div class="hero-content">
      <rented-bikes-list />
    </div>
  </div>
</template>
