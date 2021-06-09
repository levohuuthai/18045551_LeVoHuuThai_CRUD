    package com.example.levohuuthai_18045551_thiapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    String url ="https://60afcfb4e6d11e00174f5300.mockapi.io/PhongBan1";
    //ArrayAdapter<String> arrayAdapter;
    //ArrayList<String> arrayList;
    List<PhongBan> phongBanList;
    AdapterPhongBan adapterPhongBan;
    ListView list;
    Button btnThem, btnXoa,btnSua22;
    EditText edtID, edtName, edtType;
    TextView tvID,tvName,tvType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        list = findViewById(R.id.list);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua22 = findViewById(R.id.btnSua22);
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtType = findViewById(R.id.edtType);
        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvType = findViewById(R.id.tvType);

        phongBanList = new ArrayList<PhongBan>();
        GetArrayJson(url);
        adapterPhongBan = new AdapterPhongBan(this,R.layout.phongban,(ArrayList<PhongBan>) phongBanList);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi(url);
                Intent intent = new Intent(MainActivity2.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteApi(url);
                Intent intent = new Intent(MainActivity2.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        //list.setAdapter(adapterPhongBan);
        //arrayList = new ArrayList<String>();
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        //list.setAdapter(arrayAdapter);

    }
    private void GetArrayJson(String url){
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url,
                        new  Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(MainActivity2.this,""+response.toString(),Toast.LENGTH_SHORT).show();
                                for(int i=0; i<response.length(); i++){
                                    try {
                                        JSONObject object = (JSONObject) response.get(i);
                                        PhongBan pb = new PhongBan();
                                        pb.setId(object.getString("id"));
                                        pb.setName(object.getString("name"));
                                        pb.setType(object.getString("type"));
                                        Log.d("deserialize",object.getString("id"));
                                        phongBanList.add(pb);
                                        adapterPhongBan = new AdapterPhongBan(MainActivity2.this,R.layout.phongban,(ArrayList<PhongBan>) phongBanList);
                                        adapterPhongBan.notifyDataSetChanged();
                                        list.setAdapter(adapterPhongBan);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    //Toast.makeText(MainActivity2.this, "thành công rùi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity2.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //tvName.setText("ssss");
                        Toast.makeText(MainActivity2.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity2.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String>
                        params = new HashMap<>();
                String id = params.put("id",String.valueOf(phongBanList.size()+1) );
                String name = params.put("name", edtName.getText().toString());
                String type = params.put("type", edtType.getText().toString());
                PhongBan pb = new PhongBan(id,name,type);
                phongBanList.add(pb);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + edtID.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity2.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}