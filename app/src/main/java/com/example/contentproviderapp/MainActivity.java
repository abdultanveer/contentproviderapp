package com.example.contentproviderapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
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
        Uri allCalls = Uri.parse("content://call_log/calls");

        ListView listView = findViewById(R.id.inboxlistview);
        String[] from =   new String[]{CallLog.Calls.NUMBER};//name of column in db
        int[] to =  new int[]{android.R.id.text1};
        int layout = android.R.layout.simple_list_item_1;



        ContentResolver contentResolver = getContentResolver();
      Cursor dataCursor =
              contentResolver.query(allCalls,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,layout,dataCursor,from,to);
        listView.setAdapter(adapter);



    }
}
