package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public enum BottomTypes {
    SHORT, JEANS, SKIRT, PANTS;
    public static BottomTypes toEnum(int x)
    {
        switch(x)
        {
            case 0 :
                return SHORT;
            case 1:
                return JEANS;
            case 2:
                return SKIRT;
            case 3 :
                return PANTS;
            default:
                return null;
                    
            
            
            
        }
        
        
    }
}
