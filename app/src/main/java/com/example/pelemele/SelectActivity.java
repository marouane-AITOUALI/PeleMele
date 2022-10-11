package com.example.pelemele;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SelectActivity extends AppCompatActivity {

    private ImageView image;
    private Paint paint = new Paint();
    private Canvas canvas = new Canvas();
    private Rect rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        image = findViewById(R.id.imageToSelect);

        image.setOnTouchListener(new View.OnTouchListener(){
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 int pointerCount = motionEvent.getPointerCount();
                 Log.i("SelectActivity", "nombre de contacts: "+pointerCount);
                 if(pointerCount == 2 ){
                     float p1X = motionEvent.getX(0);
                     float p1Y = motionEvent.getY(0);
                     Point p1 = new Point((int) p1X, (int) p1Y);

                     float p2X = motionEvent.getX(1);
                     float p2Y = motionEvent.getY(1);
                     Point p2 = new Point((int) p2X, (int) p2Y);
                     Log.i("SelectActivity", "P1("+p1X+","+p1Y+"), P2("+p2X+","+p2Y+");");
                     //Rect r = new Rect(p1,p2);
                 }
                 return false;
             }
        }
        );
    }
}