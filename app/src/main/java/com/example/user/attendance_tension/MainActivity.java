package com.example.user.attendance_tension;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private Cursor resultSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase mydatabase = openOrCreateDatabase("Attendance", Context.MODE_PRIVATE,null);



        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);

        final EditText editText1= (EditText) findViewById(R.id.editText);

        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String subject= editText1.getText().toString();
                mydatabase.execSQL("INSERT INTO SUBJECTS VALUES('"+subject+ "','0');");
                editText1.setText("");

                resultSet = mydatabase.rawQuery("Select * from SUBJECTS",null);
                resultSet.moveToLast();


                System.out.println("subjects:::"+resultSet.getString(0)+"   pres::"+resultSet.getString(1));
                //resultSet.moveToNext();


            }

        });

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                resultSet = mydatabase.rawQuery("Select * from SUBJECTS",null);
                resultSet.moveToFirst();
                String a;
                System.out.println("DIsplay subjects::::");
                for(int i=0;i<resultSet.getCount();i++) {
                    a = resultSet.getString(0);
                    System.out.println(a);
                    resultSet.moveToNext();
                }
            }

        });

        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


             mydatabase.execSQL("Delete from Subjects");

            }

        });

        button5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);

            }

        });







    }


}
