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
    Vector<Integer> topsIDs;
    int bottomsID;
    int shoesID;

    public int getBottomID() {
        return bottomsID;
    }

    public  int getShoeID() {
        return shoesID;
    }

    public int getTopCount() {
        return topsIDs.size();
    }

    public int getTopID(int i) {
        return topsIDs.get(i);
    }
}
