package android.tom.patcher.diary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by tom.saju on 1/8/2018.
 */

public class LineEditText extends android.support.v7.widget.AppCompatEditText {
    private Rect mRect;
    private Paint mPaint;
    public LineEditText(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        mRect = new Rect();
        mPaint = new Paint();
        // define the style of line
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // define the color of line
        mPaint.setColor(Color.parseColor("#989595"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int lHeight = getLineHeight();
        // the number of line
        int count = height / lHeight;
        if (getLineCount() > count) {
            // for long text with scrolling
            count = getLineCount();
        }
        Rect r = mRect;
        Paint paint = mPaint;

        // first line
        int baseline = getLineBounds(0, r);

        // draw the remaining lines.
        for (int i = 0; i < count; i++) {
            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
            // next line
            baseline += getLineHeight();
        }
        super.onDraw(canvas);
    }

}