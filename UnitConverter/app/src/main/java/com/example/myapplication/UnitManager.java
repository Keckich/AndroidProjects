package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;


public class UnitManager {
    protected ArrayList<Unit> values = new ArrayList<>();

    public ArrayList<Unit> getValues (String category) {
        switch (category) {
            case "DISTANCE": {
                values = new ArrayList<>(distance());
                break;
            }
            case "WEIGHT": {
                values = new ArrayList<>(weight());
                break;
            }
            case "CURRENCY": {
                values = new ArrayList<>(currency());
                break;
            }
        }
        return values;
    }

    private ArrayList<Unit> distance() {
        values = new ArrayList<>();
        values.add(new Unit("inch", 25.4));
        values.add(new Unit("foot", 304.8));
        values.add(new Unit("mile", 1609000.));
        values.add(new Unit("mm", 1.));
        values.add(new Unit("m", 10e2));
        values.add(new Unit("km", 10e5));
        return values;
    }

    private ArrayList<Unit> weight() {
        values = new ArrayList<>();
        values.add(new Unit("ounce", 29.896));
        values.add(new Unit("lb", 410.));
        values.add(new Unit("pd", 16380.));
        values.add(new Unit("g", 1.));
        values.add(new Unit("kg", 10e2));
        values.add(new Unit("ton", 10e5));
        return values;
    }

    private ArrayList<Unit> currency() {
        values = new ArrayList<>();
        values.add(new Unit("USD", 1.));
        values.add(new Unit("RUB", 0.013));
        values.add(new Unit("BYN", 0.39));
        values.add(new Unit("EUR", 1.17));
        return values;
    }


}
