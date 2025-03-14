package com.shoppingcart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shoppingcart.adapters.CartAdapter;
import com.shoppingcart.models.CartItem;
import com.shoppingcart.utils.CartManager;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        CartManager.loadCart(this);  // Load cart from SharedPreferences
        cartItems = CartManager.getCartItems();
        cartAdapter = new CartAdapter(cartItems, this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> showCheckoutDialog());
    }


    private void updateTotalPrice() {
        tvTotalPrice.setText("Total: $" + CartManager.getTotalPrice());
    }

    private void showCheckoutDialog() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Confirm Checkout")
                .setMessage("Your total is $" + CartManager.getTotalPrice() + ".\nDo you want to proceed with checkout?")
                .setPositiveButton("Yes, Checkout", (dialog, which) -> {
                    CartManager.clearCart(this);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}