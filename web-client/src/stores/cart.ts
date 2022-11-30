import { defineStore } from "pinia";
import { computed } from "vue";
import type { Bike } from "@/stores/bikes";
import { useBikesStore } from "@/stores/bikes";
import { useCookies } from "@vueuse/integrations/useCookies";

export const useCartStore = defineStore("cart", () => {
  const cookies = useCookies(["cart"]);
  const { get } = useBikesStore();

  function setCart(newCart: Bike[]) {
    cookies.set("cart", newCart, {
      sameSite: "strict",
      secure: true,
      maxAge: 60 * 60 * 24 * 7,
    });
  }

  const cart = computed<Bike[]>(() => {
    const cart = cookies.get("cart");
    if (cart === undefined) {
      const newCart: Bike[] = [];
      setCart(newCart);
      return newCart;
    }
    return cart;
  });
  const count = computed(() => cart.value.length);
  const subtotal = computed(() =>
    cart.value.reduce((acc, bike) => acc + bike.value, 0)
  );

  async function addToCart(id: string): Promise<boolean> {
    const bike = await get(id);
    if (!bike) return false;
    const newCart = [...cart.value, bike];
    setCart(newCart);
    return true;
  }

  async function removeFromCart(id: string): Promise<boolean> {
    const bike = await get(id);
    if (!bike) return false;
    const newCart = cart.value.filter((b) => b.id !== bike.id);
    setCart(newCart);
    return true;
  }

  return {
    cart,
    count,
    subtotal,
    addToCart,
    removeFromCart,
  };
});
