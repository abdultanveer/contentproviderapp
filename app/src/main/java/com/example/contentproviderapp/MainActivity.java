package com.example.contentproviderapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    int LOADER_ID = 007;
    Bundle instructions;
    ListView listView;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoaderManager manager = getSupportLoaderManager();
        manager.initLoader(LOADER_ID,instructions,this);
        setContentView(R.layout.activity_main);
         listView = findViewById(R.id.inboxlistview);

        String[] from =   new String[]{CallLog.Calls.NUMBER};//name of column in db
        int[] to =  new int[]{android.R.id.text1};
        int layout = android.R.layout.simple_list_item_1;
         adapter = new SimpleCursorAdapter(this,layout,null,from,to);
        listView.setAdapter(adapter);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Uri allCalls = Uri.parse("content://call_log/calls");
        return new CursorLoader(this,
                allCalls,//warehousse
                null,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            adapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
