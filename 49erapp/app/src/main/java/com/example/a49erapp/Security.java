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

public class Security extends AppCompatActivity {
    int sec_type = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
    }

    public void backtouser(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }

    public void sec_armedstay(View view)
    {
        sec_type = 0;
    }

    public void sec_armedaway(View view)
    {
        sec_type = 1;
    }

    public void sec_disarmed(View view)
    {
        sec_type = 2;
    }

    public void apply_security(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SECURITY, new Response.Listener<String>() {
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
                params.put("armedstatus", Integer.toString(sec_type));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
