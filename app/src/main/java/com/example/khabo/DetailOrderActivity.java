package com.example.khabo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.khabo.databinding.ActivityDetailOrderBinding;

public class DetailOrderActivity extends AppCompatActivity {

    ActivityDetailOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final int image=getIntent().getIntExtra("image",0);
        final int price= Integer.parseInt(getIntent().getStringExtra("price"));
        final String name=getIntent().getStringExtra("name");

        binding.detailOrderImage.setImageResource(image);
        binding.priceLabel.setText(String.format("%d",price));
        binding.foodName.setText(name);



        DBHelper helper=new DBHelper(this);

        //Notification part starts
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("Order notification","Order notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        //Notification part ends
        binding.inserOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=helper.insertOrder(
                        binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        price,
                        image,
                        name
                );
                if(isInserted){

                    //Notification part starts
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(DetailOrderActivity.this,"Order notification");
                    builder.setContentTitle("Food order notification");
                    builder.setContentTitle("Your order has been placed");
                    builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
                    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    builder.setSound(uri);
                    builder.setAutoCancel(true);


                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(DetailOrderActivity.this);
                    managerCompat.notify(1,builder.build());

                    //Notification part ends


                }else
                    Toast.makeText(DetailOrderActivity.this, "Could not place the order", Toast.LENGTH_SHORT).show();


                //SharePreference part starts
                SharedPreferences sharedPreferences= getSharedPreferences("order", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username", binding.nameBox.getText().toString());
                editor.putString("Phone", binding.phoneBox.getText().toString());
                editor.apply();
               // binding.nameBox.setText(binding.nameBox.getText().toString());
                 //binding.phoneBox.setText(binding.phoneBox.getText().toString());
                //SharePreference part ends

            }
        });
        //Get the value of shared preference
        SharedPreferences getSharedPreferences= getSharedPreferences("order", MODE_PRIVATE);
        String nameValue= getSharedPreferences.getString("Username","");
        String phoneValue= getSharedPreferences.getString("Phone","");
        binding.nameBox.setText(nameValue);
        binding.phoneBox.setText(phoneValue);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}