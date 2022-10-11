package com.example.pelemele;

import android.graphics.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SelectActivity extends AppCompatActivity {

    private ImageView image;
    //private Layout
    private Paint paint = new Paint();
    private Canvas canvas = new Canvas();
    private Rect rect;
    private SurfaceView surface;

    private RelativeLayout layout;
    private float p1X, p1Y, p2X, p2Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        image = findViewById(R.id.imageToSelect);

        surface = findViewById(R.id.surfaceView);
        surface.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surface.setZOrderOnTop(true);

        layout = findViewById(R.id.layout);


        image.setOnTouchListener(new View.OnTouchListener(){
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 int pointerCount = motionEvent.getPointerCount();
                 Log.i("SelectActivity", "nombre de contacts: "+pointerCount);
                 if(pointerCount == 2 ){
                     if(motionEvent.getActionIndex() == MotionEvent.ACTION_DOWN){
                         p1X = motionEvent.getX(0);
                         p1Y = motionEvent.getY(0);
                     }
                     else{
                         p2X = motionEvent.getX(1);
                         p2Y = motionEvent.getY(1);

                     }

                     Rectangle rect = new Rectangle(getApplicationContext(),paint,canvas, p1X,p1Y,p2X,p2Y);
                     layout.addView(rect);







                     Log.i("SelectActivity", "P1("+p1X+","+p1Y+"), P2("+p2X+","+p2Y+");");
                     //Rect r = new Rect(p1,p2);
                 }
                 return true;
             }
        }
        );
    }
}