package com.robin.biblosearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robin.biblosearch.R;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

public class SearchVolumeAdapter extends ListAdapter<Item, SearchVolumeAdapter.ViewHolder>{
    private final Context context;
    private OncClickLister lister;

    public SearchVolumeAdapter(Context context, OncClickLister lister) {
        super(DIFF_CALLBACK);
        this.lister = lister;
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(Item oldItem, Item newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Item oldItem, Item newItem) {
            return oldItem.getVolumeInfo().getTitle().equals(newItem.getVolumeInfo().getTitle());
        }
    };



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_unit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = getItem(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        if (getCurrentList() == null) {
            return 0;
        }
        return getCurrentList().size();
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

    public void updateList(List<Item>  list){
        submitList(list);
    }
}