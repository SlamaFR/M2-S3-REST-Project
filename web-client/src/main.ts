import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import "./assets/main.css";
import { vfmPlugin } from "vue-final-modal";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(
  vfmPlugin({
    key: "$vfm",
    componentName: "VueFinalModal",
    dynamicContainerName: "ModalsContainer",
  })
);

app.mount("#app");
