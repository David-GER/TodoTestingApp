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
    private static final String TAG = ImageAdapter.class.getSimpleName();
    private List<ImageModel> list;
    private ImageAdapterListener listener;

    public ImageAdapter(ImageAdapterListener listener) {
        this.listener = listener;
        this.list = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        ViewHolder vh = new ViewHolder(v, listener);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImageModel model = list.get(position);

//        holder.text.setText(model.getName());

        Picasso.with(holder.itemView.getContext())
                .load(model.getPreview())
                .networkPolicy(NetworkPolicy.NO_STORE)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(List<ImageModel> items) {
        this.list = items;

        notifyDataSetChanged();
    }

    public List<ImageModel> getList() {
        return list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text;
        public ImageView image;
        private ImageAdapterListener listener;

        public ViewHolder(View view, final ImageAdapterListener listener) {
            super(view);

//            text = (TextView) view.findViewById(R.id.text);
            image = (ImageView) view.findViewById(R.id.image);
            this.listener = listener;

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}
