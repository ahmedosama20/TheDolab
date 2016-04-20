package com.dolab.thedolab;

import android.app.Application;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Controller extends Application {
    Clothes clothes;
    
    public Controller()
    {
    }
   
    public void addClothes(int id,int type, String note, int colorID)
    {
        
        ClothesFactory Factory= new ClothesFactory();
       clothes= Factory.CreateClothes( id,  type,  note,  colorID);
       clothes.addToDb();
       
        
        
    }

}
