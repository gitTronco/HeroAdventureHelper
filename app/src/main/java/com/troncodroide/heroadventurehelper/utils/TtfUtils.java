package com.troncodroide.heroadventurehelper.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.troncodroide.heroadventurehelper.R;

import java.util.Map;
import java.util.TreeMap;

public class TtfUtils {
    public static Map<String, Typeface> typefaces = null;
    public static final int DEFAULT_POS_TYPEFACE = 3; //Arial

    public static Typeface getTypefaceFromAssets(Context c, String filename) {
        if (typefaces == null) {
            typefaces = new TreeMap<>();
        }
        Typeface toRet = typefaces.get(filename);
        if (toRet == null) {
            toRet = Typeface.createFromAsset(c.getAssets(), filename);
            typefaces.put(filename, toRet);
        }
        return toRet;
    }

    public static String truncate(String text) {
        return truncate(text, 6);
    }

    public static String truncate(String text, int length) {
        if (text != null && text.length() > length)
            text = text.substring(0, length);
        return text;
    }

    public static String getTypefaceNameByAttrs(Context context) {
        return getTypefaceNameByAttrs(context, null);
    }

    public static String getTypefaceNameByAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return RalewayTypefaces(DEFAULT_POS_TYPEFACE);
        } else {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.TypefacedTextView,
                    0, 0);
            try {
                int mTextPos = a.getInteger(R.styleable.TypefacedTextView_setTypeface, DEFAULT_POS_TYPEFACE);
                return RalewayTypefaces(mTextPos);
            } finally {
                a.recycle();
            }
        }
    }

    public static String RalewayTypefaces(int pos) {
        String dir = "fonts/";
        String typefaces[] = {
                "8-bit pusab.ttf",//0
                "fontawesome-webfont.ttf",//1
                "Kirbys-Adventure.ttf",//2
                "MOZART_0.ttf",//3
                "PressStart2P.ttf",//4
                "prstart.ttf"//5
        };
        return dir + typefaces[pos];
    }
}
