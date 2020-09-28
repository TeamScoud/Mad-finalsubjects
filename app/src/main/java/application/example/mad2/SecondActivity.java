package application.example.mad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    EditText e3;
    DBHandler myDB;
    Button button3,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        e3 = findViewById(R.id.e3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
/*
        ListView listView = (ListView) findViewById(R.id.listView);

        myDB = new DBHandler(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0){

            Toast.makeText(SecondActivity.this," Does Not Successfully View",Toast.LENGTH_SHORT).show();
        }else{

            while (data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);

                Toast.makeText(SecondActivity.this," All Subjects View Successfully",Toast.LENGTH_SHORT).show();
            }
        }
    }

      */
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = e3.getText().toString();
                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("UserInfo1.db", Context.MODE_PRIVATE,null);

                Cursor c = sqLiteDatabase.rawQuery("select * from users where subject='"+n+"'", null);

                if (c.getCount() == 0){

                    Toast.makeText(getApplicationContext(),"Data not searched", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()){
                    buffer.append("id :"+ c.getString(0)+"\n");
                    buffer.append("subject :"+ c.getString(1)+"\n\n");


                }


                Toast.makeText(getApplicationContext(),"Results : \n "+buffer.toString(),Toast.LENGTH_SHORT).show();





            }
        });




    }
}