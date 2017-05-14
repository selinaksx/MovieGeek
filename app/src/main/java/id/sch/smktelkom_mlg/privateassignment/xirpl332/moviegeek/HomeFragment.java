package id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.adapter.Adapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.model.ResultRespons;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ArrayList<Results> mList = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(this, mList, getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager grid = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(grid);
        downloadDataSources();
        return view;

    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=6f003edf5d9ddd504ed3d9ffb42872f1&language=en-US&page=1";
        GsonGetRequest<ResultRespons> myRequest = new GsonGetRequest<ResultRespons>
                (url, ResultRespons.class, null, new Response.Listener<ResultRespons>() {
                    @Override
                    public void onResponse(ResultRespons respons) {
                        Log.d("FLOW", "onResponse:" + (new Gson().toJson(respons)));
                        mList.addAll(respons.results);
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);
    }
}
