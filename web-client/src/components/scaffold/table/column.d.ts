import { Component } from "vue";

export interface Column {
  name: string;
}

export interface Accessor extends Column {
  accessor: string | ((row: unknown) => any);
}

export interface Button extends Column {
  icon: Component;
  onClick: (event: MouseEvent, row: unknown) => void;
}
