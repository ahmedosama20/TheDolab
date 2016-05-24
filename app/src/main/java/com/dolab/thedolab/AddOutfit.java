package com.dolab.thedolab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AddOutfit extends AppCompatActivity {
    Controller controller;
    MyListAdapter adapter;
    ListView listview;
    int listCount;
    int topid;
    int bottomid;
    int shoeid;
    Outfit adding;
    DBHandler myhandler;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //Intent toMain = new Intent(AddOutfit.this,DolabMainViewActivity.class);
        //startActivity(toMain);
        //System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outfit);
        listCount = 0;
        controller = Controller.getInstance();
        myhandler = new DBHandler(AddOutfit.this);

        adapter = controller.getListAdapter(this, R.layout.upper_list_item, getResources(), listCount);
        listview = (ListView) findViewById(R.id.listView2);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listCount == 2){
                    shoeid = adapter.getItem(position).getID();
                    adding = new Outfit(0,topid,bottomid,shoeid);
                    myhandler.addOutfit(adding);

                    //Intent toMain = new Intent(AddOutfit.this,DolabMainViewActivity.class);
                    //startActivity(toMain);
                    //System.exit(0);
                    finish();
                }
                if (listCount == 1){
                    bottomid = adapter.getItem(position).getID();
                    listCount++;
                    adapter = controller.getListAdapter(AddOutfit.this, R.layout.upper_list_item, getResources(), listCount);
                    listview.setAdapter(adapter);
                }
                if (listCount == 0){
                    topid = adapter.getItem(position).getID();
                    listCount++;
                    adapter = controller.getListAdapter(AddOutfit.this, R.layout.upper_list_item, getResources(), listCount);
                    listview.setAdapter(adapter);
                }
            }
        });
    }


}
