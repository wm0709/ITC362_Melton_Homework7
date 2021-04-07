package com.example.todo_list_1;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todoDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO = "todo";
    private static final String ID = "id";
    private static final String TODO_ITEM = "todo_item";
    private static final String TODO_DEADLINE = "todo_deadline";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //build sql create statement

        String sqlCreate = "create table " + TABLE_TODO + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TODO_ITEM;
        sqlCreate += " text, " + TODO_DEADLINE + " text)";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists

        db.execSQL("drop table if exists " + TABLE_TODO);

        // Re-create tables
        onCreate(db);
    }

    public ArrayList<Todo> selectAll() {
        String sqlQuery = "select * from " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Todo> todos = new ArrayList<>();
        while(cursor.moveToNext()){
            Todo currentTodo
                    = new Todo(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            todos.add(currentTodo);
        }
        db.close();
        return todos;
    }

    public void insert(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TODO;
        sqlInsert += " values( null, '" + todo.getTodoItem();
        sqlInsert += "', '" + todo.getTodoDeadline() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TODO;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }
}
