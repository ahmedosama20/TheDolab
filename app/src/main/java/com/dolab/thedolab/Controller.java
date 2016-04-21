package com.dolab.thedolab;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

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
    public MyListAdapter getListAdapter(Context context, int resource,Resources res,int id)
    {
        clothes=new Clothes();
        ArrayList< Clothes > ClothesArray;
        switch(id)
        {
            case 0 :
                 ClothesArray = ((Top)clothes).getAll(context);
                break;
            case 1 :
                 ClothesArray = ((Bottom)clothes).getAll(context);
                break;
            case 2 :
                ClothesArray = ((Shoes)clothes).getAll(context);
                break;
            default:
                return null;
            
        }
        
         return new MyListAdapter(context, resource, ClothesArray, res);
    }
    

}
