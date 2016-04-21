package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public enum TopTypes {
    TSHIRT, SWEATSHIRT, JACKET, SHIRT, BLOUSE, CARDIGAN;
     public static TopTypes toEnum(int x)
    {
        switch(x)
        {
            case 0 :
                return TSHIRT;
            case 1:
                return SWEATSHIRT;
            case 2:
                return JACKET;
            case 3:
                return SHIRT;
            case 4:
                return BLOUSE;
            case 5:
                return CARDIGAN;
            default:
                return null;
        }
        
        
    }
}
