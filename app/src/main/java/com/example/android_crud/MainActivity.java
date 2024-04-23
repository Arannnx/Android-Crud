package com.example.android_crud;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txt_uname = findViewById(R.id.txt_uname);
        EditText txt_pword = findViewById(R.id.txt_pword);
        EditText txt_email = findViewById(R.id.txt_email);
        Button btn_save = findViewById(R.id.btn_save);
        Button btn_update = findViewById(R.id.btn_update);
        Button btn_retrieve = findViewById(R.id.btn_to_retrieve);
        Button btn_delete = findViewById(R.id.btn_delete);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdate();
            }
        });

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openretrieve();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendelete();
            }
        });

        // add btn_save onclickListener
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = txt_uname.getText().toString();
                String pword = txt_pword.getText().toString();
                String email = txt_email.getText().toString();

                // Use MainActivity.this as the context
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.19.242/android_crud/create.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response here
                        if (response.equals("Success")) {
                            Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Database failed to Add to Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("uname", uname);
                        paramV.put("pword", pword);
                        paramV.put("email", email);
                        return paramV;
                    }
                };

                queue.add(stringRequest);
            }
        });
    }

    public void openUpdate() {
        Intent intent = new Intent(this, update.class);
        startActivity(intent);

    }

    public void openretrieve() {
        Intent intent = new Intent(this, retrieve.class);
        startActivity(intent);

    }

    public void opendelete() {
        Intent intent = new Intent(this, delete.class);
        startActivity(intent);

    }
}