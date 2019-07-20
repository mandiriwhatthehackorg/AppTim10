package com.application.library;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;

public class CustomConstraintLayout extends ConstraintLayout {
    public CustomConstraintLayout(Context context) {
        super(context);
    }

    public CustomConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int tinggi = getMeasuredHeight();
        Resources r = getResources();
        int px = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 150,r.getDisplayMetrics()));
        setMeasuredDimension(width, (tinggi-px));
    }
}
