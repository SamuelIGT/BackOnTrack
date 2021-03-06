package br.ufc.samuel.backontrack.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.activity.ExerciseExecutionActivity;
import br.ufc.samuel.backontrack.activity.LoginActivity;
import br.ufc.samuel.backontrack.connection.controller.PermissionController;
import br.ufc.samuel.backontrack.connection.controller.ReportController;
import br.ufc.samuel.backontrack.model.Exercise;
import br.ufc.samuel.backontrack.model.Grasp;
import br.ufc.samuel.backontrack.model.Level;
import br.ufc.samuel.backontrack.model.Midia;
import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Progress;
import br.ufc.samuel.backontrack.model.Recommendation;
import br.ufc.samuel.backontrack.model.Report;
import br.ufc.samuel.backontrack.model.Token;
import br.ufc.samuel.backontrack.util.DownloadUtils;
import br.ufc.samuel.backontrack.util.preferences.LevelPreferences;
import br.ufc.samuel.backontrack.util.showcase.CustomShowcaseView;


public class LevelsFragment extends Fragment{

    private ImageButton[] btnLv;
    private CircularProgressBar progressBar;
    private LevelPreferences levelPreferences;
    private boolean isFirstTime;
    private String[] levelStatus;
    private View rootView;
    private int currentDownloadingLevel;
    private List<Integer> downloadsCompleted;
    private Grasp[] grasp;
    private Progress userProgress;
    private int currentDownloadingExerciseIndex;
    private DownloadTask[] tasks;
    private float totalProgress;
    private FrameLayout loadingLayout;
    private String[] videosPath;

    public LevelsFragment() {
        // Required empty public constructor
    }

    public static LevelsFragment newInstance(int sectionNumber) {
        LevelsFragment fragment = new LevelsFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_levels, container, false);
        //finds the views desired
        findViews();

        downloadsCompleted = new ArrayList<>();

        if(!checkReportQueue()){
            new LevelDownloadTask().execute();
        }

        //TODO:Verificar se algum nivel foi desbloqueado(servidor).

        //TODO:Apagar arquivos do nivel anterior.

        //Sets up the icons of the levels
        setUpLevels();

