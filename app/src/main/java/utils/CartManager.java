package com.shoppingcart.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shoppingcart.models.CartItem;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<CartItem> cartItems = new ArrayList<>();
    private static final String PREF_NAME = "CartPrefs";
    private static final String CART_KEY = "cart_items";

    public static void loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_KEY, null);
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        if (json != null) {
            cartItems = gson.fromJson(json, type);
        } else {
            cartItems = new ArrayList<>();
        }
    }

    public static void saveCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public static void addItem(Context context, CartItem item) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getName().equals(item.getName())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                saveCart(context);
                return;
            }
        }
        cartItems.add(item);
        saveCart(context);
    }

    public static void removeItem(Context context, String productName) {
        cartItems.removeIf(item -> item.getName().equals(productName));
        saveCart(context);
    }

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public static void clearCart(Context context) {
        cartItems.clear();
        saveCart(context);
    }
}
