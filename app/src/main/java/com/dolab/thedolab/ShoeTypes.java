package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public enum ShoeTypes {
    SNEAKERS, FLIPFLOP, SANDAL, CLASICSHOE, BOOT;
    public static ShoeTypes toEnum(int x)
    {
        switch(x)
        {
            case 0 :
                return SNEAKERS;
            case 1 :
                return  FLIPFLOP;
            case 2 :
                return SANDAL;
            case 3 :
                return CLASICSHOE;
            case 4 :
                return BOOT;
            default:
                return null;
        }
    }
}
