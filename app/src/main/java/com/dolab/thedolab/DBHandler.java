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
    private static final String TABLE_OUTFIT_BOTTOM = "OUTFIT_BOTTOM";
    private static final String TABLE_OUTFIT_SHOE = "OUTFIT_SHOE";

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
        String CREATE_TOPS_TABLE = "CREATE TABLE " + TABLE_TOPS + "("
                + TOP_ID + " INTEGER PRIMARY key autoincrement," + TOP_TYPE + " TEXT,"
                + TOP_NOTE + " TEXT," + TOP_COLOR + " INTEGER" + ")";
        db.execSQL(CREATE_TOPS_TABLE);

        String CREATE_BOTTOMS_TABLE = "CREATE TABLE " + TABLE_BOTTOMS + "("
                + BOTTOM_ID + " INTEGER PRIMARY key autoincrement," + BOTTOM_TYPE + " TEXT,"
                + BOTTOM_NOTE + " TEXT," + BOTTOM_COLOR + " INTEGER" + ")";
        db.execSQL(CREATE_BOTTOMS_TABLE);

        String CREATE_SHOES_TABLE = "CREATE TABLE " + TABLE_SHOES + "("
                + SHOE_ID + " INTEGER PRIMARY key autoincrement," + SHOE_TYPE + " TEXT,"
                + SHOE_NOTE + " TEXT," + SHOE_COLOR + " INTEGER" + ")";
        db.execSQL(CREATE_SHOES_TABLE);

        String CREATE_OUTFIT_TABLE = "CREATE TABLE " + TABLE_OUTFIT + "("
                + OUTFIT_ID + " INTEGER PRIMARY key autoincrement" + ")";
        db.execSQL(CREATE_OUTFIT_TABLE);

        String CREATE_OUTFIT_TOP_TABLE = "CREATE TABLE " + TABLE_OUTFIT_TOP + "("
                + OUTFIT_ID + " INTEGER, "
                + "FOREIGN KEY REFRENCES " + TABLE_OUTFIT + " ( " + OUTFIT_ID + " ) ON DELETE CASCADE, "
                + TOP_ID + " INTEGER, "
                + "FOREIGN KEY REFRENCES " + TABLE_TOPS + " ( " + TOP_ID + " ) ON DELETE CASCADE, "
                + "PRIMARY KEY ( " + OUTFIT_ID + ", " + TOP_ID + " ) " + ");";
        db.execSQL(CREATE_OUTFIT_TOP_TABLE);

        String CREATE_OUTFIT_BOTTOM_TABLE = "CREATE TABLE " + TABLE_OUTFIT_BOTTOM + "("
                + OUTFIT_ID + " INTEGER, "
                + "FOREIGN KEY REFRENCES " + TABLE_OUTFIT + " ( " + OUTFIT_ID + " ) ON DELETE CASCADE, "
                + BOTTOM_ID + " INTEGER, "
                + "FOREIGN KEY REFRENCES " + TABLE_BOTTOMS + " ( " + BOTTOM_ID + " ) ON DELETE CASCADE, "
                + "PRIMARY KEY ( " + OUTFIT_ID + ", " + BOTTOM_ID + " ) " + ");";
        db.execSQL(CREATE_OUTFIT_BOTTOM_TABLE);

        String CREATE_OUTFIT_SHOE_TABLE = "CREATE TABLE " + TABLE_OUTFIT_SHOE + "("
                + OUTFIT_ID + " INTEGER, "
                + "FOREIGN KEY REFRENCES " + TABLE_OUTFIT + " ( " + OUTFIT_ID + " ) ON DELETE CASCADE, "
                + SHOE_ID + " INTEGER, "
                + "FOREIGN KEY REFRENCES " + TABLE_SHOES + " ( " + SHOE_ID + " ) ON DELETE CASCADE, "
                + "PRIMARY KEY ( " + OUTFIT_ID + ", " + SHOE_ID + " ) " + ");";
        db.execSQL(CREATE_OUTFIT_SHOE_TABLE);
    }

    public void addTop(Top top) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOP_NAME, top.getName()); // Shop Name
        values.put(TOP_PHOTO, top.gett_image()); // Shop Phone Number
        // Inserting Row
        db.insert(TABLE_TOPS, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Top> getAllTops() {
        ArrayList<Top> topList = new ArrayList<Top>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TOPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Top top = new Top();
                top.setId(Integer.parseInt(cursor.getString(0)));
                top.setName(cursor.getString(1));
                top.sett_image(cursor.getBlob(2));
                // Adding contact to list
                topList.add(top);
            } while (cursor.moveToNext());
        }
        // return contact list
        return topList;
    }

    public ArrayList<Bottom> getAllDowns() {
        ArrayList<Bottom> downList = new ArrayList<Bottom>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_DOWNS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bottom down = new Bottom();
                down.setId(Integer.parseInt(cursor.getString(0)));
                down.setName(cursor.getString(1));
                down.setd_image(cursor.getBlob(2));
                // Adding contact to list
                downList.add(down);
            } while (cursor.moveToNext());
        }
        // return contact list
        return downList;
    }

    public ArrayList<Shoes> getAllShoes() {
        ArrayList<Shoes> shoeList = new ArrayList<Shoes>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SHOES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shoes shoe = new Shoes();
                shoe.setId(Integer.parseInt(cursor.getString(0)));
                shoe.setName(cursor.getString(1));
                shoe.sets_image(cursor.getBlob(2));
                // Adding contact to list
                shoeList.add(shoe);
            } while (cursor.moveToNext());
        }
        // return contact list
        return shoeList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
