package com.dolab.thedolab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowOutfits extends AppCompatActivity {
    ImageView topImage;
    ImageView topColor;
    ImageView bottomImage;
    ImageView bottomColor;
    ImageView shoeImage;
    ImageView shoeColor;
    Button randomGen;
    Button realRandom;
    Button before;
    Button deleter;
    int trueindexer;
    DBHandler outfiter;
    boolean clicky;
    ArrayList<Outfit> outfitsSource;
    int indexer;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toMain = new Intent(ShowOutfits.this,DolabMainViewActivity.class);
        startActivity(toMain);
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_outfits);
        topImage = (ImageView) findViewById(R.id.imageView3);
        topColor = (ImageView) findViewById(R.id.imageView6);
        bottomImage = (ImageView) findViewById(R.id.imageView4);
        bottomColor = (ImageView) findViewById(R.id.imageView7);
        shoeImage = (ImageView) findViewById(R.id.imageView5);
        shoeColor = (ImageView) findViewById(R.id.imageView8);
        randomGen = (Button) findViewById(R.id.button2);
        realRandom = (Button) findViewById(R.id.button3);
        before = (Button) findViewById(R.id.button4);
        deleter = (Button) findViewById(R.id.button);
        clicky = false;
        outfiter = new DBHandler(getApplicationContext());
        indexer = 0;
        trueindexer = 0;
        outfitsSource = outfiter.getAllOutfits();
        if (outfitsSource.size() > 0){
            int size = outfitsSource.size();
            if (indexer == size)
                indexer = 0;
            if (indexer < 0 && size > 1)
                indexer = 1;
            if (indexer < 0 && size == 1)
                indexer = 0;
            if (size > 0) {
                setTop(outfitsSource.get(indexer).getTopID());
                setBottom(outfitsSource.get(indexer).getBottomID());
                setShoe(outfitsSource.get(indexer).getShoeID());
                indexer++;
                trueindexer = indexer - 1 ;
                clicky = true;
        }
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = outfitsSource.size();
                if (indexer < 0)
                    indexer = size-1;
                if (indexer == size && size >= 2)
                    indexer = size-2;
                if (indexer == size && size < 2)
                    indexer = 0;
                if (size > 0) {
                    setTop(outfitsSource.get(indexer).getTopID());
                    setBottom(outfitsSource.get(indexer).getBottomID());
                    setShoe(outfitsSource.get(indexer).getShoeID());
                    indexer--;
                    trueindexer = indexer + 1;
                    clicky = true;
                }

            }

        });
        deleter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicky){
                outfiter.deleteOutById(outfitsSource.get(trueindexer).id);
                Intent ree = new Intent(ShowOutfits.this,ShowOutfits.class);
                startActivity(ree);
                System.exit(0);}
            }
        });
        realRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Outfit tempS;
                tempS = outfiter.ISIS();
                if (tempS.getTopID() == -1 || tempS.getBottomID() == -1 || tempS.getShoeID() == -1)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "روح شوف امك عايزة إيه";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    setTop(tempS.getTopID());
                    setBottom(tempS.getBottomID());
                    setShoe(tempS.getShoeID());
                    clicky = false;
                }

            }
        });

        randomGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = outfitsSource.size();
                if (indexer == size)
                    indexer = 0;
                if (indexer < 0 && size > 1)
                    indexer = 1;
                if (indexer < 0 && size == 1)
                    indexer = 0;
                if (size > 0) {
                    setTop(outfitsSource.get(indexer).getTopID());
                    setBottom(outfitsSource.get(indexer).getBottomID());
                    setShoe(outfitsSource.get(indexer).getShoeID());
                    indexer++;
                    trueindexer = indexer - 1 ;
                    clicky = true;
                }

            }
        });

    }}
    public void setTop(int topid){

            Top shower = outfiter.getTopById(topid);

        Drawable f = getResources().getDrawable(R.drawable.white, null);
        Bitmap bitmapf = ((BitmapDrawable)f).getBitmap();
        topColor.setImageBitmap(bitmapf);
        switch (shower.getColor()){
            case 0:
                topColor.setColorFilter(new LightingColorFilter(Color.RED, Color.RED));
                break;
            case 1:
                topColor.setColorFilter(new LightingColorFilter(Color.GREEN, Color.GREEN));
                break;
            case 2:
                topColor.setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));
                break;
            case 3:
                topColor.setColorFilter(new LightingColorFilter(Color.YELLOW, Color.YELLOW));
                break;
            case 4:
                topColor.setColorFilter(new LightingColorFilter(16737380, 16737380));
                break;
            case 5:
                topColor.setColorFilter(new LightingColorFilter(16711935, 16711935));
                break;
            case 6:
                topColor.setColorFilter(new LightingColorFilter(10824234, 10824234));
                break;
            case 7:
                topColor.setColorFilter(new LightingColorFilter(Color.BLACK, Color.BLACK));
                break;
            case 8:
                topColor.setColorFilter(new LightingColorFilter(Color.WHITE, Color.WHITE));
                break;
            default:
                topColor.clearColorFilter();
                Drawable la = getResources().getDrawable(R.drawable.laun, null);
                Bitmap bitmapla = ((BitmapDrawable)la).getBitmap();
                topColor.setImageBitmap(bitmapla);
        }
        Drawable d = null;

        switch (shower.getType()) {
            case "TSHIRT":
                d = getResources().getDrawable(R.drawable.tshirt, null);
                break;
            case "SWEATSHIRT":
                d = getResources().getDrawable(R.drawable.sweatshirt, null);
                break;
            case "JACKET":
                d = getResources().getDrawable(R.drawable.jacket, null);
                break;
            case "SHIRT":
                d = getResources().getDrawable(R.drawable.shirt, null);
                break;
            case "BLOUSE":
                d = getResources().getDrawable(R.drawable.blouse, null);
                break;
            case "CARDIGAN":
                d = getResources().getDrawable(R.drawable.cardigan, null);
                break;
            case "SHORT":
                d = getResources().getDrawable(R.drawable.shorrt, null);
                break;
            case "JEANS":
                d = getResources().getDrawable(R.drawable.jeans, null);
                break;
            case "SKIRT":
                d = getResources().getDrawable(R.drawable.skirt, null);
                break;
            case "PANTS":
                d = getResources().getDrawable(R.drawable.pants, null);
                break;
            case "SNEAKERS":
                d = getResources().getDrawable(R.drawable.sneakers, null);
                break;
            case "FLIPFLOP":
                d = getResources().getDrawable(R.drawable.flipflops, null);
                break;
            case "SANDAL":
                d = getResources().getDrawable(R.drawable.sandal, null);
                break;
            case "CLASICSHOE":
                d = getResources().getDrawable(R.drawable.clasicshoe, null);
                break;
            case "BOOT":
                d = getResources().getDrawable(R.drawable.boot, null);
                break;
        }
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        //byte[] bitmapdata = stream.toByteArray();
        //Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
        topImage.setImageBitmap(bitmap);
    }
    public void setBottom(int bottomid){
        Bottom bot = outfiter.getBottomById(bottomid);
        Drawable f = getResources().getDrawable(R.drawable.white, null);
        Bitmap bitmapf = ((BitmapDrawable)f).getBitmap();
        bottomColor.setImageBitmap(bitmapf);
        switch (bot.getColor()){
            case 0:
                bottomColor.setColorFilter(new LightingColorFilter(Color.RED, Color.RED));
                break;
            case 1:
                bottomColor.setColorFilter(new LightingColorFilter(Color.GREEN, Color.GREEN));
                break;
            case 2:
                bottomColor.setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));
                break;
            case 3:
                bottomColor.setColorFilter(new LightingColorFilter(Color.YELLOW, Color.YELLOW));
                break;
            case 4:
                bottomColor.setColorFilter(new LightingColorFilter(16737380, 16737380));
                break;
            case 5:
                bottomColor.setColorFilter(new LightingColorFilter(16711935, 16711935));
                break;
            case 6:
                bottomColor.setColorFilter(new LightingColorFilter(10824234, 10824234));
                break;
            case 7:
                bottomColor.setColorFilter(new LightingColorFilter(Color.BLACK, Color.BLACK));
                break;
            case 8:
                bottomColor.setColorFilter(new LightingColorFilter(Color.WHITE, Color.WHITE));
                break;
            default:
                bottomColor.clearColorFilter();
                Drawable la = getResources().getDrawable(R.drawable.laun, null);
                Bitmap bitmapla = ((BitmapDrawable)la).getBitmap();
                bottomColor.setImageBitmap(bitmapla);
        }
        Drawable d = null;

        switch (bot.getType()) {
            case "TSHIRT":
                d = getResources().getDrawable(R.drawable.tshirt, null);
                break;
            case "SWEATSHIRT":
                d = getResources().getDrawable(R.drawable.sweatshirt, null);
                break;
            case "JACKET":
                d = getResources().getDrawable(R.drawable.jacket, null);
                break;
            case "SHIRT":
                d = getResources().getDrawable(R.drawable.shirt, null);
                break;
            case "BLOUSE":
                d = getResources().getDrawable(R.drawable.blouse, null);
                break;
            case "CARDIGAN":
                d = getResources().getDrawable(R.drawable.cardigan, null);
                break;
            case "SHORT":
                d = getResources().getDrawable(R.drawable.shorrt, null);
                break;
            case "JEANS":
                d = getResources().getDrawable(R.drawable.jeans, null);
                break;
            case "SKIRT":
                d = getResources().getDrawable(R.drawable.skirt, null);
                break;
            case "PANTS":
                d = getResources().getDrawable(R.drawable.pants, null);
                break;
            case "SNEAKERS":
                d = getResources().getDrawable(R.drawable.sneakers, null);
                break;
            case "FLIPFLOP":
                d = getResources().getDrawable(R.drawable.flipflops, null);
                break;
            case "SANDAL":
                d = getResources().getDrawable(R.drawable.sandal, null);
                break;
            case "CLASICSHOE":
                d = getResources().getDrawable(R.drawable.clasicshoe, null);
                break;
            case "BOOT":
                d = getResources().getDrawable(R.drawable.boot, null);
                break;
        }
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        //byte[] bitmapdata = stream.toByteArray();
        //Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
        bottomImage.setImageBitmap(bitmap);





    }
    public void setShoe(int shoeid){
        Shoes bot = outfiter.getShoeById(shoeid);
        Drawable f = getResources().getDrawable(R.drawable.white, null);
        Bitmap bitmapf = ((BitmapDrawable)f).getBitmap();
        shoeColor.setImageBitmap(bitmapf);
        switch (bot.getColor()){
            case 0:
                shoeColor.setColorFilter(new LightingColorFilter(Color.RED, Color.RED));
                break;
            case 1:
                shoeColor.setColorFilter(new LightingColorFilter(Color.GREEN, Color.GREEN));
                break;
            case 2:
                shoeColor.setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));
                break;
            case 3:
                shoeColor.setColorFilter(new LightingColorFilter(Color.YELLOW, Color.YELLOW));
                break;
            case 4:
                shoeColor.setColorFilter(new LightingColorFilter(16737380, 16737380));
                break;
            case 5:
                shoeColor.setColorFilter(new LightingColorFilter(16711935, 16711935));
                break;
            case 6:
                shoeColor.setColorFilter(new LightingColorFilter(10824234, 10824234));
                break;
            case 7:
                shoeColor.setColorFilter(new LightingColorFilter(Color.BLACK, Color.BLACK));
                break;
            case 8:
                shoeColor.setColorFilter(new LightingColorFilter(Color.WHITE, Color.WHITE));
                break;
            default:
                shoeColor.clearColorFilter();
                Drawable la = getResources().getDrawable(R.drawable.laun, null);
                Bitmap bitmapla = ((BitmapDrawable)la).getBitmap();
                shoeColor.setImageBitmap(bitmapla);
        }
        Drawable d = null;

        switch (bot.getType()) {
            case "TSHIRT":
                d = getResources().getDrawable(R.drawable.tshirt, null);
                break;
            case "SWEATSHIRT":
                d = getResources().getDrawable(R.drawable.sweatshirt, null);
                break;
            case "JACKET":
                d = getResources().getDrawable(R.drawable.jacket, null);
                break;
            case "SHIRT":
                d = getResources().getDrawable(R.drawable.shirt, null);
                break;
            case "BLOUSE":
                d = getResources().getDrawable(R.drawable.blouse, null);
                break;
            case "CARDIGAN":
                d = getResources().getDrawable(R.drawable.cardigan, null);
                break;
            case "SHORT":
                d = getResources().getDrawable(R.drawable.shorrt, null);
                break;
            case "JEANS":
                d = getResources().getDrawable(R.drawable.jeans, null);
                break;
            case "SKIRT":
                d = getResources().getDrawable(R.drawable.skirt, null);
                break;
            case "PANTS":
                d = getResources().getDrawable(R.drawable.pants, null);
                break;
            case "SNEAKERS":
                d = getResources().getDrawable(R.drawable.sneakers, null);
                break;
            case "FLIPFLOP":
                d = getResources().getDrawable(R.drawable.flipflops, null);
                break;
            case "SANDAL":
                d = getResources().getDrawable(R.drawable.sandal, null);
                break;
            case "CLASICSHOE":
                d = getResources().getDrawable(R.drawable.clasicshoe, null);
                break;
            case "BOOT":
                d = getResources().getDrawable(R.drawable.boot, null);
                break;
        }
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        //byte[] bitmapdata = stream.toByteArray();
        //Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
        shoeImage.setImageBitmap(bitmap);




    }
}
