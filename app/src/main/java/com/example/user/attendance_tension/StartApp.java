package com.example.user.attendance_tension;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by acer on 8/16/2017.
 */

public class StartApp extends Activity
{
    Button setTT_butt,view_TT ;
    SQLiteDatabase mydatabase ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        mydatabase = openOrCreateDatabase("Attendance", MODE_PRIVATE,null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS monday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tuesday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS wednesday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS thursday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS friday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS saturday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS sunday(time Integer, subject VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS SUBJECTS(Subjects VARCHAR,Present Integer);");


        setTT_butt = (Button)findViewById(R.id.setTT_butt);
        view_TT = (Button)findViewById(R.id.viewTT_butt);

        setTT_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartApp.this,MainActivity.class));
            }
        });

        view_TT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartApp.this,ViewTimeTable.class));
            }
        });
    }
}
