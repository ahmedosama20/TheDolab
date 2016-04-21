package com.dolab.thedolab;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Controller extends Application {
    Clothes clothes;
    private static Controller controller=null;
    protected Controller()
    {

    }
    public static Controller getInstance()
    {
        if(controller==null)
        {
        controller = new Controller();
        }
        return controller;
    }
   
    public void addClothes(int id,int type, String note, int colorID, Context context)
    {
        ClothesFactory Factory= new ClothesFactory();
       clothes= Factory.CreateClothes( id,  type,  note,  colorID);
       clothes.addToDB(context);
    }
    public MyListAdapter getListAdapter(Context context, int resource,Resources res)
    {
         ArrayList< Clothes > ClothesArray = clothes.getAll(context);
         MyListAdapter adapter = new MyListAdapter(context, resource, ClothesArray, res);
    }
    

}
