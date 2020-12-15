package com.example.a49erapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sensors extends AppCompatActivity {
    String res;
    String print = "Floor 1 : Door close , Window close";
    String print1= "Floor 2 : Door close , Window close";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_FlOOR1SENSOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    Iterator<String> keys = jsonObject.keys();
/*
                    while(keys.hasNext()) {
                        String key = keys.next();
                        System.out.println(key);
                        if (jsonObject.get(key) instanceof JSONObject) {
                            // do something with jsonObject here
                        }
                    }
*/                    //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    res = jsonObject.getString("message");
                    JSONObject output = (JSONObject) jsonObject.get("message");
                    System.out.println("Res: "+res);
                    System.out.println("&&"+output.getString("sensor1"));
                    System.out.println("&&"+output.getString("sensor2"));
                    if(res.charAt(11)=='1' && res.charAt(23)=='1'){
                        print = " Floor 1 : Door open , Window open";
                    }
                    else if(res.charAt(11)=='0' && res.charAt(23)=='1'){
                        print = " Floor 1 : Door close , Window open";
                    }
                    if(res.charAt(11)=='1' && res.charAt(23)=='0'){
                        print = " Floor 1 : Door open , Window close";
                    }

                    TextView textView = (TextView) findViewById(R.id.fl1sensor);
                    textView.setText(print);

                }catch(JSONException e){
                    e.printStackTrace();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressDialog.hide();
                        //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);



        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Constants.URL_FlOOR2SENSOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    res = jsonObject.getString("message");
                    if(res.charAt(11)=='1' && res.charAt(23)=='1'){
                        print1 = " Floor 2 : Door open , Window open";
                    }
                    else if(res.charAt(11)=='0' && res.charAt(23)=='1'){
                        print1 = " Floor 2 : Door close , Window open";
                    }
                    else if(res.charAt(11)=='1' && res.charAt(23)=='0'){
                        print1 = " Floor 2 : Door open , Window close";
                    }
                    TextView textView = (TextView) findViewById(R.id.fl2sensor);
                    textView.setText(print1);

                }catch(JSONException e){
                    e.printStackTrace();

                }


            }
        }

                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressDialog.hide();
                        //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                // params.put("username", username);
                //params.put("password",password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest1);




    }

}


