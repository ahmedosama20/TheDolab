package com.dolab.thedolab;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mario on 4/20/2016.
 */
public class MyListAdapter extends ArrayAdapter<Top> {
    Context context;
    int resource;
    ArrayList<Top> mytops = new ArrayList<Top>();
    public MyListAdapter(Context context, int resource, ArrayList<Top> mytops) {
        super(context, resource, mytops);
        this.resource = resource;
        this.context = context;
        this.mytops = mytops;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Top mytop = mytops.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.upper_list_item,parent,false);

        TextView topname = (TextView) convertView.findViewById(R.id.textView);
        ImageView topimage = (ImageView) convertView.findViewById(R.id.imageView);

        topname.setText(mytop.getNote());
        topimage.setImageBitmap(BitmapFactory.decodeByteArray(mytop.gett_image(),0,mytop.gett_image().length));

        return convertView;
    }
}
