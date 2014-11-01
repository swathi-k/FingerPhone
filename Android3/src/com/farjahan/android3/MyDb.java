/*package com.farjahan.android3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

 *//**
 * A SQLiteOpenhelper to create "zck.db" and
 * table hw2
 *
 */
/*
public class MyDb extends SQLiteOpenHelper {


 *//**
 * constructor
 * @param context
 */
/*
public MyDb(Context context) {
super(context, "zck.db", null, 3);
// activity, database name, , version
}

@Override
public void onCreate(SQLiteDatabase db) {
//create table at first time
String sql="create table if not exists hw2 (name varchar(50), speed integer, high intger, score intger)";
db.execSQL(sql);

}

@Override
public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
// drop and create table
db.execSQL("drop table if exists hw2");
onCreate(db);
}


}*/