        return rootView;
    }

    private void setUpLevels() {
        Context context = rootView.getContext();

        getPreferences();
        Log.d("IsFistTime", ""+levelPreferences.isFirstTime());
        //if its the fist time the app is running, creates a ShowcaseView and displays it.
        if(isFirstTime){
            Target target = new ViewTarget(btnLv[0]);
            Resources.Theme theme = getResources().newTheme();
            theme.applyStyle(R.style.CustomShowcaseTheme, true);
            new ShowcaseView.Builder(getActivity())
                    .setShowcaseDrawer(new CustomShowcaseView(getResources(), theme))
                    .setStyle(R.style.CustomShowcaseTheme)
                    .setTarget(target)
                    .setContentTitle(getString(R.string.ShowcaseScreen_Title))
                    .setContentText(R.string.ShowcaseScreen_Description)
                    .hideOnTouchOutside()
                    .build();
        }
        for(int i = 0; i <= 2; i++){
            updateLevelStatus(i);
            // setLevelButtonOnClickListener(i);
        }

    }

    private void updateLevelStatus(final int index) {
        String status = levelStatus[index];
        Context context = rootView.getContext();

        switch (index){
            case 0:
                if(context.getString(R.string.LV_STATUS_ENABLED).equals(status)){
                    btnLv[index].setImageResource(R.drawable.ic_lv1);

                    setLevelButtonClickListener(index);
                }
                else if(context.getString(R.string.LV_STATUS_DOWNLOAD).equals(status)){
                    btnLv[index].setImageResource(R.drawable.ic_lv1_download);
                    setLevelButtonClickListener(index);
                }
                else {
                    btnLv[index].setImageResource(R.drawable.ic_lv1_inactive);
                }
                break;

            case 1:
                if(context.getString(R.string.LV_STATUS_ENABLED).equals(status)){
                    btnLv[index].setImageResource(R.drawable.ic_lv2);
                    setLevelButtonClickListener(index);
                }
                else if(context.getString(R.string.LV_STATUS_DOWNLOAD).equals(status)){
                    btnLv[index].setImageResource(R.drawable.ic_lv2_download);
                    setLevelButtonClickListener(index);
                }
                else {
                    btnLv[index].setImageResource(R.drawable.ic_lv2_inactive);
                }
                break;

            case 2:
                if(context.getString(R.string.LV_STATUS_ENABLED).equals(status)){
                    btnLv[index].setImageResource(R.drawable.ic_lv3);
                    setLevelButtonClickListener(index);
                }
                else if(context.getString(R.string.LV_STATUS_DOWNLOAD).equals(status)){
                    btnLv[index].setImageResource(R.drawable.ic_lv3_download);
                    setLevelButtonClickListener(index);
                }
                else {
                    btnLv[index].setImageResource(R.drawable.ic_lv3_inactive);
                }
                break;

            default:
                break;

        }
    }

    private void downloadVideos() {
        //tasksFailed = new ArrayList<>();
        playProgressBarAnimation(false);

        /*DownloadTask[]*/ tasks = new DownloadTask[grasp.length];
        for(int i = 0; i < grasp.length; i++){
            tasks[i] = new DownloadTask.Builder(grasp[i].getExercise().getMidia().getPathVideo(), DownloadUtils.getParentFile(getContext(), getString(R.string.exercise_videos_rootPath)+""+(currentDownloadingLevel+1)+"/"))
                    .setFilename("exercise_"+ i + "_level_" + grasp[i].getLevel().getLevel()+".mp4")
                    .setMinIntervalMillisCallbackProcess(30)
                    .setPassIfAlreadyCompleted(false)
                    .build();
        }

        Log.d("DownloadVideos","enqueue");
        videosPath = new String[grasp.length];
        DownloadListener1 listener = createCustomDownloadLisneter();

        tasks[currentDownloadingExerciseIndex].enqueue(listener);

        //DownloadTask.enqueue(tasks, listener);

    }

    private void playProgressBarAnimation(boolean isReversed) {

        if(isReversed){
            Animation fadeout = AnimationUtils.loadAnimation(getContext(), R.anim.progressbar_fadeout);

            fadeout.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    progressBar.setVisibility(View.INVISIBLE);
                    btnLv[currentDownloadingLevel].startAnimation(playLevelTransitionAnimation(false));
                }
                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            progressBar.startAnimation(fadeout);
        }else {
            Animation fadein = AnimationUtils.loadAnimation(getContext(), R.anim.progressbar_fadein);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(fadein);
        }
    }

    private Animation playLevelTransitionAnimation(boolean isAppearing){
        if(isAppearing){
            currentDownloadingLevel = -1;
            return AnimationUtils.loadAnimation(getContext(), R.anim.level_icon_transition_appearing_anim);

        }else {

            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.level_icon_transition_disappearing_anim);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    updateLevelStatus(currentDownloadingLevel);
                    btnLv[currentDownloadingLevel].startAnimation(playLevelTransitionAnimation(true));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            return animation;
        }
    }

    private void setLevelButtonClickListener(final int index) {
        //if the Level is enabled
        if(rootView.getContext().getString(R.string.LV_STATUS_ENABLED).equals(levelStatus[index])){
            btnLv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(rootView.getContext(), ExerciseExecutionActivity.class);
//                    intent.putExtra(getString(R.string.LEVEL_NUMBER), index+1);
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivityForResult(intent, Activity.RESULT_FIRST_USER);//TODO: Definir uma constante especifica.
                }
            });
        }
        else if(rootView.getContext().getString(R.string.LV_STATUS_DOWNLOAD).equals(levelStatus[index])){
            btnLv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentDownloadingLevel = index;
                    btnLv[index].setEnabled(false);

                    if (ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("Tem permissão?  ", "SIM");

                        downloadVideos();

                    } else {
                        Log.d("Tem permissão?  ", "NÃO");
                        // Request permission from the user
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    }
                }
            });
        }else{
            btnLv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //empty to make onClick do nothing.
                }
            });
        }


    }

    private void getPreferences() {
        Context context = rootView.getContext();

        levelPreferences = new LevelPreferences(context);
        isFirstTime = levelPreferences.isFirstTime();

        //if the app is used for the first time, sets the default preferences
        if (isFirstTime){
            String[] preferences = {context.getString(R.string.LV_STATUS_DOWNLOAD), context.getString(R.string.LV_STATUS_DISABLED), context.getString(R.string.LV_STATUS_DISABLED)};
            levelStatus = preferences;
            levelPreferences.setLevelDefaults(preferences);//sets the preferences

        }else{//if is not the first time, gets the saved preferences
            //TODO: Só executar a linha abaixo se não tiver internet para verficar o nível atual com o servidor
            levelStatus = levelPreferences.getLevelStatusPreferences();
        }
        levelPreferences.setDefaults(false);//sets the firstTime preference as false
    }

    private void findViews() {
        btnLv = new ImageButton[]{
                rootView.findViewById(R.id.btn_lv1),
                rootView.findViewById(R.id.btn_lv2),
                rootView.findViewById(R.id.btn_lv3)};
        progressBar = rootView.findViewById(R.id.pgBar_lv1);
        loadingLayout = rootView.findViewById(R.id.layout_loading);
    }

    private DownloadListener1 createCustomDownloadLisneter(){
        DownloadListener1 listener = new DownloadListener1() {
            @Override
            public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {
                Log.d("DownloadVideos","Task " + task.getFilename() + " Started");

            }

            @Override
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {
                Log.d("DownloadVideos","retry");
            }

            @Override
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                float relativeProgress = ((float)currentOffset/totalLength);
                float progress = (relativeProgress / grasp.length);

                progressBar.setProgress(100 * (progress+totalProgress));
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {
                Log.d("taskEnd", "Task " + task.getFilename() + " - Expected: " + EndCause.COMPLETED.name() + "\ntaskEnd: " + cause.name());

                if (EndCause.COMPLETED.name().equals(cause.name())) {
                    downloadsCompleted.add(task.getId());
                    Log.d("DownloadVideos", "end1: " + downloadsCompleted.size() + " == " + grasp.length);
                    int index = Integer.parseInt(task.getFilename().split("_")[1]);
                    videosPath[index] = task.getFile().getPath();

                    if (downloadsCompleted.size() == grasp.length) {//Last video
                        downloadsCompleted.clear();
                        levelStatus[currentDownloadingLevel] = getString(R.string.LV_STATUS_ENABLED);

                        List<Long> exerciseQueue = new ArrayList<>();
                        for (int i = 0; i < grasp.length; i++) {
                            Log.d("filePath", "" + task.getFilename());

                            grasp[i].getExercise().getMidia().setPathVideo(videosPath[i]);
                            grasp[i].save();

                            Log.d("taskEnd", "Exercise added to queue: "+grasp[i].getExercise().getTitle());
                            exerciseQueue.add(grasp[i].getId());
                        }

                        if(userProgress == null){
                            userProgress = new Progress(exerciseQueue);
                        }else{
                            userProgress.setExercisesQueue(exerciseQueue);
                            userProgress.setMaxProgress(exerciseQueue.size());
                        }

                        userProgress.save();
//                        Log.d("UserProgress", "Exercise: "+Progress.findById(Progress.class, id));
                        levelPreferences.setLevelDefaults(levelStatus);//sets the preferences
                        playProgressBarAnimation(true);
                        btnLv[currentDownloadingLevel].setEnabled(true);
                        totalProgress = 0;

                    } else { //If still didn't finish all the videos
                        totalProgress = (float)(currentDownloadingExerciseIndex + 1) / (float)grasp.length;
                        currentDownloadingExerciseIndex++; //next exercise index
                        tasks[currentDownloadingExerciseIndex].enqueue(this); // starts exercise
                    }

                } else {
                    Log.d("DownloadVideosEndCause", "" + cause.name());
                    progressBar.setProgress(totalProgress);
                    task.enqueue(this);
                }
            }
        };
        return listener;
    }

    private boolean checkReportQueue(){
        List<Report> reportSubmissionQueue;

        userProgress = Progress.findById(Progress.class, 1);

        if(userProgress != null){
            reportSubmissionQueue = userProgress.getReportSubmissionQueue();

            if(reportSubmissionQueue != null){
                if(!reportSubmissionQueue.isEmpty()){
                    loadingLayout.setVisibility(View.VISIBLE);
                    new ReportUpload(reportSubmissionQueue).execute();
                    return true;
                }
            }
        }
        return false;

    }

    private void DeleteOldLevel(){
        Grasp.deleteAll(Grasp.class);
        Level.deleteAll(Level.class);
        Recommendation.deleteAll(Recommendation.class);
        Exercise.deleteAll(Exercise.class);
        Midia.deleteAll(Midia.class);

        //Deleting the whole folder
        File dir = DownloadUtils.getParentFile(getContext(), getString(R.string.exercise_videos_rootPath)+""+(currentDownloadingLevel+1)+"/");
        if(dir.isDirectory()){
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d("Permission_Number: ", ""+requestCode);
        switch (requestCode) {
            case 0:
                downloadVideos();
                //downloadLevel();
                //AsyncTask para fazer requisição dos dados.
                //new LevelDownloadTask(/*grasp*/).execute();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Activity.RESULT_FIRST_USER:
//                if(resultCode == Activity.RESULT_OK) {
                //int index = data.getIntExtra(getString(R.string.EMPTY_QUEUE_INTENT), 0);
                int index = Arrays.asList(levelStatus).indexOf(getString(R.string.LV_STATUS_ENABLED));
                levelStatus = levelPreferences.getLevelStatusPreferences();
                if (index >= 0) {
                    updateLevelStatus(index);
                }
//                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //---------------------------LEVEL DOWNLOAD ASYNCTASK---------------------------------------\\
    private class LevelDownloadTask extends AsyncTask<Void, Void, Void> {
        private Permition[] permitions;
        private String errorMessage;
        private Map<String, Permition[]> permitionResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            PermissionController controller = new PermissionController();
            permitionResponse = controller.getExercises(getContext());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String[] requestMessages = new String[]{
                    getString(R.string.PERMITION_REQUEST_SUCESS),
                    getString(R.string.PERMITION_REQUEST_ERRO1),
                    getString(R.string.PERMITION_REQUEST_ERRO2)};

            for(int i = 0; i < requestMessages.length; i++){
                permitions = permitionResponse.get(requestMessages[i]);

                if(permitions != null){
                    if(i != 0){//If is not a "Sucess" (first element of the vector)
                        Toast.makeText(rootView.getContext(), requestMessages[i], Toast.LENGTH_LONG);
                    }
                    break;
                }
            }

            if (permitions != null) {

                List<Grasp> graspsStored = Grasp.listAll(Grasp.class);
                if (graspsStored.size() > 0) {
                    Log.d("SUGAR ORM:", "NAO NULO");
                    if (permitions.length != graspsStored.size()) { //TODO: Vericando se os mesmos dados ja existem localmente. -Melhorar depois-
                        Grasp.deleteAll(Grasp.class); //delete all data stored previously.
                        manageResponseData();
                    }else{
                        grasp = new Grasp[graspsStored.size()];
                        graspsStored.toArray(grasp);
                        Log.d("GRASP SIZE:", "SIZE: "+grasp.length);

                    }
                } else {
                    Log.d("SUGAR ORM:", "NULO");
                    manageResponseData();
                }
            }else {
                Log.d("TOKEN EXPIRED/INVALID", "Please, try to log in again.");
                Token token = Token.findById(Token.class, 1);
                token.setToken(getString(R.string.TOKEN_EXPIRED));
                token.save();

                Intent intent = new Intent(rootView.getContext(), LoginActivity.class);
//                    intent.putExtra(getString(R.string.LEVEL_NUMBER), index+1);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                getActivity().finish();
            }
        }

        private boolean verifyLevelHasChanged(int currentLevel){
            Grasp existingGrasp = Grasp.findById(Grasp.class, 1);
            if(existingGrasp != null){
                if(existingGrasp.getLevel().getLevel() != currentLevel){
                    return true;
                }
            }
            return false;
        }

        private void manageResponseData() {
            boolean hasLevelChanged = false;

            if (permitions != null) {

                grasp = new Grasp[permitions.length];
                hasLevelChanged = verifyLevelHasChanged(permitions[0].getGrasp().getLevel().getLevel());
                if (hasLevelChanged){
                    DeleteOldLevel();

                }

                Log.d("PATIENT_SAVE", "NAME: "+permitions[0].getPatient().getName());
                permitions[0].getPatient().save();//saves the patient data.

                for (int i = 0; i < permitions.length; i++) {
                    grasp[i] = permitions[i].getGrasp();
                    grasp[i].getLatestUpdate();

                    grasp[i].save();

                }

                Log.d("GRASP_SIZE", "GRASP SIZE: "+grasp.length);

                int currentLevel = grasp[0].getLevel().getLevel() - 1;
                for (int i = 0; i < levelStatus.length; i++) {
                    if (i != currentLevel) {
                        levelStatus[i] = getContext().getString(R.string.LV_STATUS_DISABLED);
                    } else {
                        levelStatus[i] = getContext().getString(R.string.LV_STATUS_DOWNLOAD);
                    }
                }
                levelPreferences.setLevelDefaults(levelStatus);//sets the preferences
                updateLevelStatus(currentLevel);//updates the icons
            }
            else {
                Log.d("PERMITION_RESPONSE", "manageResponseData: Permition null");
            }
        }
    }


    //--------------------------------------------------------------------------------------------\\

    private class ReportUpload extends AsyncTask<Void, Void, Void>{
        private Boolean[] reportsSended;
        private List<Report> reportSubmissionQueue;

        public ReportUpload(List<Report> reportSubmissionQueue){
            this.reportSubmissionQueue = reportSubmissionQueue;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            reportsSended = new Boolean[reportSubmissionQueue.size()];
            ReportController controller = new ReportController();

            for (int i = 0; i < reportSubmissionQueue.size(); i++) {
                Log.d("REPORT_UPLOAD", "doInBackground: Enviando report");
                reportsSended[i] = controller.sendReport(reportSubmissionQueue.get(i), getContext());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (userProgress != null) {
                List<Report> submissionFailed = new ArrayList<>();
                for (int i = 0; i < reportsSended.length; i++) {
                    if (!reportsSended[0]) {
                        submissionFailed.add(reportSubmissionQueue.get(i));
                        Log.d("REPORT_UPLOAD", "onPostExecute: Envio do Report falhou!");
                    }
                }
                userProgress.setReportSubmissionQueue(submissionFailed);
                userProgress.save();

                loadingLayout.setVisibility(View.GONE);

            }

            new LevelDownloadTask().execute();
        }
    }
}


