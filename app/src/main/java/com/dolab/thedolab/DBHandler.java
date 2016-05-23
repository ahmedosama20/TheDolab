package com.dolab.thedolab;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dolab.thedolab.Top;
import com.dolab.thedolab.Bottom;
import com.dolab.thedolab.Shoes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
/* db.rawQuery("SELECT * FROM $#^#$^#^# ORDER BY RANDOM() LIMIT 1", null); */
public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DolabDB";

    //tables
    private static final String TABLE_TOPS = "TOPS";
    private static final String TABLE_BOTTOMS = "BOTTOMS";
    private static final String TABLE_SHOES = "SHOES";
    private static final String TABLE_OUTFIT = "OUTFITS";
    private static final String TABLE_OUTFIT_TOP = "OUTFIT_TOP";

    //tops attributes
    private static final String TOP_ID = "top_id";
    private static final String TOP_TYPE = "top_type";
    private static final String TOP_NOTE = "top_note";
    private static final String TOP_COLOR = "top_color";

    //downs attributes
    private static final String BOTTOM_ID = "bottom_id";
    private static final String BOTTOM_TYPE = "bottom_type";
    private static final String BOTTOM_NOTE = "bottom_note";
    private static final String BOTTOM_COLOR = "bottom_color";

    //shoes attributes
    private static final String SHOE_ID = "shoe_id";
    private static final String SHOE_TYPE = "shoe_type";
    private static final String SHOE_NOTE = "shoe_note";
    private static final String SHOE_COLOR = "shoe_color";

    //outfit attributes
    private static final String OUTFIT_ID = "outfit_id";



    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
            //(OR)
            db.setForeignKeyConstraintsEnabled (true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        String CREATE_TOPS_TABLE = "CREATE TABLE " + TABLE_TOPS + " ("
                + TOP_ID + " INTEGER PRIMARY key autoincrement, " + TOP_TYPE + " TEXT, "
                + TOP_NOTE + " TEXT, " + TOP_COLOR + " INTEGER" + ");";
        db.execSQL(CREATE_TOPS_TABLE);

        String CREATE_BOTTOMS_TABLE = "CREATE TABLE " + TABLE_BOTTOMS + " ("
                + BOTTOM_ID + " INTEGER PRIMARY key autoincrement, " + BOTTOM_TYPE + " TEXT, "
                + BOTTOM_NOTE + " TEXT, " + BOTTOM_COLOR + " INTEGER" + ");";
        db.execSQL(CREATE_BOTTOMS_TABLE);

        String CREATE_SHOES_TABLE = "CREATE TABLE " + TABLE_SHOES + " ("
                + SHOE_ID + " INTEGER PRIMARY key autoincrement," + SHOE_TYPE + " TEXT, "
                + SHOE_NOTE + " TEXT, " + SHOE_COLOR + " INTEGER" + ");";
        db.execSQL(CREATE_SHOES_TABLE);


        String CREATE_OUTFIT_TABLE = "CREATE TABLE " + TABLE_OUTFIT + " ("
                + OUTFIT_ID + " INTEGER PRIMARY key autoincrement, "
                + BOTTOM_ID + " INTEGER REFERENCES " + TABLE_BOTTOMS + " ON DELETE CASCADE, "
                + TOP_ID + " INTEGER REFERENCES " + TABLE_TOPS + " ON DELETE CASCADE, "
                + SHOE_ID + " INTEGER REFERENCES " + TABLE_SHOES + " ON DELETE CASCADE " +  ");";
        db.execSQL(CREATE_OUTFIT_TABLE);


    }

    public Outfit ISIS()
    {
        Outfit tempo;
        int t;int b;int s;
        String selectQuery = "SELECT "+ TOP_ID +" FROM " + TABLE_TOPS + " ORDER BY RANDOM() LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery(selectQuery, null);
        if(cursor1!=null && cursor1.getCount()>0 && cursor1.moveToFirst()){
            t = cursor1.getInt(0);}
        else{
            tempo = new Outfit(-1,-1,-1,-1);
            db.close();
            return tempo;
        }
        selectQuery = "SELECT "+ BOTTOM_ID +" FROM " + TABLE_BOTTOMS + " ORDER BY RANDOM() LIMIT 1";
        Cursor cursor2 = db.rawQuery(selectQuery, null);
        if(cursor2!=null && cursor2.getCount()>0 && cursor2.moveToFirst()){
            b = cursor2.getInt(0);}
        else{
            tempo = new Outfit(-1,-1,-1,-1);
            db.close();
            return tempo;
        }
        selectQuery = "SELECT "+ SHOE_ID +" FROM " + TABLE_SHOES + " ORDER BY RANDOM() LIMIT 1";
        Cursor cursor3 = db.rawQuery(selectQuery, null);
        if(cursor3!=null && cursor3.getCount()>0 && cursor3.moveToFirst()){
            s = cursor3.getInt(0);}
        else{
            tempo = new Outfit(-1,-1,-1,-1);
            db.close();
            return tempo;
        }
        tempo = new Outfit(1,t,b,s);
        db.close();
        return tempo;



    }
    public void addTop(Top top) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOP_TYPE, top.getType());
        values.put(TOP_NOTE, top.getNote());
        values.put(TOP_COLOR, top.getColor());
        // Inserting Row
        db.insert(TABLE_TOPS, null, values);
        db.close(); // Closing database connection
    }
    //public void update_byID(int id, String v1, String v2){
       // ContentValues values = new ContentValues();
       // values.put(KEY_CONTENT1, v1);
       // values.put(KEY_CONTENT2, v2);
      //  sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_ID+"="+id, null);
    //}
    public void addToLaun(int editingid,int tap,int newcolor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(tap == 1){
            values.put(TOP_COLOR,newcolor);
            db.update(TABLE_TOPS, values, TOP_ID+"="+editingid, null);
        }
        if(tap == 2){
            values.put(BOTTOM_COLOR,newcolor);
            db.update(TABLE_BOTTOMS, values, BOTTOM_ID+"="+editingid, null);

        }
        if(tap == 3){
            values.put(SHOE_COLOR,newcolor);
            db.update(TABLE_SHOES, values, SHOE_ID+"="+editingid, null);
        }

        db.close(); // Closing database connection
    }
    public void addBottom(Bottom bottom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOTTOM_TYPE, bottom.getType());
        values.put(BOTTOM_NOTE, bottom.getNote());
        values.put(BOTTOM_COLOR, bottom.getColor());
        // Inserting Row
        db.insert(TABLE_BOTTOMS, null, values);
        db.close(); // Closing database connection
    }

    public void addShoes(Shoes shoes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SHOE_TYPE, shoes.getType());
        values.put(SHOE_NOTE, shoes.getNote());
        values.put(SHOE_COLOR, shoes.getColor());
        // Inserting Row
        db.insert(TABLE_SHOES, null, values);
        db.close(); // Closing database connection
    }

    public void addOutfit(Outfit outfit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SHOE_ID, outfit.getShoeID());
        values.put(BOTTOM_ID, outfit.getBottomID());
        values.put(TOP_ID,outfit.getTopID());
        // Inserting Row
        db.insert(TABLE_OUTFIT, null, values);
        db.close();
        //// Closing database connection
        //test
    }





    public ArrayList<Clothes> getAllTops() {
        ArrayList<Clothes> topList = new ArrayList<Clothes>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TOPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                TopTypes type = null;
                switch (cursor.getString(1)) {
                    case "TSHIRT":
                        type = TopTypes.TSHIRT;
                        break;
                    case "SWEATSHIRT":
                        type = TopTypes.SWEATSHIRT;
                        break;
                    case "JACKET":
                        type = TopTypes.JACKET;
                        break;
                    case "SHIRT":
                        type = TopTypes.SHIRT;
                        break;
                    case "BLOUSE":
                        type = TopTypes.BLOUSE;
                        break;
                    case "CARDIGAN":
                        type = TopTypes.CARDIGAN;
                        break;
                }
                Top top = new Top(type, cursor.getString(2), cursor.getInt(3));
                top.setID(id);

                // Adding contact to list
                topList.add(top);
            } while (cursor.moveToNext());
        }
        // return contact list
        db.close();
        return topList;
    }

    public ArrayList<Outfit> getAllOutfits() {
        ArrayList<Outfit> outList = new ArrayList<Outfit>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_OUTFIT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int bottomid = cursor.getInt(1);
                int topid = cursor.getInt(2);
                int shoeid = cursor.getInt(3);
                Outfit tempy = new Outfit(id,topid,bottomid,shoeid);




                outList.add(tempy);
            } while (cursor.moveToNext());
        }
        // return contact list
        db.close();
        return outList;
    }
    public Top getTopById(int searchId) {
        Top showy;
        String selectQuery = "SELECT * FROM " + TABLE_TOPS + " WHERE " + TOP_ID + " = " + String.valueOf(searchId);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            int id = cursor.getInt(0);
            TopTypes type = null;
            switch (cursor.getString(1)) {
                case "TSHIRT":
                    type = TopTypes.TSHIRT;
                    break;
                case "SWEATSHIRT":
                    type = TopTypes.SWEATSHIRT;
                    break;
                case "JACKET":
                    type = TopTypes.JACKET;
                    break;
                case "SHIRT":
                    type = TopTypes.SHIRT;
                    break;
                case "BLOUSE":
                    type = TopTypes.BLOUSE;
                    break;
                case "CARDIGAN":
                    type = TopTypes.CARDIGAN;
                    break;
            }
            showy = new Top(type, cursor.getString(2), cursor.getInt(3));
            showy.setID(id);
            db.close();
            return showy;
        }

        return null;
    }
    public Bottom getBottomById(int searchId) {
        Bottom showy;
        String selectQuery = "SELECT * FROM " + TABLE_BOTTOMS + " WHERE " + BOTTOM_ID + " = " + String.valueOf(searchId);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            int id = cursor.getInt(0);
            BottomTypes type = null;
            switch (cursor.getString(1)) {
                case "SHORT":
                    type = BottomTypes.SHORT;
                    break;
                case "JEANS":
                    type = BottomTypes.JEANS;
                    break;
                case "SKIRT":
                    type = BottomTypes.SKIRT;
                    break;
                case "PANTS":
                    type = BottomTypes.PANTS;
                    break;
            }
            showy = new Bottom(type, cursor.getString(2), cursor.getInt(3));
            showy.setID(id);
            db.close();
            return showy;
        }

        return null;
    }
    public Shoes getShoeById(int searchId) {
        Shoes showy;
        String selectQuery = "SELECT * FROM " + TABLE_SHOES + " WHERE " + SHOE_ID + " = " + String.valueOf(searchId);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            int id = cursor.getInt(0);
            ShoeTypes type = null;
            switch (cursor.getString(1)) {
                case "SNEAKERS":
                    type = ShoeTypes.SNEAKERS;
                    break;
                case "FLIPFLOP":
                    type = ShoeTypes.FLIPFLOP;
                    break;
                case "SANDAL":
                    type = ShoeTypes.SANDAL;
                    break;
                case "CLASICSHOE":
                    type = ShoeTypes.CLASICSHOE;
                    break;
                case "BOOT":
                    type = ShoeTypes.BOOT;
                    break;
            }
            showy = new Shoes(type, cursor.getString(2), cursor.getInt(3));
            showy.setID(id);
            db.close();
            return showy;
        }

        return null;
    }

    public ArrayList<Clothes> getAllBottoms() {
        ArrayList<Clothes> bottomList = new ArrayList<Clothes>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BOTTOMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                BottomTypes type = null;
                switch (cursor.getString(1)) {
                    case "SHORT":
                        type = BottomTypes.SHORT;
                        break;
                    case "JEANS":
                        type = BottomTypes.JEANS;
                        break;
                    case "SKIRT":
                        type = BottomTypes.SKIRT;
                        break;
                    case "PANTS":
                        type = BottomTypes.PANTS;
                        break;
                }
                Bottom bottom = new Bottom(type, cursor.getString(2), cursor.getInt(3));
                bottom.setID(id);

                // Adding contact to list
                bottomList.add(bottom);
            } while (cursor.moveToNext());
        }
        // return contact list
        db.close();
        return bottomList;
    }



    public ArrayList<Clothes> getAllShoes() {
        ArrayList<Clothes> shoeList = new ArrayList<Clothes>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SHOES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                ShoeTypes type = null;
                switch (cursor.getString(1)) {
                    case "SNEAKERS":
                        type = ShoeTypes.SNEAKERS;
                        break;
                    case "FLIPFLOP":
                        type = ShoeTypes.FLIPFLOP;
                        break;
                    case "SANDAL":
                        type = ShoeTypes.SANDAL;
                        break;
                    case "CLASICSHOE":
                        type = ShoeTypes.CLASICSHOE;
                        break;
                    case "BOOT":
                        type = ShoeTypes.BOOT;
                        break;
                }
                Shoes shoes = new Shoes(type, cursor.getString(2), cursor.getInt(3));
                shoes.setID(id);

                // Adding contact to list
                shoeList.add(shoes);
            } while (cursor.moveToNext());
        }
        // return contact list
        db.close();
        return shoeList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void deleteOutById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_OUTFIT +" where " + OUTFIT_ID + " = " + String.valueOf(id));
        db.close();
    }
    public void deleteTopId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_OUTFIT +" where " + TOP_ID + " = " + String.valueOf(id));
        db.execSQL("delete from "+ TABLE_TOPS +" where " + TOP_ID + " = " + String.valueOf(id));
        db.close();
    }
    public void deleteBottomId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_OUTFIT +" where " + BOTTOM_ID + " = " + String.valueOf(id));
        db.execSQL("delete from "+ TABLE_BOTTOMS +" where " + BOTTOM_ID + " = " + String.valueOf(id));
        db.close();
    }
    public void deleteShoeId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_OUTFIT +" where " + SHOE_ID + " = " + String.valueOf(id));
        db.execSQL("delete from "+ TABLE_SHOES +" where " + SHOE_ID + " = " + String.valueOf(id));
        db.close();
    }
}
