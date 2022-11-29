<script lang="ts" setup>
import { useField, useForm } from "vee-validate";
import * as yup from "yup";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import InputField from "@/components/form/InputField.vue";

const router = useRouter();

const { register } = useUserStore();

const form = useForm();
const { value: username, errors: usernameErrors } = useField<string>(
  "username",
  yup.string().required()
);

const { value: password, errors: passwordErrors } = useField<string>(
  "password",
  yup.string().required()
);

const { value: repeatPassword, errors: repeatPasswordErrors } =
  useField<string>("repeatPassword", yup.string().required());

function onSubmit() {
  form
    .validate()
    .then((result) => {
      if (!result?.valid) throw "Invalid form";
      if (password.value !== repeatPassword.value) {
        repeatPasswordErrors.value.push("Passwords do not match");
        throw "Invalid form";
      }

      const usernameValue = username.value;
      const passwordValue = password.value;
      form.resetForm();
      return register(usernameValue, passwordValue);
    })
    .catch((err) => console.log(err)); // TODO: handle error
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
  <input-field
    label="Repeat password"
    v-model="repeatPassword"
    :errors="repeatPasswordErrors"
    type="password"
  />
  <div class="form-control mt-6">
    <button class="btn btn-primary" @click.prevent="onSubmit">Register</button>
  </div>
</template>
