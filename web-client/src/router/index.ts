import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import BikesView from "../views/BikesView.vue";

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
      component: BikesView,
    },
  ],
});

export default router;
