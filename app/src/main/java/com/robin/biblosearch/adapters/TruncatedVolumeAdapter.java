package com.robin.biblosearch.adapters;

import android.content.Context;
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

import java.util.List;

public class TruncatedVolumeAdapter extends RecyclerView.Adapter<TruncatedVolumeAdapter.ViewHolder> {
    private final Context context;
    private List<VolumeInfo> items;
    private OncClickLister clickLister;

    public TruncatedVolumeAdapter(List<VolumeInfo> items, Context context, OncClickLister clickLister) {
        this.items = items;
        this.context = context;
        this.clickLister = clickLister;
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
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickLister.onClickVolumeItem(position);
                        }
                    }
            );
        }
    }

    public interface OncClickLister{
        public void onClickVolumeItem(int position);
    }
}