package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class ClothesFactory {
    public Clothes CreateClothes(int id, String note, byte[] image, int colorID) {
        switch (id) {
            case 0:
                return new Top(note,  colorID);
            case 1:
                return new Bottom(note,  colorID);
            case 2:
                return new Shoes(note,  colorID);
            default:
                return null;
        }
    }
}
