package com.dolab.thedolab;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Bottom extends Clothes {
    BottomTypes Type;
    public Bottom(BottomTypes type, String note, int colorID){
        super(note, colorID);
        Type = type;
    }

    public String getType() {
        return Type.name();
    }

     public ArrayList< Bottom > getAll(Context context) {
         DBHandler myHandler = new DBHandler(context);
         return myHandler.getAllBottoms();
     }

    public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addBottom(this);
    }
}
