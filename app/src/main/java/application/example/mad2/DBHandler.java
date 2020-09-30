package application.example.mad2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.text.PrecomputedText;
import android.util.Log;
import android.widget.EditText;import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "UserInfo1.db";
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "subject";





        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

   EditText edtSubject;


    @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                sqLiteDatabase.execSQL(
                        "create table users " +
                                "(id integer primary key, subject TEXT unique)" //
                );
            }catch (Exception e){
                System.out.println("Database created successfully" +e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        public long addInfo(String subject) {


            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME,subject);
                //


            long newRowId = sqLiteDatabase.insert(TABLE_NAME, null, values);
            return newRowId;

        }

        public List readAllInfo(String req){

            SQLiteDatabase sqLiteDatabase = getReadableDatabase();

            String[] projection = {

                    UsersMaster.Users._ID,
                    COLUMN_NAME,

            };
            String sortOrder = COLUMN_NAME + " DESC";

            Cursor cursor = sqLiteDatabase.query(
                    TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder

            );

            List subjects = new ArrayList<>();

            while(cursor.moveToNext()) {

                String subject = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                subjects.add(subject);
            }
                cursor.close();
                //Log.i(TAG, "readAllInfo: " + subjects);


            return subjects;


        }

        public void deleteInfo(String subject){

            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String selection = COLUMN_NAME + " LIKE ?";
            String[] selectionArgs = { subject };
            sqLiteDatabase.delete(TABLE_NAME, selection, selectionArgs);
        }

        public int updateInfo(String subject){

            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, subject);
            String selection  = COLUMN_NAME + " LIKE ?";
            String[] selectionArgs = { subject };

            int count = sqLiteDatabase.update(
                    TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
                    );


            return count;
        }

        public Cursor getListContents(){

            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor data = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);
            return data;

        }



        public int getcount() {
            String query = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);
            return cursor.getCount();

        }


    public Integer deleteInfo1(String id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"id = ?",new String[] { id });
    }

    public boolean updateInfo1(String id,String subject){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, subject);

        sqLiteDatabase.update(TABLE_NAME, values, "id =?",new String[] { id });

        return true;
    }

    //Get user details according to the name



    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}


