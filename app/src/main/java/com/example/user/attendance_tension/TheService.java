package com.example.user.attendance_tension;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by acer on 8/7/2017.
 */

public class TheService extends Service {


   // public static Map<String,Integer> m1 =new HashMap<String,Integer> ();
   // SQLiteDatabase mydatabase1 = Activity2.mydatabase;
    Cursor res;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(TheService.this,"Service Destroyed",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        SQLiteDatabase mydatabase1 = openOrCreateDatabase("Attendance", Context.MODE_PRIVATE,null);
        Toast.makeText(TheService.this,"Service started",Toast.LENGTH_SHORT).show();
        Calendar calender = Calendar.getInstance();

        //creating a map for subject variables
        /*
        for(int i=0;i<Activity2.subjectList.size();i++)
        {
            m1.put(Activity2.subjectList.get(i),new Integer(0));
            Toast.makeText(TheService.this,"Sub::"+Activity2.subjectList.get(i)+"   time::"+m1.get(Activity2.subjectList.get(i)),Toast.LENGTH_SHORT).show();
        }
        */

        res = mydatabase1.rawQuery("Select * from monday",null);
        res.moveToFirst();
        Toast.makeText(TheService.this,"iN Service   max "+res.getCount(),Toast.LENGTH_SHORT).show();

        for(int i=0;i<res.getCount();i++)
        {
            Toast.makeText(TheService.this,"iN Service    "+i,Toast.LENGTH_SHORT).show();

            Toast.makeText(TheService.this,"iN Service    "+res.getString(0)+"    "+res.getString(1),Toast.LENGTH_SHORT).show();
            calender.set(calender.HOUR_OF_DAY,18);
            calender.set(calender.MINUTE,res.getInt(0));

            Intent noti_intent = new Intent(this,Notification_reciever.class);
            noti_intent.putExtra("time",res.getInt(0));
            noti_intent.putExtra("subject",res.getString(1));
            PendingIntent noti_pi = PendingIntent.getBroadcast(getApplicationContext(),i,noti_intent,PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),alarmManager.INTERVAL_FIFTEEN_MINUTES,noti_pi);

            res.moveToNext();
            Toast.makeText(TheService.this,"alarm set",Toast.LENGTH_SHORT).show();
        }


        /*
        calender.set(calender.HOUR_OF_DAY,11);
        calender.set(calender.MINUTE,57);
        Intent noti_intent = new Intent(this,Notification_reciever.class);
        noti_intent.putExtra("time",res.getString(0));
        noti_intent.putExtra("subject",res.getString(1));
        PendingIntent noti_pi = PendingIntent.getBroadcast(getApplicationContext(),100,noti_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),AlarmManager.INTERVAL_DAY,noti_pi);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),alarmManager.INTERVAL_DAY*7,noti_pi);

        Toast.makeText(TheService.this,"alarm set",Toast.LENGTH_SHORT).show();
        //calender.setWeekData()
        */


        return START_STICKY;
    }



}
