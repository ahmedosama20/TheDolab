package com.dolab.thedolab;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by OmarAbdulRahman on 4/20/16.
 */
public class Outfit {
    int id;
    int topsID;
    int bottomsID;
    int shoesID;

    public Outfit(int i,int t,int b,int s)
    {id = i;topsID = t;bottomsID = b;shoesID = s;}
    public int getBottomID() {
        return bottomsID;
    }

    public  int getShoeID() {
        return shoesID;
    }

    public int getTopID() {
        return topsID;
    }
}
