package id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.ComingSoonFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.HomeFragment;


public class VolleySingleton {
    private static volatile VolleySingleton mInstance;
    private static HomeFragment mCtx;
    private static ComingSoonFragment mCtx2;

    private RequestQueue mRequestQueue;
    
    /*private VolleySingleton(Context context)
    {
        if (mInstance != null)
        {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }*/

    private VolleySingleton(HomeFragment context) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    private VolleySingleton(ComingSoonFragment context1) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx2 = context1;
        mRequestQueue = getRequestQueue();
    }


    public static VolleySingleton getInstance(HomeFragment context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public static VolleySingleton getInstance(ComingSoonFragment context1) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context1);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getActivity());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}