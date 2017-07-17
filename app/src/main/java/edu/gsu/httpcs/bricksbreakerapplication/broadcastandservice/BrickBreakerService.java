package edu.gsu.httpcs.bricksbreakerapplication.broadcastandservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Timer;

/**
 * Created by thaku on 7/9/2017.
 */

public class BrickBreakerService extends Service {
        private MediaPlayer mediaPlayer = new MediaPlayer();
        public static final int LOADING = 11111;
        public static final int PLAYING = 22222;
        public static final int PAUSED = 33333;
        public static final int PLAY = 55555;
        public static final int PAUSE = 66666;

        public static final String ACTION = "TestAction";
        public static final String STOP = "StopAction";
        public static final String AUDIO = "audio";
        private Timer timer = new Timer();
        private String url = "";

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if (intent.getAction().equals("AUDIO")) {
                if (intent.getIntExtra("CMD", 0) == PLAY) {
                    String urlMsg = intent.getStringExtra("URL");
                    if (url.equals(urlMsg)) {
                        mediaPlayer.start();
                        sendPlaying();
                    } else {
                        try {
                            url = urlMsg;
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepareAsync();
                            sendLoading();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (intent.getIntExtra("CMD", 0) == PAUSE) {
                    mediaPlayer.pause();
                    sendPaused();
                }
            }
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    sendPlaying();
                }
            });
            return super.onStartCommand(intent, flags, startId);
        }
        public void sendPaused() {
            Intent intent = new Intent();
            intent.setAction(AUDIO);
            intent.putExtra("MSG", PAUSED);
            sendBroadcast(intent);
        }
        public void sendPlaying() {
            Intent intent = new Intent();
            intent.setAction(AUDIO);
            intent.putExtra("MSG", PLAYING);
            sendBroadcast(intent);
        }
        public void sendLoading() {
            Intent intent = new Intent();
            intent.setAction(AUDIO);
            intent.putExtra("MSG", LOADING);
            sendBroadcast(intent);
        }
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
