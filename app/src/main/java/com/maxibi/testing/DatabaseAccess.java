package com.maxibi.testing;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/14/2017.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper opHelper;
    private SQLiteDatabase sqlDatabase;
    private static DatabaseAccess instance;


    private DatabaseAccess(Context context){
        this.opHelper = new DatabaseOpenHelper(context);
    }


    //getter
    public static DatabaseAccess getInstance (Context context){
        if( instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.sqlDatabase = opHelper.getWritableDatabase();
    }

    public void close(){
        if( sqlDatabase != null){
            this.sqlDatabase.close();
        }
    }

    public List<String> getQuotes(){
        List<String> list = new ArrayList<>();
        String query = "SELECT * FROM quotes ";
        Cursor cursor = sqlDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
