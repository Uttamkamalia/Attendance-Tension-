package com.example.user.attendance_tension;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private Cursor resultSet;
    private String day, time, subject;
    public static SQLiteDatabase mydatabase;
    public static ArrayList<String> subjectList;
     //SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mydatabase = openOrCreateDatabase("Attendance",MODE_PRIVATE,null);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.week_days,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner4);
        adapter= ArrayAdapter.createFromResource(this,R.array.time,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);


        resultSet = mydatabase.rawQuery("Select * from SUBJECTS",null);
        resultSet.moveToFirst();
        subjectList= new ArrayList<>();

        for(int i=0;i<resultSet.getCount();i++)
        {
            subjectList.add(i,resultSet.getString(0));
            resultSet.moveToNext();
        }

        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner5);

        ArrayAdapter<String> adpter2= new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item,subjectList);
        adpter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adpter2);

        //mydatabase.execSQL("CREATE TABLE IF NOT EXISTS subjects(Day VARCHAR,'9' VARCHAR,'10' VARCHAR,'11' VARCHAR,'12' VARCHAR,'2' VARCHAR,'3' VARCHAR,'4' VARCHAR);");

        Button insert = (Button) findViewById(R.id.ins_butt);
        Button next = (Button) findViewById(R.id.next2_butt);



       // mydatabase.execSQL("INSERT INTO " + "monday" + " VALUES('"+"56"+"', '"+"math"+"');");
        //mydatabase.execSQL("INSERT INTO " + "monday" + " VALUES('"+"57"+"', '"+"sci"+"');");
        //mydatabase.execSQL("INSERT INTO " + "monday" + " VALUES('"+"59"+"', '"+"phy"+"');");

        insert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Cursor resultset;
                day= new String(spinner1.getSelectedItem().toString());
                time=new String(spinner2.getSelectedItem().toString());
                subject= new String(spinner3.getSelectedItem().toString());

                mydatabase.execSQL("INSERT INTO " + day + " VALUES('"+time+"', '"+subject+"');");


                resultset = mydatabase.rawQuery("SELECT * FROM "+"monday"+";", null);
                resultset.moveToLast();

                System.out.println(resultset.getString(0) + " AT THIS TIME INSERTED :: " + resultset.getString(1));
            }

        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("before Service");
                Intent go_service = new Intent(Activity2.this,TheService.class);
                startService(go_service);
                System.out.println("after Service");
            }
        });
        //Intent go_service = new Intent(this,TheService.class);
        //startService(go_service);

    }
}
