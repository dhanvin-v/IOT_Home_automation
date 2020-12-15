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

public class Locks extends AppCompatActivity {
    int front_door = 0;
    int garage_door = 0;
    int back_door = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locks);
    }

    public void fdopen(View view)
    {
        front_door = 1;
    }

    public void fdclose(View view)
    {
        front_door = 0;
    }
    public void gdopen(View view)
    {
        garage_door = 1;
    }
    public void gdclose(View view)
    {
        garage_door = 0;
    }
    public void bdopen(View view)
    {
        back_door = 1;
    }
    public void bdclose(View view)
    {
        back_door = 0;
    }
    public void backtouser(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }


    public void locks_apply(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOCKS, new Response.Listener<String>() {
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
                params.put("frontdoor", Integer.toString(front_door));
                params.put("garagedoor", Integer.toString(garage_door));
                params.put("backdoor", Integer.toString(back_door));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
