package id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.ComingSoonFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.HomeFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.ScrollingActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.model.Results;

/**
 * Created by Shelin on 13/05/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.mViewHolder> {
    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> mList;
    HomeFragment homeFragment;
    ComingSoonFragment comingSoonFragment;
    Context context;
    private int lastposition = -1;

    public Adapter(HomeFragment homeFragment, ArrayList<Results> mList, Context context) {
        this.mList = mList;
        this.homeFragment = homeFragment;
        this.context = context;
    }

    public Adapter(ComingSoonFragment comingSoonFragment, ArrayList<Results> adapter, Context context) {
        this.mList = adapter;
        this.comingSoonFragment = comingSoonFragment;
        this.context = context;
    }

    @Override
    public Adapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        mViewHolder vh = new mViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(Adapter.mViewHolder holder, int position) {
        final Results result = mList.get(position);
        holder.movTitle.setText(result.title);
        image = url + result.poster_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_error)
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScrollingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title", result.title);
                intent.putExtra("poster_path", result.backdrop_path);
                intent.putExtra("description", result.overview);
                intent.putExtra("release", result.release_date);
                intent.putExtra("original", result.original_language);
                intent.putExtra("popularity", result.popularity);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        TextView movTitle;
        ImageView imageView;
        CardView cardView;

        public mViewHolder(View v) {
            super(v);
            movTitle = (TextView) v.findViewById(R.id.textViewTitle);
            imageView = (ImageView) v.findViewById(R.id.iv_image);
            cardView = (CardView) v.findViewById(R.id.Card);
        }
    }
}
