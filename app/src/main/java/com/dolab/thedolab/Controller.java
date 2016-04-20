package com.dolab.thedolab;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Controller extends Application {
    Clothes clothes;
    
    public Controller()
    {
    }
   
    public void addClothes(int id,int type, String note, int colorID, Context context)
    {

        ClothesFactory Factory= new ClothesFactory();
       clothes= Factory.CreateClothes( id,  type,  note,  colorID);
       clothes.addToDB(context);

        
       ClothesFactory Factory= new ClothesFactory();
       clothes= Factory.CreateClothes( id,  type,  note,  colorID);
       clothes.addToDB(context);
       
    }
    public getListAdapter(context context, int resource)
    {
         ArrayList< Clothes > ClothesArray = clothes.getAll(context);
         MyListAdapter adapter = new MyListAdapter(context,resource,ClothesArray);
         
    }

}
