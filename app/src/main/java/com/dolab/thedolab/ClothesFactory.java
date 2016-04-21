package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class ClothesFactory {
    public Clothes CreateClothes(int id, int type, String note, int colorID) {
        switch (id) {
            case 0:
                return new Top(TopTypes.toEnum(type), String note, int colorID);
            case 1:
                return new Bottom(BottomTypes.toEnum(type), String note, int colorID);
            case 2:
                return new Shoes(ShoesTypes.toEnum(type), String note, int colorID);
            default:
                return null;
        }
    }
}
