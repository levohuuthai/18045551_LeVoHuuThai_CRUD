package com.example.levohuuthai_18045551_thiapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhongBan extends BaseAdapter {

    Context context;
    String url ="https://60afcfb4e6d11e00174f5300.mockapi.io/PhongBan1";
    public AdapterPhongBan(){

    }
    int layout;
    List<PhongBan> phongBanList;
    public AdapterPhongBan(Context context, int layout, List<PhongBan>phongBanList) {
        this.context = context;
        this.layout = layout;
        this.phongBanList = phongBanList;
    }
    @Override
    public int getCount() {
        return phongBanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layout,parent,false);
        TextView id,name,type;
        Button btnEdit, btnDelete;

        id = convertView.findViewById(R.id.tvID);
        name = convertView.findViewById(R.id.tvName);
        type = convertView.findViewById(R.id.tvType);
/*
        id = convertView.findViewById(R.id.edtID);
        name = convertView.findViewById(R.id.edtName);
        type = convertView.findViewById(R.id.edtType);*/
        PhongBan pb = phongBanList.get(position);
        id.setText(pb.getId());
        name.setText(pb.getName());
        type.setText(pb.getType());

        //SuaDULIEU
        btnEdit = convertView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhongBan pb = phongBanList.get(position);
                Intent intent = new Intent(context,suathongtin.class);
                intent.putExtra("id",pb.getId());
                intent.putExtra("name",pb.getName());
                intent.putExtra("type",pb.getType());
                context.startActivity(intent);
            }
        });
        //Xóa DL
        btnDelete=convertView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteApi(url,id.getText().toString().trim());
                Intent intent  = new Intent(context.getApplicationContext(),MainActivity2.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    private void DeleteApi(String url,String id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context.getApplicationContext(), "Successfully!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
