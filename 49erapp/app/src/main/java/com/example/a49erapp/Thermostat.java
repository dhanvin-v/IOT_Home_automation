package com.example.a49erapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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


public class Thermostat extends AppCompatActivity {

    int fanVal;
    int modeVal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);


    }
    public void fanauto(View view)
    {
        fanVal = 0;
    }

    public void fanon(View view)
    {
        fanVal = 1;
    }

    public void modeoff(View view)
    {
        modeVal = 0;
    }

    public void modecool(View view)
    {
        modeVal = 1;
    }

    public void modeheat(View view)
    {
        modeVal = 2;
    }

    public void backtouser(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }


    public void apply_thermostat(View view) {

        EditText temperature = findViewById(R.id.Temperature_Value);
        final String Temperature = temperature.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_THERMOSTAT, new Response.Listener<String>() {
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
                params.put("temp", Temperature);
                params.put("fan", Integer.toString(fanVal));
                params.put("mode", Integer.toString(modeVal));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}



