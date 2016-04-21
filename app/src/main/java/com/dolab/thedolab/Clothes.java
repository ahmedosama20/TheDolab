package com.dolab.thedolab;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Clothes {
    int ID;
    String Note;
    int ColorID;

    public Clothes() {}

    public Clothes(String note, int colorID){
        ID = 0;
        Note = note;
        ColorID = colorID;
    }

    public void setID(int id) {
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public String getNote() {
        return Note;
    }

    public int getColor() {
        return ColorID;
    }

    public String getType() { return ""; }

    public void addToDB(Context context) {}

    public ArrayList<Clothes> getAll(Context c) { return null; }
}
