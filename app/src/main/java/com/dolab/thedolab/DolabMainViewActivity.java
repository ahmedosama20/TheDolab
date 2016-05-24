package com.dolab.thedolab;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;

public class DolabMainViewActivity extends AppCompatActivity {

    Controller controller;
    public static int sec;
    public static int mPosition = 0;
    public static boolean delete;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolab_main_view);
        controller = Controller.getInstance();
        //
        delete = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mPosition = 0;
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                mPosition = tab.getPosition();
                mViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mPosition = tab.getPosition();
            }

        });
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.dele);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final ColorStateList x = fab.getBackgroundTintList();
        FloatingActionButton ran = (FloatingActionButton) findViewById(R.id.ran);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete = !delete;
                if(delete) {
                    v.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else
                {
                    v.setBackgroundTintList(x);
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOnClick(view);
            }
        });
        ran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), ShowOutfits.class);
                startActivity(x);
               // System.exit(0);
            }
        });
        FloatingActionButton newo = (FloatingActionButton) findViewById(R.id.newo);
        newo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opener = new Intent(getApplicationContext(),AddOutfit.class);
                startActivity(opener);
                //System.exit(0);
            }
        });
        //controller.addClothes(0, 1, "note", 10, getApplicationContext());



    }

    public void myOnClick(View view) {
        Intent i = new Intent(this, AdditionActivity.class);
        i.putExtra("section", mPosition);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dolab_main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Activity mact = (Activity) context;
            del = (FloatingActionButton) mact.findViewById(R.id.dele);
            x = ((FloatingActionButton) mact.findViewById(R.id.fab)).getBackgroundTintList();
        }

        Controller controller;
        MyListAdapter adapter;
        ListView topslistview;
        DBHandler dEdit;
        FloatingActionButton del;
        ColorStateList x ;
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

            super.onViewCreated(view, savedInstanceState);
            int sec = getArguments().getInt(ARG_SECTION_NUMBER);
            adapter = controller.getListAdapter(getActivity().getApplicationContext(), R.layout.upper_list_item, getResources(), sec);

            dEdit = new DBHandler(getActivity().getApplicationContext());

            topslistview = (ListView) getView().findViewById(R.id.listView);
            topslistview.setAdapter(adapter);

            topslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (!delete){
                    int test = adapter.getItem(position).getColor();
                    if (test >= 100){
                        adapter.getItem(position).setColorID(test-100);
                    }
                    else{
                        adapter.getItem(position).setColorID(test+100);
                    }
                    adapter.notifyDataSetChanged();
                    dEdit.addToLaun(adapter.getItem(position).getID(),getArguments().getInt(ARG_SECTION_NUMBER)+1,adapter.getItem(position).getColor());}
                    else
                    {
                        if (mPosition == 0)
                            dEdit.deleteTopId(adapter.getItem(position).getID());
                        if (mPosition == 1)
                            dEdit.deleteBottomId(adapter.getItem(position).getID());
                        if (mPosition == 2)
                            dEdit.deleteShoeId(adapter.getItem(position).getID());
                        delete = false;
                        del.setBackgroundTintList(x);
                        adapter = controller.getListAdapter(getActivity(), R.layout.upper_list_item,getResources(),mPosition);
                        topslistview.setAdapter(adapter);
                    }
                }
            });

        }

        @Override
        public void onResume() {
            super.onResume();
            adapter = controller.getListAdapter(getActivity().getApplicationContext(),R.layout.upper_list_item,getResources(),getArguments().getInt(ARG_SECTION_NUMBER));
            //adapter.notifyDataSetChanged();
            topslistview.setAdapter(adapter);
        }

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {
            controller = Controller.getInstance();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //mPosition = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_dolab_main_view, container, false);

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TOP";
                case 1:
                    return "DOWN";
                case 2:
                    return "SHOE";
            }
            return null;
        }
    }
}
