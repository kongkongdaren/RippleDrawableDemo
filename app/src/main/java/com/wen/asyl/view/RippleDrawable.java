package com.wen.asyl.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;


/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-12 10:59
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class RippleDrawable extends Drawable {
     //透明度(0~255)
    private  int mAlpha=30;
    private int mRippleColor=0;
    //画笔
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    //圆心坐标
    private float mRipplePointX,mRipplePointY;
    //半径
    private float mRippleRadius=200;


    public RippleDrawable() {
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
        setRippleColor(Color.RED);
        //设置滤镜
       // setColorFilter(new LightingColorFilter(0xFFFF0000,0x00330000));
    }
    public void setRippleColor(int color){
        mRippleColor=color;
        onColorOrAlphaChange();
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
       // canvas.drawColor(Color.RED);
       // canvas.drawBitmap(bitmap,0,0,mPaint);
        //画上一个圆
        canvas.drawCircle(mRipplePointX
                ,mRipplePointY,
                mRippleRadius,mPaint);

    }
    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        //设置Drawable的透明度
           mAlpha=alpha;
        onColorOrAlphaChange();
    }
    private void onColorOrAlphaChange(){
        mPaint.setColor(mRippleColor);
        if (mAlpha!=255) {
            //得到颜色本身透明度
            int pAlpha = Color.alpha(mRippleColor);
            int realAlpha= (int) (pAlpha*(mAlpha/255f));
            mPaint.setAlpha(realAlpha);
        }
        //刷新当前Drawable
        invalidateSelf();

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
         //颜色滤镜
        if (mPaint.getColorFilter()!=colorFilter){
            mPaint.setColorFilter(colorFilter);
            //刷新当前Drawable
            invalidateSelf();
        }
    }

    @Override
    public int getOpacity() {
        int alpha=mPaint.getAlpha();
        if (alpha==255){
            //不透明的drawable
            return PixelFormat.OPAQUE;
        }else  if (alpha==0){
            //全透明的drawable
             return PixelFormat.TRANSPARENT;
        }else{
            //半透明的drawable
            return PixelFormat.TRANSLUCENT;
        }
    }

    @Override
    public int getAlpha() {
        return mAlpha;
    }
    public  void onTouch(MotionEvent event){
//        mRippleRadius=mRippleRadius+10;
//        invalidateSelf();
        //判断点击操作
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                onTouchDown(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
    }
    private void onTouchDown(float x,float y){
        mRipplePointX=x;
        mRipplePointY=y;
        mRippleRadius=mRippleRadius+30;
        invalidateSelf();

    }
}
