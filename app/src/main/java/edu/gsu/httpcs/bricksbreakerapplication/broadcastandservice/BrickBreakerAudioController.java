package edu.gsu.httpcs.bricksbreakerapplication.broadcastandservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


/**
 * Created by thaku on 7/5/2017.
 */

public class BrickBreakerAudioController {
    public final  static int AUDIOPLAYING=111;
    public final  static int AUDIOLOADING=222;
    public final  static int AUDIOPAUSED=333;
    public static int STATUS=AUDIOPAUSED;
    private  Context context;
    private  TestAudioReceiver testAudioReceiver;
    private AudioListener listener;

    public BrickBreakerAudioController(Context context, AudioListener listener)
    {
        this.context=context;
        this.listener=listener;

    }
    public  void play(String url)
    {
        Intent intent=new Intent(context, BrickBreakerService.class);
        intent.setAction("AUDIO");
        intent.putExtra("CMD",BrickBreakerService.PLAY);
        intent.putExtra("URL",url);
        context.startService(intent);
        initReceiver();
        
    }

    private void initReceiver() {
        testAudioReceiver=new TestAudioReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(BrickBreakerService.AUDIO);
        context.registerReceiver(testAudioReceiver,filter);
    }
    public void pause()
    {
        Intent intent=new Intent(context, BrickBreakerService.class);
        intent.setAction("AUDIO");
        intent.putExtra("CMD",BrickBreakerService.PAUSE);
        context.startService(intent);
        initReceiver();
    }
    public interface AudioListener{
        public void onPlaying();
        public void onLoading();
        public void onPaused();
    }

    public  class TestAudioReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(BrickBreakerService.AUDIO)) {
                int msg = intent.getIntExtra("MSG",0);
                switch(msg){
                    case BrickBreakerService.PLAYING:
                        STATUS=AUDIOPLAYING;
                        listener.onPlaying();
                        break;
                    case BrickBreakerService.PAUSED:
                        STATUS=AUDIOPAUSED;
                        listener.onPaused();
                        break;
                    case BrickBreakerService.LOADING:
                        STATUS=AUDIOLOADING;
                        listener.onLoading();
                        break;
                }
            }

        }
    }
}
