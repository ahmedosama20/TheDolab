package com.dolab.thedolab;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Bottom extends Clothes {
    BottomTypes Type;
    public Bottom(BottomTypes type, String note, int colorID){
        super(note, colorID);
        Type = type;
    }

    public String getType() {
        return Type.name();
    }
     public void addToDB(Context context) {
        DBHandler myHandler = new DBHandler(context);
        myHandler.addTop(this);
    }
     public ArrayList< Clothes > getBottoms(Context context)
    {
        DBHandler myHandler = new DBHandler(context);
       return myHandler.getAllBottoms(this);
    }
}
