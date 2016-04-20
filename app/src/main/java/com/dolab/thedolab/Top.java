package com.dolab.thedolab;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Top extends Clothes {
    TopTypes Type;
    public Top(TopTypes type, String note, int colorID){
        super(note, colorID);
        Type = type;
    }

    public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addTop(this);
    }
}
