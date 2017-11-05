package org.apparatum.mini_paint;

    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.Paint;
    import android.graphics.Path;
    import android.util.AttributeSet;
    import android.view.MotionEvent;
    import android.view.View;

/**
 * Created by Arthur Abramov on 05.11.2017.
 */
public class CanvasView extends View {

    private final Paint mPaint;
    private Context context;
    public int width;
    public int height;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private Path mPath;

    public float mX,mY;
    public static final float TOLERANCE = 5.0f;


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(false);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4.0f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onStartTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mPaint);
    }

    private void onStartTouch(float x, float y){
        mPath.moveTo(x,y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(x - mY);

        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas(){
        mPath.reset();
        invalidate();
    }

    private void upTouch(){
        mPath.lineTo(mX,mY);
    }



}
