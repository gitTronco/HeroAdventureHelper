package com.troncodroide.heroadventurehelper.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.troncodroide.heroadventurehelper.utils.TtfUtils;


public class TypefacedCheckBOx extends CheckBox {

    public TypefacedCheckBOx(Context context) {
        super(context);
        setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context)));
    }

    public TypefacedCheckBOx(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!this.isInEditMode()) {
            setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context, attrs)));
        }
    }

    public TypefacedCheckBOx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!this.isInEditMode()) {
            setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context, attrs)));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefacedCheckBOx(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!this.isInEditMode()) {
            setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context, attrs)));
        }
    }

}
