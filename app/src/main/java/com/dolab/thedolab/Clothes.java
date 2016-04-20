package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Clothes {
    int ID;
    String Note;
    int ColorID;

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
     public void addToDB(Context context) {
       
    }
}
