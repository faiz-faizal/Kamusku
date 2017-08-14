package com.maxibi.testing;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

    public ArrayList<Word> getQuotes(){
        ArrayList<String> listA = new ArrayList<String>();
        ArrayList<String> listB = new ArrayList<String>();
        ArrayList<Word> listC = new ArrayList<Word>();


        String query1 = "SELECT bm FROM quotes ";
        String query2 = "SELECT bi FROM quotes ";

        Cursor cursor1 = sqlDatabase.rawQuery(query1, null);
        Cursor cursor2 = sqlDatabase.rawQuery(query2, null);


        cursor1.moveToFirst();
        while(!cursor1.isAfterLast()){
            listA.add(cursor1.getString(0));
            cursor1.moveToNext();
        }
        cursor1.close();

        cursor2.moveToFirst();
        while(!cursor2.isAfterLast()){
            listB.add(cursor2.getString(0));
            cursor2.moveToNext();
        }
        cursor2.close();

        for ( int i = 0; i < listA.size(); i++)
        {
            Word wordDefinition = new Word (listA.get(i),listB.get(i));
            listC.add(wordDefinition);
        }
        return listC;
    }

}
