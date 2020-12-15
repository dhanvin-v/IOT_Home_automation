package com.example.a49erapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GarageDoor extends AppCompatActivity {
    int twodoor = 0;
    int onedoor = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garagedoor);
    }

    public void tdopen(View view){ twodoor = 1;}
    public void tdclose(View view){ twodoor = 0;}
    public void odopen(View view) { onedoor = 1;}
    public void odclose(View view) { onedoor = 0;}
    public void backtouser(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }

    public void gd_apply(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GARAGEDOOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                }catch(JSONException e){
                    e.printStackTrace();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("garagetwodoor", Integer.toString(twodoor));
                params.put("garageonedoor", Integer.toString(onedoor));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    }

