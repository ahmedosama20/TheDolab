package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Bottom extends Clothes {
    BottomTypes Type;
    public Bottom(BottomTypes type, String note, byte[] image, int colorID){
        super(note, image, colorID);
        Type = type;
    }
}
