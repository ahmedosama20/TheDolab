package com.dolab.thedolab;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ShowOutfits extends AppCompatActivity {
    ImageView topImage;
    ImageView topColor;
    ImageView bottomImage;
    ImageView bottomColor;
    ImageView shoeImage;
    ImageView shoeColor;
    Button randomGen;
    DBHandler outfiter;
    ArrayList<Outfit> outfitsSource;
    int indexer;
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
        outfiter = new DBHandler(getApplicationContext());
        indexer = 0;
        outfitsSource = outfiter.getAllOutfits();
        randomGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexer == outfitsSource.size())
                    indexer = 0;
                if (outfitsSource.size() > 0) {
                    setTop(outfitsSource.get(indexer).getTopID());
                    setBottom(outfitsSource.get(indexer).getBottomID());
                    setShoe(outfitsSource.get(indexer).getShoeID());
                    indexer++;
                }

            }
        });

    }
    public void setTop(int topid){

            //db function to return top
            //breaking the top to it's data and show it



    }
    public void setBottom(int bottomid){






    }
    public void setShoe(int shoeid){






    }
}
