package com.robin.biblosearch.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.robin.biblosearch.R;
import com.robin.biblosearch.datasource.local.BookDao;
import com.robin.biblosearch.datasource.local.Database;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

import static com.robin.biblosearch.views.MainActivity.BOOK_EXTRA_KEY;


public class GridWidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext());
    }
}

class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private BookDao bookDao;
    private List<VolumeInfo> allFavs;
    Context mContext;

    public GridRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
        Database db;
        db = Database.getInstance(mContext.getApplicationContext());
        bookDao = db.bookDao();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        allFavs = bookDao.getAllFavouritesForWidget();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (allFavs == null) return 0;

        else if(allFavs.size() < 4){
            return allFavs.size();

        }else {
            return allFavs.size();
        }
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_list);
            try {

                Bitmap bitmap = Glide.with(mContext)
                        .asBitmap()
                        .load(allFavs.get(position).getThumbnail())
                        .error(R.drawable.default_image)
                        .submit(128, 192)
                        .get();

                views.setImageViewBitmap(R.id.favourite_widget_image, bitmap);

                Bundle extras = new Bundle();
                extras.putParcelable(BOOK_EXTRA_KEY, allFavs.get(position));
                Intent fillInIntent = new Intent();
                fillInIntent.putExtras(extras);
                views.setOnClickFillInIntent(R.id.favourite_widget_image, fillInIntent);


            } catch (Exception e) {
                e.printStackTrace();
            }
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
