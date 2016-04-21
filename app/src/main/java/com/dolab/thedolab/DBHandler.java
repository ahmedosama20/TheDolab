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
    public void onCreate(SQLiteDatabase db) {
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
                + BOTTOM_ID + " INTEGER " + "FOREIGN KEY REFRENCES " + TABLE_BOTTOMS + " ( " + BOTTOM_ID + " ) ON DELETE CASCADE, "
                + SHOE_ID + " INTEGER " + "FOREIGN KEY REFRENCES " + TABLE_SHOES + " ( " + SHOE_ID + " ) ON DELETE CASCADE " +  ");";
        db.execSQL(CREATE_OUTFIT_TABLE);

        String CREATE_OUTFIT_TOP_TABLE = "CREATE TABLE " + TABLE_OUTFIT_TOP + " ("
                + OUTFIT_ID + " INTEGER " + "FOREIGN KEY REFRENCES " + TABLE_OUTFIT + " ( " + OUTFIT_ID + " ) ON DELETE CASCADE, "
                + TOP_ID + " INTEGER " + "FOREIGN KEY REFRENCES " + TABLE_TOPS + " ( " + TOP_ID + " ) ON DELETE CASCADE, "
                + "PRIMARY KEY ( " + OUTFIT_ID + ", " + TOP_ID + " ) " + ");";
        db.execSQL(CREATE_OUTFIT_TOP_TABLE);

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
        // Inserting Row
        db.insert(TABLE_OUTFIT, null, values);

        int outfitID = getLastID(db);
        for (int i=0; i<outfit.getTopCount(); i++) {
            ContentValues values1 = new ContentValues();
            values1.put(OUTFIT_ID, outfitID);
            values1.put(TOP_ID, outfit.getTopID(i));
            db.insert(TABLE_OUTFIT_TOP, null, values1);
        }
        db.close(); // Closing database connection
    }

    int getLastID(SQLiteDatabase db) {
        String query = "SELECT " + OUTFIT_ID + " From " + TABLE_OUTFIT + " ORDER BY " + OUTFIT_ID + " DESC LIMIT 1;" ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return -1;
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
        return topList;
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
        return shoeList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
