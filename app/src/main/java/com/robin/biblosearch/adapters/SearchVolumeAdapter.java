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
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

public class SearchVolumeAdapter extends RecyclerView.Adapter<SearchVolumeAdapter.ViewHolder> {
    private final Context context;
    private List<Item> items;
    private OncClickLister lister;

    public SearchVolumeAdapter(List<Item> items, Context context, OncClickLister lister) {
        this.items = items;
        this.context = context;
        this.lister = lister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_unit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookSubTitile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.search_book_image);
            bookTitle = itemView.findViewById(R.id.search_book_title);
            bookSubTitile = itemView.findViewById(R.id.search_book_subtitle);

        }

        public void bind(Item item, int position) {
            if (item.getVolumeInfo().getImageLinks() != null) {
                Glide.with(context)
                        .load(item.getVolumeInfo().getImageLinks().getSmallThumbnail())
                        .override(128,192)
                        .error(R.drawable.default_image)
                        .into(bookImage);
            }
            bookTitle.setText(item.getVolumeInfo().getTitle());
            bookSubTitile.setText(item.getVolumeInfo().getSubtitle());

            itemView.setOnClickListener(v -> {
                lister.onClickSearchItem(position);
            });
        }
    }
    public interface OncClickLister{
        public void onClickSearchItem(int position);
    }
}