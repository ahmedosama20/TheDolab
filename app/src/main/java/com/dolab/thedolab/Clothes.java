package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Clothes {
    int ID;
    String Note;
    byte[] Image;
    int ColorID;
    public Clothes(String note, byte[] image, int colorID){
        ID = 0;
        Note = note;
        Image = image;
        ColorID = colorID;
    }
}
