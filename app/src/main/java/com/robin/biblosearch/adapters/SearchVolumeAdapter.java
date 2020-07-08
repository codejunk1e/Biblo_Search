package com.robin.biblosearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robin.biblosearch.R;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

public class SearchVolumeAdapter extends RecyclerView.Adapter<SearchVolumeAdapter.ViewHolder> {
    private final Context context;
    private List<VolumeInfo> items;

    public SearchVolumeAdapter(List<VolumeInfo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_unit, parent, false);
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
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookSubTitile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookTitle = itemView.findViewById(R.id.book_subtitle);

        }

        public void bind(VolumeInfo item, int position) {
            //TODO Implement the bind method wth Item class
        }
    }
}