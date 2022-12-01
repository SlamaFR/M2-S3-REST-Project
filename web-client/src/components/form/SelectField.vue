<script lang="ts" setup>
withDefaults(
  defineProps<{
    label: string;
    head: string;
    modelValue: string;
    errors: string[];
    values: {
      key: string;
      value: any;
    }[];
  }>(),
  {
    modelValue: "",
    head: "Select one",
  }
);

defineEmits<{
  (e: "update:modelValue", value: string): void;
}>();
</script>

<template>
  <div class="form-control">
    <label class="label">
      <span class="label-text">{{ label }}</span>
    </label>
    <select
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      class="select select-bordered w-full max-w-xs border"
    >
      <option disabled selected>{{ head }}</option>
      <option v-for="value in values" :key="value.key" :value="value.value">
        {{ value.key }}
      </option>
    </select>
    <span v-if="errors.length" class="text-error">{{ errors[0] }}</span>
  </div>
</template>
