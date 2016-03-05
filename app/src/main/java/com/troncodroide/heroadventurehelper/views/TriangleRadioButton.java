package com.troncodroide.heroadventurehelper.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.troncodroide.heroadventurehelper.R;

public class TriangleRadioButton extends TypefacedRadioButton {

    public TriangleRadioButton(Context context) {
        super(context);
    }

    public TriangleRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TriangleRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //int color = getContext().getResources().getColor(R.color.grey6);

        if (!isChecked()) {
//            color = Color.WHITE;
        }
        int width = getMeasuredWidth();
        int heidht = getMeasuredHeight();
        int x1, y1, x2, y2, x3, y3;
        int radius = 10;

        x1 = (width / 2) + radius;
        x2 = (width / 2) - radius;
        x3 = (width / 2);

        y1 = heidht;
        y2 = heidht;
        y3 = heidht - radius;

        PointF punto1 = new PointF(x1, y1);
        PointF punto2 = new PointF(x2, y2);
        PointF punto3 = new PointF(x3, y3);

        Path path = new Path();
        path.moveTo(punto1.x, punto1.y);

        path.lineTo(punto2.x, punto2.y);
        path.lineTo(punto3.x, punto3.y);
        path.lineTo(punto1.x, punto1.y);

        Paint paint = new Paint();
  //      paint.setColor(color);
        // We can use Paint.Style.Stroke if we want to draw a "hollow" polygon,
        // But then we had better set the .StrokeWidth property on the paint.
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1f);
        canvas.drawPath(path, paint);
    }

}
