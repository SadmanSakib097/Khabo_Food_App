package com.example.khabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp_Activity extends AppCompatActivity {

    TextView go_login;
    EditText userName,userPassword,userPhone,userEmail;
    Button userRegBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userName=(EditText) findViewById(R.id.userName);
        userEmail=(EditText) findViewById(R.id.userEmailAddress);
        userPassword=(EditText) findViewById(R.id.userPassword);
        userPhone=(EditText) findViewById(R.id.userPhone);
        userRegBtn=(Button) findViewById(R.id.regBtn);
        DB=new DBHelper(this);


        userRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=userName.getText().toString();
                String email=userEmail.getText().toString();
                String pass=userPassword.getText().toString();
                String phn=userPhone.getText().toString();

                if(user.equals("")||email.equals("")||pass.equals("")||phn.equals(""))
                    Toast.makeText(SignUp_Activity.this, "Fillup all the boxes", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser=DB.checkuseremail(email);
                    if(checkuser==false){
                        Boolean insert=DB.inserdata(user,email,pass,phn);
                        if(insert==true){
                            Toast.makeText(SignUp_Activity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Sign_In_Activity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignUp_Activity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUp_Activity.this, "User already exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        go_login=findViewById(R.id.go_to_login);
        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SignUp_Activity.this, "Login Page!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(SignUp_Activity.this,Sign_In_Activity.class);
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