package com.dolab.thedolab;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdditionActivity extends AppCompatActivity {

    Controller controller;
    int section;
    int selectedPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        Spinner spinner = (Spinner)findViewById(R.id.ColorSpinner);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        Bundle mdata = getIntent().getExtras();
        section = mdata.getInt("section");

        Button mbutton = (Button)findViewById(R.id.addButton);
        String x = "Add ";
        if(section == 0)
        {
            x+= "Top";
        }
        else if(section == 1)
        {
            x += "Down";
        }
        else if(section == 2)
        {
            x += "Shoe";
        }
        mbutton.setText(x);
        controller = Controller.getInstance();

        //begin constructing arrays of stuff
        final ArrayList<ArrayList> arraylists = new ArrayList();
        arraylists.add(new ArrayList()); // top
        arraylists.add(new ArrayList()); // bottom
        arraylists.add(new ArrayList()); // shoe

        arraylists.get(0).add(R.drawable.blouse);
        arraylists.get(0).add(R.drawable.cardigan);
        arraylists.get(0).add(R.drawable.jacket);
        arraylists.get(0).add(R.drawable.shirt);
        arraylists.get(0).add(R.drawable.sweatshirt);
        arraylists.get(0).add(R.drawable.tshirt);
        arraylists.get(1).add(R.drawable.jeans);
        arraylists.get(1).add(R.drawable.pants);
        arraylists.get(1).add(R.drawable.shorrt);
        arraylists.get(1).add(R.drawable.skirt);
        arraylists.get(2).add(R.drawable.boot);
        arraylists.get(2).add(R.drawable.clasicshoe);
        arraylists.get(2).add(R.drawable.flipflops);
        arraylists.get(2).add(R.drawable.sandal);
        arraylists.get(2).add(R.drawable.sneakers);

        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.addclothesScrollView);

        LinearLayout topLinearLayout = new LinearLayout(this);

        topLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        final ArrayList<ImageView> myList = new ArrayList();


        selectedPicture = -1;
        for (int i = 0; i < arraylists.get(section).size(); i++){

            final ImageView imageView = new ImageView (this);

            imageView.setTag(i);

            imageView.setImageResource((int)arraylists.get(section).get(i));

            final float scale = getResources().getDisplayMetrics().density;
            int dpWidthInPx  = (int) (160 * scale);
            int dpHeightInPx = (int) (180 * scale);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpWidthInPx, dpHeightInPx);
            imageView.setLayoutParams(layoutParams);
            topLinearLayout.addView(imageView);
            myList.add(imageView);

            imageView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    for(int i = 0; i < myList.size(); i++)
                    {
                        myList.get(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.background_material_light));
                    }
                    v.setBackgroundColor((Color.GREEN));
                    selectedPicture = (int)imageView.getTag();
                }
            });


        }

        scrollView.addView(topLinearLayout);
    }

    //Button onClick;
    public void onClick(View view) {
        if(selectedPicture == -1)
        {
            Context context = getApplicationContext();
            CharSequence text = "Please select type";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            controller.addClothes(section, selectedPicture, ((EditText)findViewById(R.id.addClothesComment)).getText().toString(), ((Spinner)findViewById(R.id.ColorSpinner)).getSelectedItemPosition(), getApplicationContext());
            finish();
        }
    }
}
