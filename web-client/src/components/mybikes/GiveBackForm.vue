<script lang="ts" setup>
import { useField, useForm } from "vee-validate";
import * as yup from "yup";
import type { Bike } from "@/stores/bikes";
import InputField from "@/components/form/InputField.vue";
import SelectField from "@/components/form/SelectField.vue";

const props = defineProps<{
  bike: Bike;
}>();

const emit = defineEmits<{
  (
    e: "submit",
    order: { bike: Bike; returnState: string; comment: string }
  ): void;
  (e: "cancel"): void;
}>();

const form = useForm();
const { value: returnState, errors: returnStateErrors } = useField<string>(
  "returnState",
  yup
    .string()
    .required()
    .oneOf(["as new", "good", "slightly damaged", "bad", "broken", "lost"])
);

const { value: comment, errors: commentErrors } = useField<string>(
  "content",
  yup.string().required()
);

function onSubmit() {
  form
    .validate()
    .then((result) => {
      if (!result?.valid) throw "Invalid form";

      emit("submit", {
        bike: props.bike,
        returnState: returnState.value,
        comment: comment.value,
      });
      form.resetForm();
    })
    .catch((err) => console.log(err)); // TODO: handle error
}
</script>

<template>
  <div class="flex gap-2 align-center">
    <select-field
      class="w-full"
      label="Return State"
      v-model="returnState"
      :errors="returnStateErrors"
      head="Select return state"
      :values="[
        { key: 'as new', value: 'as new' },
        { key: 'good', value: 'good' },
        { key: 'slightly damaged', value: 'slightly damaged' },
        { key: 'bad', value: 'bad' },
        { key: 'broken', value: 'broken' },
        { key: 'lost', value: 'lost' },
      ]"
    />
    <input-field label="Comment" v-model="comment" :errors="commentErrors" />
  </div>
  <div class="flex-shrink-0 flex justify-end gap-4 items-center pt-4">
    <button class="btn btn-secondary" @click="onSubmit">Give Back</button>
    <button class="btn" @click="$emit('cancel')">Cancel</button>
  </div>
</template>
