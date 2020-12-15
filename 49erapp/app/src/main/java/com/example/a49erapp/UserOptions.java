package com.example.a49erapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
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


public class UserOptions extends AppCompatActivity{

    private WebView camera;


    int Light01 = 0 , Light02 = 0, Light11 = 0, Light12 = 0, Sensor01 = 0,Sensor02 = 0,Sensor11 = 0,
            Sensor12 = 0, md1 = 0, md2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
//        camera = (WebView) findViewById(R.id.activity_camera);
    }
    public void first_floor(View view){
        setContentView(R.layout.activity_floor0);
    }
    public void second_floor(View view){
        setContentView(R.layout.activity_floor1);
    }

    public void backtouser(View view){
        setContentView(R.layout.activity_options);
    }

    public void Thermostat(View view){
        Intent intent = new Intent(this,Thermostat.class);
        startActivity(intent);
    }

    public void Security(View view){
        Intent intent = new Intent(this,Security.class);
        startActivity(intent);
    }

    public void Lock(View view){
        Intent intent = new Intent(this,Locks.class);
        startActivity(intent);
    }

    public void Garagedoor(View view){
        Intent intent = new Intent(this,GarageDoor.class);
        startActivity(intent);
    }

    public void Weather (View view){
        Intent intent = new Intent(this,Weather.class);
        startActivity(intent);
    }

    public void Camera(View view)
    {
        Intent intent = new Intent(this,Camera.class);
        startActivity(intent);
    }

    public void Sensors(View view)
    {
        Intent intent = new Intent(this,Sensors.class);
        startActivity(intent);
    }

    public void updateserverf0(View view){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_FLOOR1, new Response.Listener<String>() {
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
                        // progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("light1", Integer.toString(Light01));
                params.put("light2", Integer.toString(Light02));
                params.put("sensors1", Integer.toString(Sensor01));
                params.put("sensors2", Integer.toString(Sensor02));
                params.put("motiondetector", Integer.toString(md1));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void updateserverf1(View view){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_FlOOR2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                }catch(JSONException e){
                    e.printStackTrace();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("light1", Integer.toString(Light11));
                params.put("light2", Integer.toString(Light12));
                params.put("sensor1", Integer.toString(Sensor11));
                params.put("sensor2", Integer.toString(Sensor12));
                params.put("motiondetector", Integer.toString(md2));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void F0L1on(View view){
        Light01 = 1;
    }
    public void F0L1off(View view){
        Light01 = 0;
    }
    public void F0L2on(View view){ Light02 = 1; }
    public void F0L2off(View view){
        Light02 = 0;
    }
    public void F0S1on(View view) {Sensor01 = 1;}
    public void F0S1off(View view) {Sensor01 = 0;}
    public void F0S2on(View view) {Sensor02 = 1;}
    public void F0S2off(View view) {Sensor02 = 0;}
    public void F0Mon(View view) { md1 = 1;}
    public void F0Moff(View view) { md1 = 0;}


    public void F1L1on(View view){
        Light11 = 1;
    }
    public void F1L1off(View view){
        Light11 = 0;
    }
    public void F1L2on(View view){ Light12 = 1; }
    public void F1L2off(View view){
        Light12 = 0;
    }
    public void F1S1on(View view) {Sensor11 = 1;}
    public void F1S1off(View view) {Sensor11 = 0;}
    public void F1S2on(View view) {Sensor12 = 1;}
    public void F1S2off(View view) {Sensor12 = 0;}
    public void F1Mon(View view) { md2 = 1;}
    public void F1Moff(View view) { md2 = 0;}
}






