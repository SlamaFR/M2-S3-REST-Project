<script lang="ts" setup>
import { useField, useForm } from "vee-validate";
import * as yup from "yup";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const router = useRouter();

const { login } = useUserStore();

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
      router.push("/bikes");
    })
    .catch((err) => console.log(err)); // TODO: handle error
}
</script>

<template>
  <div class="form-control">
    <label class="label">
      <span class="label-text">Username</span>
    </label>
    <input type="text" class="input input-bordered" v-model="username" />
    <span v-if="usernameErrors.length" class="text-error">{{
      usernameErrors[0]
    }}</span>
  </div>
  <div class="form-control">
    <label class="label">
      <span class="label-text">Password</span>
    </label>
    <input type="password" class="input input-bordered" v-model="password" />
    <span v-if="passwordErrors.length" class="text-error">{{
      passwordErrors[0]
    }}</span>
  </div>
  <div class="form-control mt-6">
    <button class="btn btn-primary" @click.prevent="onSubmit">Login</button>
  </div>
</template>
