package edu.gsu.httpcs.bricksbreakerapplication.resourcesView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.gsu.httpcs.bricksbreakerapplication.Level3Activity;


public class MyHandlerClass extends AppCompatActivity {
    private int number=0;
    public boolean handlerRunning=false;
    Level3Activity activity;
    private final int TIMER=123;
    Bundle handlerBundle;
    Handler handler=new Handler();//=new Handler(){

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Runnable activity=new Runnable() {
            @Override
            public void run() {
                MyHandlerCl();
                Log.d("fromhandlermsg",""+number);
            }
        };
        new Thread(activity).start();
    }
    public Thread thread;

  public void MyHandlerCl()
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),Level3Activity.class);
                    number = number++;
                handlerBundle=new Bundle();
                handlerBundle.putString("num"," "+number);
                i.putExtras(handlerBundle);
                startActivity(i);

            }
        });
    }
}


