package com.example.candystore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "candyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CANDY = "candy";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //build sql create statement

        String sqlCreate = "create table " + TABLE_CANDY + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + PRICE + " real )";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists

        db.execSQL("drop table if exists " + TABLE_CANDY);

        // Re-create tables
        onCreate(db);
    }

    public void insert(Candy candy){

        SQLiteDatabase db = this. getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CANDY;
        sqlInsert += " values( null, '" + candy.getName();
        sqlInsert += "', '" + candy .getPrice() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }
}
