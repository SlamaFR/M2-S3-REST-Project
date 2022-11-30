import { defineStore, storeToRefs } from "pinia";
import { computed, watch } from "vue";
import type { Bike } from "@/stores/bikes";
import { useBikesStore } from "@/stores/bikes";
import { useCookies } from "@vueuse/integrations/useCookies";
import type { User } from "@/stores/user";
import { useUserStore } from "@/stores/user";

interface Cart {
  user: User;
  bikes: Bike[];
}

export const useCartStore = defineStore("cart", () => {
  const cookies = useCookies(["cart"]);
  const { user, isConnected } = storeToRefs(useUserStore());
  const { getBike } = useBikesStore();

  watch(isConnected, (isConnected) => {
    if (!isConnected) {
      cookies.remove("cart", { sameSite: "strict", secure: true });
    }
  });

  function setCart(newCart: Cart) {
    cookies.set("cart", newCart, {
      sameSite: "strict",
      secure: true,
      maxAge: 60 * 60 * 24 * 7,
    });
  }

  const cart = computed<Cart>(() => {
    const cart: Cart = cookies.get("cart");
    if (!cart || (cart.user?.id ?? undefined) !== user.value.id) {
      const newCart: Cart = { user: user.value, bikes: [] };
      setCart(newCart);
      return newCart;
    }
    return cart;
  });
  const count = computed(() => cart.value.bikes.length);
  const subtotal = computed(() =>
    cart.value.bikes.reduce((acc, bike) => acc + bike.value, 0)
  );

  async function addToCart(id: string): Promise<boolean> {
    const bike = await getBike(id);
    if (!bike) return false;
    if (cart.value.bikes.some((bike) => bike.id === id)) return false;
    const { user, bikes } = cart.value;
    const newCart: Cart = { user, bikes: [...bikes, bike] };
    setCart(newCart);
    return true;
  }

  async function removeFromCart(id: string): Promise<boolean> {
    const bike = await getBike(id);
    if (!bike) return false;
    const newCart = cart.value.bikes.filter((b) => b.id !== bike.id);
    setCart({ user: cart.value.user, bikes: newCart });
    return true;
  }

  async function clearCart() {
    setCart({ user: cart.value.user, bikes: [] });
  }

  function cartContains(id: string): boolean {
    return cart.value.bikes.some((bike) => bike.id === id);
  }

  return {
    cart,
    count,
    subtotal,
    addToCart,
    removeFromCart,
    clearCart,
    cartContains,
  };
});
