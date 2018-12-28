package br.ufc.samuel.backontrack.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import br.ufc.samuel.backontrack.fragments.ExerciseStartDialogFragment;
import br.ufc.samuel.backontrack.fragments.ExitAlertDialogFragment;
import br.ufc.samuel.backontrack.fragments.FeedbackDialogFragment;
import br.ufc.samuel.backontrack.model.Grasp;
import br.ufc.samuel.backontrack.model.Progress;
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
    private ValueAnimator pgBarOutlineAnim;
    private Grasp currentExercise;
    private Progress progress;
    private TextView toolbarTitle;

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

        //int intExtra = getIntent().getIntExtra(getString(R.string.LEVEL_NUMBER), -1);
        prepareVideo();
        //String videopath = Environment.getExternalStorageDirectory().getPath()+getString(R.string.exercise_videos_rootPath)+ intExtra +"/"+"ex"+(intExtra+1)+".mp4";
        toolbarTitle = findViewById(R.id.toolbarText);
        toolbarTitle.setText(currentExercise.getExercise().getTitle());

        showExerciseStartDialog();//shows the exercises start dialog
//        mediaController.show();//shows the Media Controller HUD
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void prepareVideo() {
        progress = Progress.findById(Progress.class, 1);
        long currentExerciseId = progress.getExercisesQueue().get(0);

        Log.d("CurrentExerciseID", "prepareVideo: "+ currentExerciseId);

        currentExercise = Grasp.find(Grasp.class, "id = ?", ""+currentExerciseId).get(0);

        Log.d("CurrentExerciseTitle", "prepareVideo: "+ currentExercise.getExercise().getTitle());
        Log.d("VideoPath", "prepareVideo: "+ currentExercise.getExercise().getMidia().getPathVideo());

        Uri videoUri = Uri.fromFile(new File(currentExercise.getExercise().getMidia().getPathVideo()));
        videoView.setVideoURI(videoUri);
        //videoView.setVideoPath(currentExercise.getExercise().getMidia().getPathVideo()); //TODO: Usar content Provider.
        videoView.seekTo(100);
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentByTag("Dialog Exit Alert") == null){
            String[] args = {getString(R.string.ARGS_FEEDBACK_DIALOG_ID), getString(R.string.ARGS_FEEDBACK_DIALOG_TIMER)};
            ExitAlertDialogFragment exitAlertDialogFragment = ExitAlertDialogFragment.newInstance(currentExercise.getId(), args, chronometer.getTimerSec());
        exitAlertDialogFragment.show(getSupportFragmentManager(), "Dialog Exit Alert");
        }else{
            super.onBackPressed();
        }
    }

    private void showExerciseStartDialog() {
        int numOfObjects = currentExercise.getExercise().getObjects().size();
        String[] objects = new String[numOfObjects];
        for(int i = 0; i < currentExercise.getExercise().getObjects().size(); i++){
            objects[i] = currentExercise.getExercise().getObjects().get(i).getName();
        }

        ExerciseStartDialogFragment dialogFragment = ExerciseStartDialogFragment.newInstance(objects, getString(R.string.ARGS_EXERCISE_START_DIALOG));
        dialogFragment.setCancelable(false);
        dialogFragment.show(getSupportFragmentManager(), "Dialog Exercise Start");
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
        pgBarOutlineAnim = animateDrawableColor(pgBarOutline);

        btnPgBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chronometer.isRunning()){
                    Log.d("Chrnometer:","is not running");
                    chronometer.startTimer(SystemClock.uptimeMillis());
                    //btnPgBar.setImageResource(R.drawable.ic_stop);
                    btnPgBar.setImageDrawable(avdPlayToStop);
                    avdPlayToStop.start();//starts the stop icon transition.
                    pgBarOutlineAnim.start();
                    //TODO: fazer animação da linha externa quando o chronômetro estiver rodando.
                }else {
                    Log.d("Chrnometer:","is running");
                    pgBarOutlineAnim.start();
                    pgBarOutlineAnim.pause();
                    btnPgBar.setImageDrawable(avdStopToPlay);
                    avdStopToPlay.start();//starts the play icon transition.
                    chronometer.stopTimer();
                    String[] args = {getString(R.string.ARGS_FEEDBACK_DIALOG_ID), getString(R.string.ARGS_FEEDBACK_DIALOG_TIMER)};
                    FeedbackDialogFragment feedbackDialogFragment = FeedbackDialogFragment.newInstance(currentExercise.getId(), args, chronometer.getTimerSec());
                    feedbackDialogFragment.show(getSupportFragmentManager(), "Dialog Feedback");
                    }
                }
        });
    }

    private ValueAnimator animateDrawableColor(final ImageView v) {
        final int color = getResources().getColor(R.color.timerProgressBarOutLineAnimation);
        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaColor = adjustAlpha(color, mul);
                v.getBackground().setColorFilter(alphaColor, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    v.getBackground().setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        return colorAnim;
    }
    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    //Todo: Implementar modo fullscreen.
}
