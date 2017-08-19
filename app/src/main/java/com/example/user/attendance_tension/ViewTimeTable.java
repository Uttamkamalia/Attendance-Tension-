package com.example.user.attendance_tension;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by acer on 8/16/2017.
 */

public class ViewTimeTable extends Activity {
    Button viewMon_butt ,viewAttend_butt,viewTue_butt,viewWed_butt,viewThur_butt,viewFri_butt,viewSat_butt,viewSun_butt;
    SQLiteDatabase mydb;
    Cursor point;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtimetable);

        mydb = openOrCreateDatabase("Attendance",MODE_PRIVATE,null);
        viewMon_butt = (Button)findViewById(R.id.viewMon_butt);
        viewTue_butt = (Button)findViewById(R.id.viewTue_butt);
        viewWed_butt = (Button)findViewById(R.id.viewWed_butt);
        viewThur_butt = (Button)findViewById(R.id.viewThur_butt);
        viewFri_butt = (Button)findViewById(R.id.viewFri_butt);
        viewSat_butt = (Button)findViewById(R.id.viewSat_butt);
        viewSun_butt = (Button)findViewById(R.id.viewSun_butt);
        viewAttend_butt = (Button)findViewById(R.id.viewAttend_butt);



        viewMon_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from monday",null);

                showData(point,"MONDAY",false);
            }
        });

        viewTue_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from tuesday",null);

                showData(point,"TUESDAY",false);
            }
        });

        viewWed_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from wednesday",null);

                showData(point,"WEDNESDAY",false);
            }
        });

        viewThur_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from thursday",null);

                showData(point,"THURSDAY",false);
            }
        });

        viewFri_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from friday",null);

                showData(point,"FRIDAY",false);
            }
        });

        viewSat_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from saturday",null);

                showData(point,"SATURDAY",false);
            }
        });

        viewSun_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from sunday",null);

                showData(point,"SUNDAY",false);
            }
        });

        viewAttend_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = mydb.rawQuery("Select * from SUBJECTS",null);
                point.moveToFirst();
                showData(point,"ATTENDANCE",true);
            }
        });


    }

    public void showData(Cursor res,String day,boolean attend){
        String opt1,opt2;

        if(attend)
        {
            opt1 = "Subject";
            opt2 = "Presents";
        }
        else
        {
            opt1 = "Time";
            opt2 = "Subject";
        }
        if(res.getCount()==0)
        {
            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT);
            return ;
        }

        res.moveToFirst();
        StringBuffer data = new StringBuffer();

        //data.append(day+"\n");
        for(int i=0;i<res.getCount();i++)
        {
            data.append((i+1)+") "+opt1+" :"+res.getString(0)+"  "+opt2+": "+res.getString(1)+"\n");
            res.moveToNext();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(day);
        builder.setMessage(data);
        builder.show();

    }



}
