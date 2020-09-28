package application.example.mad2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {




    DBHandler myDB;
    EditText edtSubject,e1;
    //
    Button btn,btn1,btn3,btn4,button,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSubject = findViewById(R.id.edtSubject);
        e1 = findViewById(R.id.e1);
        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn2 = findViewById(R.id.btn2);

        //
        myDB = new DBHandler(this);
        button = findViewById(R.id.button);


        deleteInfo1();
        updateData1();
        ViewAll();



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
    public void addData(View view){

        DBHandler dbHandler = new DBHandler(this);
        long val = dbHandler.addInfo(edtSubject.getText().toString());

         String Name = edtSubject.getText().toString();

        if(Name.length()==0){

            edtSubject.requestFocus();
            edtSubject.setError("FIELD CANNOT BE EMPTY");

        }else if(!Name.matches("[a-zA-Z ]+")){

            edtSubject.requestFocus();
            edtSubject.setError("Enter Only Alphabetical Character");

        }else if(val>0){

            Toast.makeText(this,"Subject Added Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Subject Not Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public void viewAll(View view){



        //Intent intent = new Intent(this,SecondActivity.class);
        DBHandler dbHandler = new DBHandler(this);
        List unames = dbHandler.readAllInfo("users");
        //intent.putExtra("n1", unames.toString());

        Toast.makeText(this, unames.toString(), Toast.LENGTH_SHORT).show();
        //startActivity(intent);
    }

    public void deleteData(View view) {

        DBHandler dbHandler = new DBHandler(this);
        //validation
        String Name = edtSubject.getText().toString();

        if (Name.length() == 0) {

            edtSubject.requestFocus();
            edtSubject.setError("FIELD CANNOT BE EMPTY");

        } else {
            dbHandler.deleteInfo(edtSubject.getText().toString());


            Toast.makeText(this, edtSubject.getText().toString() + " Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(View view){

        DBHandler dbHandler = new DBHandler(this);

        int val = dbHandler.updateInfo(edtSubject.getText().toString());

        if(val>0){
            Toast.makeText(this," Data update Successfully",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this," Data not update Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void getcount(View view){
        DBHandler dbHandler = new DBHandler(this);

       int y = dbHandler.getcount();
       if (y>0){

           Toast.makeText(this,+ (dbHandler.getcount()) + " Subjects Successfully Entered",Toast.LENGTH_SHORT).show();
       }else{

           Toast.makeText(this," Data not count Successfully",Toast.LENGTH_SHORT).show();
       }

    }


    public void deleteInfo1() {

        final DBHandler dbHandler = new DBHandler(this);
        //validation

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = dbHandler.deleteInfo1(e1.getText().toString());

                if (deleteRows > 0){


                    Toast.makeText(MainActivity.this, edtSubject.getText().toString() + " Deleted Successfully", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(MainActivity.this, edtSubject.getText().toString() + " Deleted Not Successfully", Toast.LENGTH_SHORT).show();


                }
            }
        });



        Toast.makeText(this, e1.getText().toString() + " Deleted Successfully", Toast.LENGTH_SHORT).show();
    }


    public void updateData1(){

        final DBHandler dbHandler = new DBHandler(this);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isUpdate = dbHandler.updateInfo1(e1.getText().toString(),edtSubject.getText().toString());

                if (isUpdate == true){
                    Toast.makeText(MainActivity.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ViewAll(){

        final DBHandler dbHandler = new DBHandler(this);
        button.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {




                                           Cursor res = dbHandler.getAllData();
                                           if (res.getCount() == 0){

                                               showMessage("Error","Nothing Found");
                                               return;
                                           }

                                           StringBuffer buffer = new StringBuffer();
                                           while (res.moveToNext()){
                                               buffer.append("id :"+ res.getString(0)+"\n");
                                               buffer.append("subject :"+ res.getString(1)+"\n\n");


                                           }

                                           showMessage("Data",buffer.toString());
                                       }
                                   }
        );

    }
    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }




}