package com.troncodroide.heroadventurehelper.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.troncodroide.heroadventurehelper.utils.TtfUtils;


public class TypefacedRadioButton extends RadioButton {

    public TypefacedRadioButton(Context context) {
        super(context);
        setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context)));
    }

    public TypefacedRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!this.isInEditMode()) {
            setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context, attrs)));
        }
    }

    public TypefacedRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!this.isInEditMode()) {
            setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context, attrs)));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefacedRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!this.isInEditMode()) {
            setTypeface(TtfUtils.getTypefaceFromAssets(context, TtfUtils.getTypefaceNameByAttrs(context, attrs)));
        }
    }

}
