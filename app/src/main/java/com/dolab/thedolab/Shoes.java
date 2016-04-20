package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Shoes extends Clothes {
    ShoeTypes Type;
    public Shoes(ShoeTypes type ,String note, byte[] image, int colorID){
        super(note, image, colorID);
        Type = type;
    }
}
