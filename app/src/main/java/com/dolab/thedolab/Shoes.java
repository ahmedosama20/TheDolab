package com.dolab.thedolab;

import android.content.Context;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Shoes extends Clothes {
    ShoeTypes Type;
    public Shoes(ShoeTypes type ,String note, int colorID){
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
       return myHandler.getAllShoes(this);
=======

    public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addShoes(this);
>>>>>>> Stashed changes
    }
}
