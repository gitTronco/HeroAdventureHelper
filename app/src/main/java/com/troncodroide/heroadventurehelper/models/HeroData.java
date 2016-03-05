package com.troncodroide.heroadventurehelper.models;

import com.troncodroide.heroadventurehelper.R;

/**
 * Created by Tronco on 05/03/2016.
 */
public class HeroData {
    String name;
    int resImage;
    int level;

    public HeroData() {
        name = "Jhon Doe";
        resImage = R.drawable.hero;
        level = 0;
    }

    public HeroData(String name, int resImage, int level) {
        this.name = name;
        this.resImage = resImage;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getResImage() {
        return resImage;
    }

    public int getLevel() {
        return level;
    }
}
