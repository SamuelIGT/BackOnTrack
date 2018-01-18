package br.ufc.samuel.backontrack.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.VideoView;

import br.ufc.samuel.backontrack.R;

public class ExerciseExecutionActivity extends AppCompatActivity {
private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_execution);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videoView = findViewById(R.id.videoView);
        int intExtra = getIntent().getIntExtra(getString(R.string.LEVEL_NUMBER), -1);
        String videopath = Environment.getExternalStorageDirectory().getPath()+getString(R.string.exercise_videos_rootPath)+ intExtra +"/"+"ex"+(intExtra+1)+".mp4";
        videoView.setVideoPath(videopath);
        videoView.seekTo(100);


        Log.d("Selected Level: ", videopath);
    }
}
