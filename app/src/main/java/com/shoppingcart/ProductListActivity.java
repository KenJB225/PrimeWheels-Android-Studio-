package com.shoppingcart;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shoppingcart.adapters.ProductAdapter;
import com.shoppingcart.models.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
        });

        TextView categoryTitle = findViewById(R.id.tvCategoryTitle);
        recyclerView = findViewById(R.id.recyclerView);

        String category = getIntent().getStringExtra("category");
        categoryTitle.setText(category);

        productList = new ArrayList<>();
        loadProducts(category);

        adapter = new ProductAdapter(productList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        ImageButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            // Click Animation
            ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                    btnCart,
                    PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1f));
            scaleDown.setDuration(150);
            scaleDown.start();

            // Navigate to CartActivity
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void loadProducts(String category) {
        productList.clear();
        if (category.equals("Cars")) {
            productList.add(new Product("Pagani Zonda", 1452000, "High performance hyper car", R.drawable.car_zonda));
            productList.add(new Product("Lamborghini Urus", 275000, "High performance luxury SUV", R.drawable.car_urus));
            productList.add(new Product("Nissan Skyline R34 GT-R", 1357000, "Classic sports car", R.drawable.car_r34));
            productList.add(new Product("Nissan 350z", 34500, "High performance sports car", R.drawable.car_350z));
        } else if (category.equals("Bikes")) {
            productList.add(new Product("Giant Propel", 2500, "Aerodynamic road bike", R.drawable.bike_giant));
            productList.add(new Product("Trek Marlin 7", 1100, "Versatile mountain bike", R.drawable.bike_trek));
            productList.add(new Product("Tarmac SL 8", 14000, "Versatile mountain bike", R.drawable.bike_tarmac));
            productList.add(new Product("Cervelo s5 Ultegra di2", 9000, "Versatile mountain bike", R.drawable.bike_cervelo));
        } else if (category.equals("Motorcycles")) {
            productList.add(new Product("Harley-Davidson Iron 883", 9999, "Iconic cruiser motorcycle", R.drawable.motorcycle_harley));
            productList.add(new Product("Yamaha trene 700", 12500, "High-performance adventure motorcycle", R.drawable.motorcycle_yamaha));
            productList.add(new Product("Kawasaki Maisto Metal Ninja H2r", 58100, "High-performance hypersport motorcycle", R.drawable.motorcycle_kawasaki));
            productList.add(new Product("BMW S1000RR", 27519, "High-performance track motorcycle", R.drawable.motorcycle_bmw));
        } else if (category.equals("Accessories")) {
            productList.add(new Product("Leather Seat Covers", 199, "Premium seat protection for your car.", R.drawable.accessory_seat_cover));
            productList.add(new Product("Shoei Neotec 3 Grasp TC5 helmet", 400, "Safety-first full-face helmet.", R.drawable.accessory_helmet));
            productList.add(new Product("Specialized s evade", 200, "Safety-first bike helmet.", R.drawable.accessory_bike_helmet));
            productList.add(new Product("KMP F8X M2C / M3 / M4 Racing Wheel", 500, "Safety-first full-face helmet.", R.drawable.accessory_wheel));
        }
    }
}