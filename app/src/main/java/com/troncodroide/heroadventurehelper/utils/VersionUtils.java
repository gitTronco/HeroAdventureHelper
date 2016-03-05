package com.troncodroide.heroadventurehelper.utils;

import android.os.Build;

public class VersionUtils {

    //SDK_INT value        Build.VERSION_CODES        Human Version Name
    public static final int BASE = 1; //Android 1.0 (no codename)
    public static final int BASE_1_1 = 2; // Android 1.1 Petit Four
    public static final int CUPCAKE = 3; // Android 1.5 Cupcake
    public static final int DONUT = 4; // Android 1.6Donut
    public static final int ECLAIR = 5; // Android 2.0Eclair
    public static final int ECLAIR_0_1 = 6; // Android 2.0.1Eclair
    public static final int ECLAIR_MR1 = 7;// Android 2.1Eclair
    public static final int FROYO = 8;// Android 2.2 Froyo
    public static final int GINGERBREAD = 9;// Android 2.3 Gingerbread
    public static final int GINGERBREAD_MR1 = 10;// Android 2.3.3Gingerbread
    public static final int HONEYCOMB = 11;// Android 3.0Honeycomb
    public static final int HONEYCOMB_MR1 = 12;// Android 3.1Honeycomb
    public static final int HONEYCOMB_MR2 = 13;// Android 3.2Honeycomb
    public static final int ICE_CREAM_SANDWICH = 14;// Android 4.0 Ice Cream Sandwich
    public static final int ICE_CREAM_SANDWICH_MR1 = 15;// Android 4.0.3 Ice Cream Sandwich
    public static final int JELLY_BEAN = 16;// Android 4.1 Jellybean
    public static final int JELLY_BEAN_MR1 = 17;// Android 4.2 Jellybean
    public static final int JELLY_BEAN_MR2 = 18;// Android 4.3 Jellybean
    public static final int KITKAT = 19;// Android 4.4 KitKat
    public static final int KITKAT_WATCH = 20;// Android 4.4 KitKat Watch
    public static final int LOLLIPOP = 21;// Android 5.0 Lollipop
    public static final int LOLLIPOP_MR1 = 22;// Android 5.1 Lollipop
    public static final int M = 23;// Android 6.0 Marshamallow

    public static boolean isJellyBean() {
        int sdk = getSDK();
        return (sdk == JELLY_BEAN || sdk == JELLY_BEAN_MR1 || sdk == JELLY_BEAN_MR2);
    }

    public static int getSDK() {
        return Build.VERSION.SDK_INT;
    }
}
