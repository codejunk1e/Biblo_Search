package com.robin.biblosearch.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robin.biblosearch.R;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.views.BookDetailsActivity;

import java.util.List;

import static com.robin.biblosearch.views.MainActivity.BOOK_EXTRA_KEY;


public class TruncatedVolumeAdapter extends RecyclerView.Adapter<TruncatedVolumeAdapter.ViewHolder> {
    private final Context context;
    private List<VolumeInfo> items;

    public TruncatedVolumeAdapter(List<VolumeInfo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unit, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VolumeInfo item = items.get(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }

        if (items.size() < 4){
            return items.size();
        }else {
            return 4;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.recents_fav_book_image);
            bookTitle = itemView.findViewById(R.id.recents_fav_book_title);
        }

        public void bind(VolumeInfo item, int position) {
            Glide.with(context)
                    .load(item.getThumbnail())
                    .override(128,192)
                    .error(R.drawable.default_image)
                    .into(bookImage);

            bookTitle.setText(item.getTitle());
            itemView.setOnClickListener(
                    v -> {
                        Intent intent = new Intent(context, BookDetailsActivity.class);
                        intent.putExtra(BOOK_EXTRA_KEY, items.get(position));
                        context.startActivity(intent);                        }
            );
        }
    }

    public void update(List<VolumeInfo> items){
        this.items = items;
        notifyDataSetChanged();
    }
}