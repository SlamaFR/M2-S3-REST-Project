<script lang="ts" setup>
import { useUrlSearchParams } from "@vueuse/core";
import LoginForm from "@/components/main/LoginForm.vue";
import RegisterForm from "@/components/main/RegisterForm.vue";

const params = useUrlSearchParams("history");

if (!params.mode || (params.mode !== "login" && params.mode !== "register")) {
  params.mode = "login";
}

function setMode(mode: string) {
  params.mode = mode;
}

function btnClass(mode: string) {
  return params.mode === mode ? "btn-active" : "";
}
</script>

<template>
  <div class="hero min-h-full bg-base-200">
    <div class="hero-content flex-col gap-20 lg:flex-row-reverse">
      <div class="text-center lg:text-left">
        <h1 class="text-5xl font-bold">Login now!</h1>
        <p class="py-6">
          You'll be able to access to the library of available bikes and add
          them to your cart to order them !
        </p>
      </div>
      <div class="card flex-shrink-0 w-full max-w-sm shadow-2xl bg-base-100">
        <div class="card-body">
          <div class="btn-group justify-end">
            <button
              class="btn"
              :class="btnClass('login')"
              @click.prevent="setMode('login')"
            >
              Login
            </button>
            <button
              class="btn"
              :class="btnClass('register')"
              @click.prevent="setMode('register')"
            >
              Register
            </button>
          </div>
          <login-form v-if="params.mode === 'login'" />
          <register-form v-else />
        </div>
      </div>
    </div>
  </div>
</template>
