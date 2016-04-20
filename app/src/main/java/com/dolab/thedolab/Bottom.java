package com.dolab.thedolab;

import android.content.Context;

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
<<<<<<< Updated upstream
     public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addTop(this);
    }
     public ArrayList< Clothes > getAll(Context context)
    {
        DBHandler myHandler = new DBHandler(context);
       return myHandler.getAllBottoms(this);
=======

    public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addBottom(this);
>>>>>>> Stashed changes
    }
}
