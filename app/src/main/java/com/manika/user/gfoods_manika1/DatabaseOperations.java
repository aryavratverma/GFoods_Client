package com.manika.user.gfoods_manika1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 29-09-2018.
 */



    /**
     * Created by user on 07-07-2018.
     */

    public class DatabaseOperations extends SQLiteOpenHelper {
        public static final int database_version=1;
        public String create_query="create table "+CartTable.TableInfo.table_name+"("+ CartTable.TableInfo.user_name+" text,"+
                CartTable.TableInfo.user_phone+" text,"+
                CartTable.TableInfo.user_items+" text,"+
        CartTable.TableInfo.user_qty+" text,"+
        CartTable.TableInfo.user_price+" text,"+
                CartTable.TableInfo.user_total+" text,"+
        CartTable.TableInfo.user_date+ " text);";

        public DatabaseOperations(Context context) {  //to create database
            super(context, CartTable.TableInfo.database_name,null,database_version);
            Log.d("Database OPerations","Database created");
        }

        @Override
        public void onCreate(SQLiteDatabase sdb) {   // to create table

            sdb.execSQL(create_query);
            Log.d("Database Operations","Table created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ CartTable.TableInfo.table_name);
            onCreate(db);
        }
        public void putInformation(DatabaseOperations dop,String name,String phone,String items,String qty,String price,String total,String date)
        {
            SQLiteDatabase SQ=dop.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(CartTable.TableInfo.user_name,name);
            cv.put(CartTable.TableInfo.user_phone,phone);
            cv.put(CartTable.TableInfo.user_items,items);
            cv.put(CartTable.TableInfo.user_qty,qty);
            cv.put(CartTable.TableInfo.user_price,price);
            cv.put(CartTable.TableInfo.user_total,total);
            cv.put(CartTable.TableInfo.user_date,date);
            //  cv.put(Table.TableInfo.user_phone,phone);
            long k=SQ.insert(CartTable.TableInfo.table_name,null,cv);
            Log.d("Database insertion","one row inserted");

        }


       /* public void updateInformation(DatabaseOperations dop,String name,String phone,String pass)
        {
            SQLiteDatabase SQ=dop.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(Table.TableInfo.user_name,name);
            cv.put(Table.TableInfo.user_pass,pass);
            //  cv.put(Table.TableInfo.user_phone,phone);
            long k=SQ.update(Table.TableInfo.table_name,cv, Table.TableInfo.user_name+"=?",new String[]{name});
            Log.d("Database insertion","one row inserted");

        }
        public void deleteRecord(DatabaseOperations dob,String name)
        {
            SQLiteDatabase sq=getWritableDatabase();
            sq.delete(Table.TableInfo.table_name, Table.TableInfo.user_name+"=?",new String[]{name});
            sq.close();
        }*/
        public Cursor getInformation(DatabaseOperations dob)
        {
            SQLiteDatabase SQ=dob.getReadableDatabase();
            String[] columns={CartTable.TableInfo.user_name, CartTable.TableInfo.user_phone, CartTable.TableInfo.user_items, CartTable.TableInfo.user_qty, CartTable.TableInfo.user_price, CartTable.TableInfo.user_total, CartTable.TableInfo.user_date,};  //columns daal do
            Cursor cr=SQ.query(CartTable.TableInfo.table_name,columns,null,null,null,null,null);
            return cr;
        }
    /*
    public void insertRecord(StudentRecord sr)
    {
       SQLiteDatabase database= getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Table.TableInfo.user_name,sr.getName());
        cv.put(Table.TableInfo.user_pass,sr.getPassword());
        long k=database.insert(Table.TableInfo.table_name,null,cv);
        Log.d("Database insertion","one row inserted");

    }*/

        //for single record retrieval we have to call query method and provide some query-parameters
        //the parameters are 1) String Table 2) String[] columns 3)string selection 4) String[] selection 5) group by
        //6) having by 7) orederby

     /*   public Cursor getSingleRecord(DatabaseOperations dob,String name) {

            SQLiteDatabase db = getReadableDatabase();
            // StudentRecord record = null;
            Cursor cr = db.query(Table.TableInfo.table_name, new String[]{Table.TableInfo.user_name, Table.TableInfo.user_pass}, Table.TableInfo.user_name + "=?", new String[]{name}, null, null, null);

            return cr;
        }*/


}
