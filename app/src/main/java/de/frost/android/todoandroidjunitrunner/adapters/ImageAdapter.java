package de.frost.android.todoandroidjunitrunner.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.frost.android.todoandroidjunitrunner.R;
import de.frost.android.todoandroidjunitrunner.model.ImageModel;

/**
 * Created by david on 24.02.17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<ImageModel> list;

    public ImageAdapter() {
        this.list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);


        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageModel model = list.get(position);

        holder.text.setText(model.getName());

        Picasso.with(holder.itemView.getContext())
                .load(model.getUrl())
                .networkPolicy(NetworkPolicy.NO_STORE)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);

            text = (TextView) view.findViewById(R.id.text);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
