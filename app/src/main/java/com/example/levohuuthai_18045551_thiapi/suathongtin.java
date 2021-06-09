package com.example.levohuuthai_18045551_thiapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class suathongtin extends AppCompatActivity {
    EditText edtIDSua,edtNameSua,edtTypeSua;
    Button btnSua;
    String url ="https://60afcfb4e6d11e00174f5300.mockapi.io/PhongBan1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suathongtin);
        edtIDSua=findViewById(R.id.edtIDSua);
        edtNameSua=findViewById(R.id.edtNameSua);
        edtTypeSua=findViewById(R.id.edtTypeSua);


        Intent intent = getIntent();
        edtIDSua.setText(intent.getStringExtra("id"));
        edtNameSua.setText(intent.getStringExtra("name"));
        edtTypeSua.setText(intent.getStringExtra("type"));

        btnSua=findViewById(R.id.btnSua);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url,edtIDSua.getText().toString().trim());
                Intent intent  = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    private void PutApi(String url,String id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(suathongtin.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(suathongtin.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", edtNameSua.getText().toString());
                params.put("age", edtTypeSua.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}