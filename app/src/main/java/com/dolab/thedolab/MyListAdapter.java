package com.dolab.thedolab;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Mario on 4/20/2016.
 */
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Clothes myPiece = myClothes.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.upper_list_item,parent,false);

        TextView topname = (TextView) convertView.findViewById(R.id.textView);
        ImageView topimage = (ImageView) convertView.findViewById(R.id.imageView);

        topname.setText(myPiece.getNote());

        Drawable d = null;
        switch (myPiece.getType()) {
            case "TSHIRT":
                d = Res.getDrawable(R.drawable.tshirt);
                break;
            case "SWEATSHIRT":
                d = Res.getDrawable(R.drawable.sweatshirt);
                break;
            case "JACKET":
                d = Res.getDrawable(R.drawable.jacket);
                break;
            case "SHIRT":
                d = Res.getDrawable(R.drawable.shirt);
                break;
            case "BLOUSE":
                d = Res.getDrawable(R.drawable.blouse);
                break;
            case "CARDIGAN":
                d = Res.getDrawable(R.drawable.cardigan);
                break;
        }
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
        topimage.setImageBitmap(bitmap1);

        return convertView;
    }
}
