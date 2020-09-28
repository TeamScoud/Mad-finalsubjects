package application.example.mad2;

import android.provider.BaseColumns;

public class UsersMaster {

        UsersMaster(){}

        public static class Users implements BaseColumns {

            public static final String TABLE_NAME = "users"; //change table name

            public static final String COLUMN_NAME = "subject";



        }
}
