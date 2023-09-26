package com.example.khabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.khabo.Adapters.FoodAdapterUser;
import com.example.khabo.Models.FoodModelUser;
import com.example.khabo.databinding.ActivityFoodListUserBinding;

import java.util.ArrayList;

public class Food_list_User extends AppCompatActivity {


    ActivityFoodListUserBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodListUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<FoodModelUser> listUser = new ArrayList<>();
        listUser.add(new FoodModelUser(R.drawable.biryani, "Biryani", "200"));
        listUser.add(new FoodModelUser(R.drawable.fc, "Fried Chicken", "150"));
        listUser.add(new FoodModelUser(R.drawable.burger, "Burger", "180"));
        listUser.add(new FoodModelUser(R.drawable.pizza, "Pizza", "350"));
        listUser.add(new FoodModelUser(R.drawable.ff, "French Fries", "120"));
        listUser.add(new FoodModelUser(R.drawable.chaomin, "Chaomin", "100"));

        FoodAdapterUser adapter = new FoodAdapterUser(listUser, this);
        binding.recycleviewFoodlistUser.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycleviewFoodlistUser.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Food List User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(Food_list_User.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}