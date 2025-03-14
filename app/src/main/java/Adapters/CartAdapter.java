package com.shoppingcart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shoppingcart.R;
import com.shoppingcart.models.CartItem;
import com.shoppingcart.utils.CartManager;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private Context context;

    public CartAdapter(List<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("$" + item.getPrice());
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.totalPrice.setText("Total: $" + item.getTotalPrice());

        // Increase Quantity
        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.quantity.setText(String.valueOf(item.getQuantity()));
            holder.totalPrice.setText("Total: $" + item.getTotalPrice());
            notifyDataSetChanged();
        });

        // Decrease Quantity
        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.quantity.setText(String.valueOf(item.getQuantity()));
                holder.totalPrice.setText("Total: $" + item.getTotalPrice());
                notifyDataSetChanged();
            }
        });

        // Remove Item
        holder.btnRemove.setOnClickListener(v -> {
            CartManager.removeItem(context,item.getName());
            cartItems.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity, totalPrice;
        Button btnIncrease, btnDecrease, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvCartItemName);
            price = itemView.findViewById(R.id.tvCartItemPrice);
            quantity = itemView.findViewById(R.id.tvCartItemQuantity);
            totalPrice = itemView.findViewById(R.id.tvCartItemTotalPrice);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
