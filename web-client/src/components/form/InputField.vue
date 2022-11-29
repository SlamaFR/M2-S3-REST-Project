<script lang="ts" setup>
defineProps<{
  label: string;
  modelValue?: string;
  errors: string[];
  type?: string;
}>();

defineEmits(["update:modelValue"]);
</script>

<template>
  <div class="form-control">
    <label class="label">
      <span class="label-text">{{ label }}</span>
    </label>
    <input
      :type="type ?? 'text'"
      class="input input-bordered"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
    />
    <span v-if="errors.length" class="text-error">{{ errors[0] }}</span>
  </div>
</template>
