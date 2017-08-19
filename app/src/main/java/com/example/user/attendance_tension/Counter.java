package com.example.user.attendance_tension;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by acer on 8/7/2017.
 */
//db.execSQL("UPDATE DB_TABLE SET YOUR_COLUMN='newValue' WHERE id=6 ");
public class Counter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        SQLiteDatabase mydb = context.openOrCreateDatabase("Attendance",MODE_PRIVATE,null);

        Bundle exData = intent.getExtras();
        String subject = exData.getString("subject");
        Cursor res = mydb.rawQuery("SELECT * FROM SUBJECTS WHERE Subjects='"+subject+"';",null);
        res.moveToFirst();
        int updt = res.getInt(1)+1;
        //TheService.m1.put(subject,new Integer(TheService.m1.get(subject)+1));

        mydb.execSQL("UPDATE SUBJECTS SET Present='"+updt+"' WHERE Subjects='"+subject+"';");

       // System.out.print("in counter::"+subject+" -->"+TheService.m1.get(subject));
        res = mydb.rawQuery("SELECT * FROM SUBJECTS WHERE Subjects='"+subject+"';",null);
        res.moveToFirst();
        updt = res.getInt(1);

        Toast.makeText(context,"in counter::"+subject+" -->"+updt,Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,"in counter::"+subject+" -->"+TheService.m1.get(subject).intValue(),Toast.LENGTH_SHORT).show();
    }
}
