package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProductsDrawImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(new CanvasCreator(this));
    }

    private class CanvasCreator extends View {

        private Paint paint;
        private Rect rect;
        private Canvas canvas;


        public CanvasCreator(Context context) {
            super(context);
            paint = new Paint();
            rect = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.canvas = canvas;
            setBackgroundColor(Color.BLACK);

            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(3);

            canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/6,100, paint);
            drawTriangle(canvas.getWidth()/2, canvas.getHeight()/3, 300 );
            drawRectangle(canvas.getWidth()/2, canvas.getHeight()/2, 200);

        }

        private void drawTriangle(int x, int y, int width) {
            paint.setColor(Color.GREEN);
            int halfWidth = width / 2;

            Path path = new Path();
            path.moveTo(x, y - halfWidth);
            path.lineTo(x - halfWidth, y + halfWidth);
            path.lineTo(x + halfWidth, y + halfWidth);
            path.lineTo(x, y - halfWidth);
            path.close();
            canvas.drawPath(path, paint);
        }

        private void drawRectangle(int x, int y, int radius) {
            paint.setColor(Color.RED);
            Rect rectangle = new Rect((int) (x - ((0.8) * radius)), (int) (y - ((0.6) * radius)), (int) (x + ((0.8) * radius)), (int) (y + ((0.6 * radius))));
            canvas.drawRect(rectangle, paint);
        }
    }
}