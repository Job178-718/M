package com.sun.m.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.sun.m.R;


/**
 * 将图片转化为圆型
 */
public class RoundImageView extends ImageView {

    private static final String TAG = "RoundImageView";
    private Bitmap mBitmap;

    private Paint mPaint,mRoundPaint;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔paint
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mRoundPaint = new Paint();
        mRoundPaint.setColor(0xFF03DAC5);
        mRoundPaint.setStrokeWidth(20);
        mRoundPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 测量绘制
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null || mBitmap.isRecycled()) {
                //直接网上找张图片，放到assets目录下
            //mBitmap = BitmapFactory.decodeStream(getContext().getAssets().open("music.png"));
            Drawable background = getDrawable();
            mBitmap = ((BitmapDrawable) background).getBitmap();
        }
        //画出圆形图片三个方法
        clipRound(canvas);
    }

    /**
     * 方式一：离屏混合模式实现
     */
    private void xfermodRound(Canvas canvas) {
        //新图层:离屏绘制，restore后才将内容显示到屏幕上
        canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);
        //宽高自己指定，这里测试暂时用控件默认宽高
        canvas.drawCircle(getWidth()/2, getHeight()/2, getHeight()/2, mPaint);
        //设置混合模式为SRC_IN
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap,null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        canvas.restore();
    }

    /**
     * 方式2：剪切路径实现，缺点是不能抗锯齿
     */
    private void clipRound(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        path.addCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, Path.Direction.CW);

        canvas.clipPath(path);
        //绘制图片
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        //画外边框
        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2,mRoundPaint);
        canvas.restore();
    }


    /**
     * 方式三：shader实现，跟方式一无大的差别
     */
    private void shaderRound(Canvas canvas) {
        Matrix matrix = new Matrix();

        float scaleX = getWidth() * 1f / mBitmap.getWidth();
        float scaleY = getHeight() * 1f / mBitmap.getHeight();

        matrix.setScale(scaleX, scaleY);
        //获取图片的步骤
        Drawable drawable = getDrawable();

        //获取bitmap
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shader.setLocalMatrix(matrix);
        mPaint.setColor(getResources().getColor(R.color.teal_700));
        mPaint.setShader(shader);
        canvas.drawCircle(getWidth()/2f, getHeight()/2f, getHeight()/2, mPaint);
    }
}

