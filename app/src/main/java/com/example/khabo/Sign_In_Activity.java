package com.example.khabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_In_Activity extends AppCompatActivity {
    TextView newAcc;
    EditText username,userpassword;
    Button loginButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username=(EditText) findViewById(R.id.userNameLogin);
        userpassword=(EditText) findViewById(R.id.userPasswordLogin);
        loginButton=(Button) findViewById(R.id.loginBtn);
        DB=new DBHelper(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String pass=userpassword.getText().toString();

                //SharePreference part starts
                SharedPreferences sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username",name);
                editor.apply();
                username.setText(name);
                //SharePreference part ends


                if(name.equals("")||pass.equals(""))
                    Toast.makeText(Sign_In_Activity.this, "Fillup all the box", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass=DB.checkusernamepassword(name,pass);
                        if(checkuserpass==true){
                            Toast.makeText(Sign_In_Activity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Sign_In_Activity.this, "Invalid User Info", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });

        //Get the value of shared preference
        SharedPreferences getSharedPreferences= getSharedPreferences("login", MODE_PRIVATE);
        String value= getSharedPreferences.getString("Username"," ");
        username.setText(value);


        newAcc=findViewById(R.id.newAcc);
        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Sign_In_Activity.this, "Crate New Account!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Sign_In_Activity.this,SignUp_Activity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}