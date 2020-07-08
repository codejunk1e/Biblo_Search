package com.robin.biblosearch.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.robin.biblosearch.R;

public class FavBookService extends IntentService {
    public static final String ACTION_UPDATE_WIDGETS = "com.robin.biblosearch.widget.ACTION_UPDATE_WIDGETS";


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FavBookService(String name) {
        super(name);
    }

    public FavBookService() {
        super("FavBookService");
    }

    public static void startActionUpdateWidgets(Context context) {
        Intent intent = new Intent(context, FavBookService.class);
        intent.setAction(ACTION_UPDATE_WIDGETS);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_WIDGETS.equals(action)) {
                handleActionUpdateWidgets();
            }
        }
    }

    private void handleActionUpdateWidgets() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, FavBooksWidget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);
        FavBooksWidget.updateAppWidgets(this, appWidgetManager,appWidgetIds);
    }
}

