package com.shoppingcart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shoppingcart.R;
import com.shoppingcart.models.Product;
import java.util.List;
import com.shoppingcart.utils.CartManager;
import com.shoppingcart.models.CartItem;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText("$" + product.getPrice());
        holder.description.setText(product.getDescription());
        holder.image.setImageResource(product.getImageResId()); // Load image

        holder.btnAddToCart.setOnClickListener(v -> {
            CartManager.addItem(context, new CartItem(product.getName(), product.getPrice(), 1));
            Toast.makeText(context, product.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, description;
        ImageView image;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgProduct); // Added ImageView
            name = itemView.findViewById(R.id.tvProductName);
            price = itemView.findViewById(R.id.tvProductPrice);
            description = itemView.findViewById(R.id.tvProductDescription);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
