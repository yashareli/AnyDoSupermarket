package com.exmaple.eli.anydosupermarket.Logic;

import android.graphics.Color;

/**
 * Created by eliyashar on 18/10/16.
 */
public class ProductData {
    private String weight;
    private String bagColor;
    private String name;

    public String getWeight(){
        return weight;
    }

    public int getColor() {
        return Color.parseColor(bagColor);
    }

    public String getName() {
        return name;
    }
}