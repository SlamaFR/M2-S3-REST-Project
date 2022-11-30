import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import BikeListView from "@/views/BikeListView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/bikes",
      name: "bikes",
      component: BikeListView,
    },
    {
      path: "/bikes/:id",
      name: "bike",
      component: () => import("@/views/BikeView.vue"),
    },
    {
      path: "/cart",
      name: "cart",
      component: () => import("@/views/CartView.vue"),
    },
    {
      path: "/mybikes",
      name: "mybikes",
      component: () => import("@/views/MyBikesView.vue"),
    }
  ],
});

export default router;
