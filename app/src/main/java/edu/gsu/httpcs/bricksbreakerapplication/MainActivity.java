package edu.gsu.httpcs.bricksbreakerapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gsu.httpcs.bricksbreakerapplication.adapters.BrickBreakerFragmentPagerAdapter;
import edu.gsu.httpcs.bricksbreakerapplication.broadcastandservice.BrickBreakerAudioController;
import edu.gsu.httpcs.bricksbreakerapplication.dialog.CustomDialog;
import edu.gsu.httpcs.bricksbreakerapplication.fragments.AboutGameFragment;
import edu.gsu.httpcs.bricksbreakerapplication.fragments.HomeFragment;
import edu.gsu.httpcs.bricksbreakerapplication.fragments.LevelListFragment;

public class MainActivity extends AppCompatActivity{
    private ArrayList<Pair<String,Fragment>> list = new ArrayList<Pair<String,Fragment>>();
    private boolean isplaying=false;
    @BindView(R.id.playing_button)
    Button playBt;
    private BrickBreakerAudioController controller;
    private String audioUrl="";
    public void audioStr(String str)
    {this.audioUrl=str;}
    @OnClick(R.id.music_button)
    public void musicLists()
    {
        CustomDialog customDialog= new CustomDialog(this, new CustomDialog.ICustomDialogListener() {
            @Override
            public void displayResult(String msg) {
                Toast.makeText( MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                audioUrl=msg;
            }
        });
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();
    }

    @BindView(R.id.activity_fragment_pager_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.activity_advance_fragment_pager)
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // Pair.create("redFragment",new RedFragment());
        list.add(new Pair<String, Fragment>("Home", new HomeFragment()));
        list.add(new Pair<String, Fragment>("About", new AboutGameFragment()));
        list.add(new Pair<String, Fragment>("Play", new LevelListFragment()));
        //ViewPager advancePager=(ViewPager)findViewById(R.id.activity_advance_view_pager);
        BrickBreakerFragmentPagerAdapter adapter = new BrickBreakerFragmentPagerAdapter(this.getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        controller= new BrickBreakerAudioController(this, new BrickBreakerAudioController.AudioListener() {
            @Override
            public void onPlaying() {
                playBt.setText("Paused");
                playBt.setClickable(true);
            }

            @Override
            public void onLoading() {
                playBt.setText("Loading");
                playBt.setClickable(false);
            }

            @Override
            public void onPaused() {
                playBt.setText("Play");
                playBt.setClickable(true);
            }
        });
        playBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (BrickBreakerAudioController.STATUS){
                    case BrickBreakerAudioController.AUDIOPLAYING:
                        controller.pause();
                        break;
                    case BrickBreakerAudioController.AUDIOPAUSED:
                        controller.play(audioUrl);
                        break;
                    default:
                        break;
                }
                if(BrickBreakerAudioController.STATUS== BrickBreakerAudioController.AUDIOPAUSED)
                {
                    controller.play(audioUrl);
                }
            }
        });
    }
}

