<script lang="ts" setup>
import { useField, useForm } from "vee-validate";
import * as yup from "yup";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import InputField from "@/components/form/InputField.vue";
import { useToast } from "@/stores/toasts";

const router = useRouter();

const { login } = useUserStore();
const { pushToast } = useToast();

const form = useForm();
const { value: username, errors: usernameErrors } = useField<string>(
  "username",
  yup.string().required()
);

const { value: password, errors: passwordErrors } = useField<string>(
  "password",
  yup.string().required()
);

function onSubmit() {
  form
    .validate()
    .then((result) => {
      if (!result?.valid) throw "Invalid form";

      const usernameValue = username.value;
      const passwordValue = password.value;
      form.resetForm();
      return login(usernameValue, passwordValue);
    })
    .then(() => {
      pushToast("success", "Successfully logged in");
    })
    .catch((err) => pushToast("error", err));
}
</script>

<template>
  <input-field label="Username" v-model="username" :errors="usernameErrors" />
  <input-field
    label="Password"
    v-model="password"
    :errors="passwordErrors"
    type="password"
  />
  <div class="form-control mt-6">
    <button class="btn btn-primary" @click.prevent="onSubmit">Login</button>
  </div>
</template>