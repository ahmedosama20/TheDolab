package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Bottom extends Clothes {
    BottomTypes Type;
    public Bottom(BottomTypes type, String note, int colorID){
        super(note, colorID);
        Type = type;
    }
}
