package com.dolab.thedolab;

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
}
