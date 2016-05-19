package com.dolab.thedolab;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Mario on 4/20/2016.
 */
//test
public class MyListAdapter extends ArrayAdapter<Clothes> {
    Context context;
    int resource;
    Resources Res;
    ArrayList<Clothes> myClothes = new ArrayList<Clothes>();
    public MyListAdapter(Context context, int resource, ArrayList<Clothes> myclothes, Resources res) {
        super(context, resource, myclothes);
        this.resource = resource;
        this.context = context;
        this.myClothes = myclothes;
        Res = res;
    }
   // public void addToArray(Clothes clothT){
    //   myClothes.add(clothT);
    //    this.notifyDataSetChanged();
    //}
    public void changeEntry(int id){
        for (int x = 0 ; x < myClothes.size() ; x++)
        {
            if (myClothes.get(x).getID() == id);
                //myClothes.get(x); here set the change in the item itself then restart activity;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Clothes myPiece = myClothes.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.upper_list_item,parent,false);

        TextView topname = (TextView) convertView.findViewById(R.id.textView);
        ImageView topimage = (ImageView) convertView.findViewById(R.id.imageView);
        ImageView topcolor = (ImageView) convertView.findViewById(R.id.imageView2);

        topname.setText(myPiece.getNote());
        Drawable f = Res.getDrawable(R.drawable.white, null);
        Bitmap bitmapf = ((BitmapDrawable)f).getBitmap();
        topcolor.setImageBitmap(bitmapf);
        switch (myPiece.getColor()){
            case 0:
                topcolor.setColorFilter(new LightingColorFilter(Color.RED, Color.RED));
                break;
            case 1:
                topcolor.setColorFilter(new LightingColorFilter(Color.GREEN, Color.GREEN));
                break;
            case 2:
                topcolor.setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));
                break;
            case 3:
                topcolor.setColorFilter(new LightingColorFilter(Color.YELLOW, Color.YELLOW));
                break;
            case 4:
                topcolor.setColorFilter(new LightingColorFilter(16737380, 16737380));
                break;
            case 5:
                topcolor.setColorFilter(new LightingColorFilter(16711935, 16711935));
                break;
            case 6:
                topcolor.setColorFilter(new LightingColorFilter(10824234, 10824234));
                break;
            case 7:
                topcolor.setColorFilter(new LightingColorFilter(Color.BLACK, Color.BLACK));
                break;
            case 8:
                topcolor.setColorFilter(new LightingColorFilter(Color.WHITE, Color.WHITE));
                break;
            default:
                Drawable la = Res.getDrawable(R.drawable.laun, null);
                Bitmap bitmapla = ((BitmapDrawable)la).getBitmap();
                topcolor.setImageBitmap(bitmapla);
        }

        Drawable d = null;

        switch (myPiece.getType()) {
            case "TSHIRT":
                d = Res.getDrawable(R.drawable.tshirt, null);
                break;
            case "SWEATSHIRT":
                d = Res.getDrawable(R.drawable.sweatshirt, null);
                break;
            case "JACKET":
                d = Res.getDrawable(R.drawable.jacket, null);
                break;
            case "SHIRT":
                d = Res.getDrawable(R.drawable.shirt, null);
                break;
            case "BLOUSE":
                d = Res.getDrawable(R.drawable.blouse, null);
                break;
            case "CARDIGAN":
                d = Res.getDrawable(R.drawable.cardigan, null);
                break;
            case "SHORT":
                d = Res.getDrawable(R.drawable.shorrt, null);
                break;
            case "JEANS":
                d = Res.getDrawable(R.drawable.jeans, null);
                break;
            case "SKIRT":
                d = Res.getDrawable(R.drawable.skirt, null);
                break;
            case "PANTS":
                d = Res.getDrawable(R.drawable.pants, null);
                break;
            case "SNEAKERS":
                d = Res.getDrawable(R.drawable.sneakers, null);
                break;
            case "FLIPFLOP":
                d = Res.getDrawable(R.drawable.flipflops, null);
                break;
            case "SANDAL":
                d = Res.getDrawable(R.drawable.sandal, null);
                break;
            case "CLASICSHOE":
                d = Res.getDrawable(R.drawable.clasicshoe, null);
                break;
            case "BOOT":
                d = Res.getDrawable(R.drawable.boot, null);
                break;
        }
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        //byte[] bitmapdata = stream.toByteArray();
        //Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
        topimage.setImageBitmap(bitmap);

        return convertView;

    }
}
