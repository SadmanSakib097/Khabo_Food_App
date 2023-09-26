package com.example.khabo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khabo.Adapters.FoodAdapter;
import com.example.khabo.Models.FoodModel;
import com.example.khabo.databinding.ActivityFoodListBinding;

import java.util.ArrayList;

public class Food_list extends AppCompatActivity {


    ActivityFoodListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<FoodModel> list = new ArrayList<>();
        list.add(new FoodModel(R.drawable.biryani, "Biryani", "200"));
        list.add(new FoodModel(R.drawable.fc, "Fried Chicken", "150"));
        list.add(new FoodModel(R.drawable.burger, "Burger", "180"));
        list.add(new FoodModel(R.drawable.pizza, "Pizza", "350"));
        list.add(new FoodModel(R.drawable.ff, "French Fries", "120"));
        list.add(new FoodModel(R.drawable.chaomin, "Chaomin", "200"));

        FoodAdapter adapter = new FoodAdapter(list, this);
        binding.recycleviewFoodlist.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycleviewFoodlist.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Food List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}