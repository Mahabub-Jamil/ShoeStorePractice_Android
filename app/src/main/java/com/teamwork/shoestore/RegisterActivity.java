package com.teamwork.shoestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView registerback;
    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText phone;
    TextInputEditText address;
    TextInputEditText password;
    TextView signup;
    ProgressBar progressBar;
    TextView error;
    String Sname,Semail,Sphone,Saddress,Spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        setContentView(R.layout.activity_register);

        registerback = findViewById(R.id.alreadyhaveanccount);
        name = findViewById(R.id.registername);
        email = findViewById(R.id.registeremail);
        phone = findViewById(R.id.registerphone);
        address = findViewById(R.id.registeraddress);
        password = findViewById(R.id.registerpassword);
        signup = findViewById(R.id.signupbutton);
        progressBar = findViewById(R.id.registerprogressbar);
        error = findViewById(R.id.registererror);

        registerback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Sname = name.getText().toString();
                Semail = email.getText().toString();
                Sphone = phone.getText().toString();
                Saddress = address.getText().toString();
                Spassword = password.getText().toString();

                if(Semail.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(Semail).matches()){
                    email.setError("Valid Email required");
                    email.requestFocus();
                }
                if(Sphone.isEmpty()){
                    phone.setError("Valid contact number required");
                    phone.requestFocus();
                }
                if(Saddress.isEmpty()){
                    address.setError("Address required");
                    address.requestFocus();
                }
                if(Spassword.length()<6){
                    password.setError("Password must be SIX digit");
                    password.requestFocus();
                }
                if(Sname.isEmpty()){
                    name.setError("Name required");
                    name.requestFocus();
                }

                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                String url ="https://mj01861.000webhostapp.com/Registration.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.equals("Success")){
                                    Toast.makeText(RegisterActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    finish();
                                } else if (response.equals("EmailregisteredPhoneregistered")) {
                                    email.setError("Email already exist");
                                    email.requestFocus();
                                    phone.setError("Phone already exist");
                                    phone.requestFocus();

                                } else if (response.equals("Emailregistered")) {
                                    email.setError("Email already exist");
                                    email.requestFocus();
                                    
                                } else if (response.equals("Phoneregistered")) {
                                    phone.setError("Phone already exist");
                                    phone.requestFocus();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("name",Sname);
                        paramV.put("email",Semail);
                        paramV.put("phone",Sphone);
                        paramV.put("address",Saddress);
                        paramV.put("password",Spassword);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}