package edu.gsu.httpcs.bricksbreakerapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

import edu.gsu.httpcs.bricksbreakerapplication.resourcesView.Brick;

public class Level1Activity extends AppCompatActivity implements View.OnTouchListener {
    public int screenX;
    public int screenY;
    public int chance=3;
    private BrickBreakerView1 breakoutView;
    private Bitmap steelBall;
    float ballX;
    float ballY;
    public long startTime;
    public long currRunTime=0;
    private Bitmap steelPaddle;
    float padX;

    boolean isPlaying=false;
    boolean isPaused=true;
    boolean hasLowScore=false;
    boolean onetime=false;

    private Paint paint;
    private Canvas canvas;
    private float xaVal=8;
    private float yVal=16;
    int score=0;
    private boolean padMove;

    public int getScore() {
        return score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        breakoutView=new BrickBreakerView1(this);
        setContentView(breakoutView);
        breakoutView.setOnTouchListener(this);
        steelBall= BitmapFactory.decodeResource(getResources(),R.mipmap.small_ball);
        steelPaddle=BitmapFactory.decodeResource(getResources(),R.mipmap.steel);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;

        ballX=screenX/2;
        ballY=screenY-132;

        padX=screenX/2-90;
        paint=new Paint();
    }
    @Override
    public void onPause() {
        super.onPause();
        breakoutView.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        breakoutView.Resume();
    }
    class BrickBreakerView1 extends SurfaceView implements Runnable {
        // This is our thread
        Thread gameThread = null;
        private SurfaceHolder holder;

        private RectF rectBall;
        private RectF rectpad;
        Brick[]bricks=new Brick[200];
        int numBricks=0;

        public BrickBreakerView1(Context context) {
            super(context);
            holder=getHolder();
            createBricksAndRestart();
        }
        public void createBricksAndRestart() {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int brickWidth = size.x/8;
            int brickHeight = size.y/14;
            ballX=screenX/2;
            ballY=screenY-132;
            padX=screenX/2-90;
            score=0;
            // Build a wall of bricks
            numBricks=0;
            for (int column = 0; column < 3; column++) {
                for (int row = 0; row < 6; row++) {
                    bricks[numBricks] = new Brick((row+1)*brickWidth, (column+1)*brickHeight,
                            (row+2)*brickWidth, (column+2)*brickHeight);
                    numBricks++;
                }
            }
        }

        @Override
        public void run() {
            while(isPlaying)
            {
                if(!holder.getSurface().isValid())
                {
                    continue;
                }

                if(!isPaused&&onetime) {
                    currRunTime = System.currentTimeMillis();
                }

                canvas=holder.lockCanvas();
                canvas.drawARGB(255,150,120,130);
                rectBall=new RectF();
                rectBall.set(ballX,ballY,ballX+20,ballY+20);
                canvas.drawBitmap(steelBall,ballX,ballY,null);
                rectpad=new RectF();
                rectpad.set(padX,screenY-100,padX+185,screenY-98);
                canvas.drawBitmap(steelPaddle,padX,screenY-100,null);
                paint.setTextSize(50);
                if(onetime) {
                    canvas.drawText("Chances " + chance + " Time elapsed: " + ((System.currentTimeMillis()-startTime) /
                            1000) + " sec  score: " + score, screenX / 2 - 400, 80, paint);
                }else {
                    canvas.drawText("Chances " + chance + " Time elapsed: " + ((currRunTime-startTime) /
                            1000) + " sec  score: " + score, screenX / 2 - 400, 80, paint);
                }
                paint.setColor(Color.RED);
                for (int i = 0; i < numBricks; i++) {
                    if (bricks[i].getVisibility()) {
                        canvas.drawRect(bricks[i].getRect(), paint);
                    }
                }
                if(score>=180) {
                    paint.setTextSize(60);
                    canvas.drawText("Time elapsed: "+ (currRunTime-startTime)/1000 +
                            " sec  Your score is: " + score, screenX / 2 - 100, screenY / 2 - 40, paint);
                    isPaused=true;
                    onetime=false;

                }else if(score<180&&isPaused&&hasLowScore&&chance==0)
                {
                    paint.setTextSize(70);
                    canvas.drawText("Time elapsed: "+(currRunTime-startTime)/1000 +
                            " sec   Your score is: "+ score,screenX/2-500,screenY/2,paint);
                    canvas.drawText("Tab to play again: ",screenX/2-300,screenY/2+80,paint);
                    onetime=false;
                    isPaused=true;
                }
                holder.unlockCanvasAndPost(canvas);
                update();
            }

        }
        public void update() {
            // Check for ball colliding with a brick
            if (!isPaused) {
                if (ballX <= 0 || ballX >= screenX - 70)
                    xaVal = -xaVal;

                if (ballY <= 0)
                    yVal = -yVal;

                for (int i = 0; i < numBricks; i++) {
                    if (bricks[i].getVisibility()) {
                        if (RectF.intersects(bricks[i].getRect(), rectBall)) {
                            bricks[i].setInvisible();
                            yVal = -yVal;
                            score = score + 10;

                            Random generator = new Random();
                            float answer = generator.nextFloat() + 1;

                            if (answer == 0) {
                                xaVal = xaVal * answer;
                                yVal = yVal * answer;
                            }
                        }
                    }
                }
                if(chance==0)
                {
                    chance=3;
                }
                if (RectF.intersects(rectpad, rectBall)) {
                    yVal = -yVal;
                }
                if (score >= 181) {
                    score = 180;
                    createBricksAndRestart();
                    pause();
                }
                if(ballY>screenY)
                {
                    isPaused=true;
                    chance--;
                }
                ballX += xaVal;
                ballY += yVal;
            }
        }

        public void pause()
        {
            isPlaying=false;
        }
        public void Resume()
        {
            isPlaying=true;
            gameThread=new Thread(this);
            gameThread.start();
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int action=event.getAction();
        float mov=0.0f;
        switch(action){
            case MotionEvent.ACTION_DOWN:
                if(isPaused)
                {
                    breakoutView.createBricksAndRestart();
                    if(!onetime)
                    {
                        startTime=System.currentTimeMillis();
                        onetime=true;
                    }
                }

                if(isPlaying)
                {
                    breakoutView.Resume();
                    isPaused=false;
                    padMove=true;
                    hasLowScore=true;
                }
                mov=event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                if(padMove) {
                    if (event.getRawX() > screenX / 2) {
                        mov = event.getRawX();
                        padX = padX + 40;

                    } else if (event.getRawX() < screenX / 2) {
                        mov = event.getRawX();
                        padX = padX - 40;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                padMove=false;
                break;
        }
        return true;
    }
}