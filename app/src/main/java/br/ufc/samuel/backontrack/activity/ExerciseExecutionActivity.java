package br.ufc.samuel.backontrack.activity;

import android.os.Environment;
import android.os.SystemClock;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.ufc.samuel.backontrack.util.Chronometer;

import br.ufc.samuel.backontrack.R;

public class ExerciseExecutionActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageView pgBarOutline;
    private ImageButton btnPgBar;
    private TextView timer;
    private MediaController mediaController;
    private Chronometer chronometer;
    private AnimatedVectorDrawableCompat avdPlayToStop;
    private AnimatedVectorDrawableCompat avdStopToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_execution);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);//disable the actionBar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//displays a back button on the actionBar

        findViews();
        mediaController = new MediaController(this); //instantiating the media controller
        mediaController.setAnchorView(videoView); //sets the the view that media controller will be anchored
        videoView.setMediaController(mediaController);

        chronometer = new Chronometer(timer, this);
        setUpButtons();
        initializeVectorDrawableAnimations();

        int intExtra = getIntent().getIntExtra(getString(R.string.LEVEL_NUMBER), -1);
        String videopath = Environment.getExternalStorageDirectory().getPath()+getString(R.string.exercise_videos_rootPath)+ intExtra +"/"+"ex"+(intExtra+1)+".mp4";
        videoView.setVideoPath(videopath);
        videoView.seekTo(100);


        Log.d("Selected Level: ", videopath);
//        mediaController.show();//shows the Media Controller HUD

    }

    private void initializeVectorDrawableAnimations() {
        avdPlayToStop = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_play_to_stop);
        avdStopToPlay = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_stop_to_play);
    }

    private void findViews() {
        videoView = findViewById(R.id.videoView);
        pgBarOutline = findViewById(R.id.imgView_pgBar_outline);
        btnPgBar = findViewById(R.id.btn_pgBar);
        timer = findViewById(R.id.timer);
    }

    private void setUpButtons() {
        btnPgBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chronometer.isRunning()){
                    Log.d("Chrnometer:","is not running");
                    chronometer.startTimer(SystemClock.uptimeMillis());
                    //btnPgBar.setImageResource(R.drawable.ic_stop);
                    btnPgBar.setImageDrawable(avdPlayToStop);
                    avdPlayToStop.start();//starts the stop icon transition.
                    //TODO: fazer animação da linha externa quando o chronômetro estiver rodando.
                }else {
                    Log.d("Chrnometer:","is running");
                    btnPgBar.setImageDrawable(avdStopToPlay);
                    avdStopToPlay.start();//starts the play icon transition.
                    chronometer.stopTimer();
                    //TODO:Mostrar dialog de finalização.
                    }
                }
        });
    }

    //Todo: Implementar modo fullscreen.
}
