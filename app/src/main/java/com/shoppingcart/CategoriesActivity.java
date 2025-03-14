package com.shoppingcart;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ImageButton btnCars = findViewById(R.id.btnCars);
        ImageButton btnBikes = findViewById(R.id.btnBikes);
        ImageButton btnMotorcycles = findViewById(R.id.btnMotorcycles);
        ImageButton btnAccessories = findViewById(R.id.btnAccessories);

        btnCars.setOnClickListener(v -> openCategory("Cars"));
        btnBikes.setOnClickListener(v -> openCategory("Bikes"));
        btnMotorcycles.setOnClickListener(v -> openCategory("Motorcycles"));
        btnAccessories.setOnClickListener(v -> openCategory("Accessories"));

        ImageButton btnCart = findViewById(R.id.btnCart);
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
                });

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

    private void openCategory(String category) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
