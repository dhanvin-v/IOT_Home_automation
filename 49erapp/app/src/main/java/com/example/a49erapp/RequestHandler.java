package com.example.a49erapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandler
{
    private static RequestHandler rhandle;
    private RequestQueue rqueue;
    private Context ctx;

    private RequestHandler(Context context)
    {
        ctx = context;
        rqueue = getRequestQueue();
    }

    public static synchronized RequestHandler getInstance(Context context)
    {
        if (rhandle == null)
        {
            rhandle = new RequestHandler(context);
        }
        return rhandle;
    }

    public RequestQueue getRequestQueue()
    {
        if (rqueue == null)
        {
            rqueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return rqueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}

