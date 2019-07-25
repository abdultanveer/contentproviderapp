package com.example.contentproviderapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uriSms = Uri.parse("content://sms/inbox");
        ListView listView = findViewById(R.id.inboxlistview);

        ContentResolver contentResolver = getContentResolver();
      Cursor dataCursor =
              contentResolver.query(uriSms,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                dataCursor,
                new String[]{"body"},
                new int[]{android.R.id.text1});
        listView.setAdapter(adapter);



    }
}
