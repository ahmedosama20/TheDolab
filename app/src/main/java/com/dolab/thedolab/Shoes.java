package com.dolab.thedolab;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Shoes extends Clothes {
    ShoeTypes Type;
    public Shoes()
    {
        
    }
    public Shoes(ShoeTypes type ,String note, int colorID){
        super(note, colorID);
        Type = type;
    }

    @Override
    public String getType() {
        return Type.name();
    }

    @Override
    public ArrayList< Clothes > getAll(Context context) {
         DBHandler myHandler = new DBHandler(context);
         return myHandler.getAllShoes();
     }

    @Override
    public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addShoes(this);
    }
}
