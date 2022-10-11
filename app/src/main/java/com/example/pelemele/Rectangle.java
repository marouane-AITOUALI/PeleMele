package com.example.pelemele;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Rectangle extends View {
    private Paint paint;
    private Canvas canvas;

    private float x1,x2,y1,y2;
    public Rectangle(Context context, Paint p, Canvas c, float x1, float y1, float x2, float y2) {
        super(context);
        paint = p;
        this.canvas = c;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        init();
    }

    public void init(){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setAlpha(40);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawRect(x1,y1,x2,y2,paint);
    }
}
