package com.erised.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.LruCache;
import android.widget.TextView;

public class MyTextView extends TextView {

    /**
     * An <code>LruCache</code> for previously loaded typefaces.
     */
    private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(
            12);
    private Typeface tf;

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyTextView(Context context) {
        super(context);
        init();

    }

    private void init() {

        tf = sTypefaceCache.get("fonts/Gotham-Bold.otf");

        if (tf == null) {
            tf = Typeface.createFromAsset(getContext().getAssets(),
                    "fonts/Gotham-Bold.otf");

            sTypefaceCache.put("fonts/Gotham-Bold.otf", tf);
        }
        setTypeface(tf);
    }
}
