package br.ufc.samuel.backontrack.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.SQLException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
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
import android.widget.ImageButton;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.activity.ExerciseExecutionActivity;
import br.ufc.samuel.backontrack.connection.controller.PermitionController;
import br.ufc.samuel.backontrack.model.Grasp;
import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Progress;
import br.ufc.samuel.backontrack.util.DownloadUtils;
import br.ufc.samuel.backontrack.util.preferences.LevelPreferences;
import br.ufc.samuel.backontrack.util.showcase.CustomShowcaseView;
//TODO: Remover tela de login da pilha de retorno

public class LevelsFragment extends Fragment{

    private ImageButton[] btnLv;
    //private ImageButton btnLv2;
    //private ImageButton btnLv3;
    private CircularProgressBar progressBar;
    private LevelPreferences levelPreferences;
    private boolean isFirstTime;
    private String[] levelStatus;
    private View rootView;
    private int currentDownloadingLevel;
    private List<Integer> downloadsCompleted;
    private Grasp[] grasp;
    private Progress userProgress;

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

        new LevelDownloadTask().execute();

        //TODO:Verificar se algum nivel foi desbloqueado(servidor).

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

    private void setLevelButtonOnClickListener(int index) {
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
                    setDownloadLevelButtonClickListener(index);
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
                    setDownloadLevelButtonClickListener(index);
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
                    setDownloadLevelButtonClickListener(index);
                }
                else {
                    btnLv[index].setImageResource(R.drawable.ic_lv3_inactive);
                }
                break;

            default:
                break;

        }
    }

    //Todo: esse método faz a mesma coisa que o método setLevelButtonClickListener. -Consertar-
    private void setDownloadLevelButtonClickListener(final int index) {
        btnLv[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDownloadingLevel = index;
                btnLv[index].setEnabled(false);

                if (ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Tem permissão?  ", "SIM");

                    downloadVideos();



                    //downloadLevel();
                    //TODO: chamar AsyncTask para fazer requisição dos dados.
                   // new LevelDownloadTask(/*grasp*/).execute();

                } else {
                    Log.d("Tem permissão?  ", "NÃO");
                    // Request permission from the user
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }


            }
        });


    }

    private void downloadVideos() {
        playProgressBarAnimation(false);

        DownloadTask[] tasks = new DownloadTask[grasp.length];
        for(int i = 0; i < grasp.length; i++){
            tasks[i] = new DownloadTask.Builder(grasp[i].getExercise().getMidia().getPathVideo(), DownloadUtils.getParentFile(getContext(), getString(R.string.exercise_videos_rootPath)+""+(currentDownloadingLevel+1)+"/"))
                    .setFilename("exercise_"+ i + "_level_" + grasp[i].getLevel().getLevel()+".mp4")
                    .setMinIntervalMillisCallbackProcess(30)
                    .setPassIfAlreadyCompleted(false)
                    .build();
        }
//        DownloadTask task = new DownloadTask.Builder("http://i9move.quixada.ufc.br/api/video/videoY.mp4",  DownloadUtils.getParentFile(getContext(),getString(R.string.exercise_videos_rootPath)+""+(currentDownloadingLevel+1)+"/" ))
//                 .setFilename("sdd.mp4")
//                 .setMinIntervalMillisCallbackProcess(30)
//                 .setPassIfAlreadyCompleted(false)
//                 .build();
        Log.d("DownloadVideos","enqueue");

        DownloadListener1 listener = createCustomDownloadLisneter();

        DownloadTask.enqueue(tasks, listener);

    }

    /*private void downloadLevel(*//*Grasp[] grasp*//*) {
//        this.grasp = grasp;
        playProgressBarAnimation(false);

        dm = new DownloadManagerPro(rootView.getContext().getApplicationContext());

        dm.init(getString(R.string.exercise_videos_rootPath)+(currentDownloadingLevel+1)+"/", 10, LevelsFragment.this);

        //Grasp grasp = new Grasp();
        List<Integer> taskTokenList = new ArrayList<>();

        //TODO: Adicionar path dos videos à fila.
        for (int i = 0; i < grasp.length; i++){
            Log.d("Downloading video path", "" + grasp[i].getExercise().getMidia().getPathVideo());

            if(grasp[i].getExercise().getMidia().getPathVideo() != null) {
                String videoUrl = grasp[i].getExercise().getMidia().getPathVideo();

                int taskToken = dm.addTask(*//*exercise title*//*  grasp[i].getId() + "_" + i, *//*video download path*//*videoUrl, true, false);
                taskTokenList.add(taskToken);
            }
        }
//        int taskToken1 = dm.addTask("ex1", "https://volafile.org/get/zrnq3LDLAwK1/6%20-%20Flex%C3%A3o%20de%20um%20bra%C3%A7o.mp4", true, false);
//        int taskToken2 = dm.addTask("ex2", "https://volafile.org/get/zrofqw-kBxdG/17%20-%20Gar%C3%A7om%20com%20o%20copo.mp4", true, false);
        downloadsCompleted = new ArrayList<>();
        try {
            dm.startQueueDownload(0, QueueSort.oldestFirst); //downloadTaskPerTime (the first parameter) cannot equals or higher than the number of tasks.
        } catch (QueueDownloadInProgressException e) {
            e.printStackTrace();
        }

    }*/

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
                    startActivity(intent);
                }
            });
        }
        else if(rootView.getContext().getString(R.string.LV_STATUS_DOWNLOAD).equals(levelStatus[index])){
            btnLv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO:Fazer download do nível.
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
    }

   /* //change the video path to the local path
    private void changeVideoPath(){
        List<ReportStructure> downloadList = dm.lastCompletedDownloads();
        for (ReportStructure rs: downloadList){
            int index = Integer.parseInt(rs.name.split("_")[1]);
            Log.d("path local: ", ""+rs.saveAddress);
            grasp[index].getExercise().getMidia().setPathVideo(rs.saveAddress);

        }
    }*/

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

/*

    //-----------------------------------DOWNLOAD MANAGER--------------------------------------\\

    @Override
    public void OnDownloadStarted(long taskId) {

    }

    @Override
    public void OnDownloadPaused(long taskId) {

    }

    @Override
    public void onDownloadProcess(long taskId, double percent, long downloadedLength) {
        final float progress = ((float)(percent / 100)) / grasp.length;
        Log.d("Download "+taskId+":", ""+((float)(percent / 100)));
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(progressBar.getProgress()+progress);
            }
        });

    }

    @Override
    public void OnDownloadFinished(long taskId) {

    }

    @Override
    public void OnDownloadRebuildStart(long taskId) {

    }

    @Override
    public void OnDownloadRebuildFinished(long taskId) {

    }

    @Override
    public void OnDownloadCompleted(long taskId) {
        if(downloadsCompleted == null){
            downloadsCompleted = new ArrayList<>();
        }

        downloadsCompleted.add(taskId);

        changeVideoPath();//change video path to local path
        List<Long> exerciseQueue = new ArrayList<>();
        if(downloadsCompleted.size() == grasp.length){
            downloadsCompleted = null;
            levelStatus[currentDownloadingLevel] = getString(R.string.LV_STATUS_ENABLED);

            for (int i = 0; i <= grasp.length; i++){
                grasp[i].save();

                exerciseQueue.add(grasp[i].getId());
            }

            userProgress = new Progress(exerciseQueue);
            userProgress.save();

            levelPreferences.setLevelDefaults(levelStatus);//sets the preferences
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    playProgressBarAnimation(true);
                    btnLv[currentDownloadingLevel].setEnabled(true);
                }
            });

        }
    }

    @Override
    public void connectionLost(long taskId) {
        Log.d("ERRO DE CONEXÃO: ", "conexão perdida!");
    }
*/



    //---------------------------LEVEL DOWNLOAD ASYNCTASK---------------------------------------\\
    private class LevelDownloadTask extends AsyncTask<Void, Void, Void> {
        Permition[] permitions;

        /*        public LevelDownloadTask(Grasp grasp){
                    this.grasp = grasp;
                }*/
        @Override
        protected Void doInBackground(Void... voids) {
            PermitionController controller = new PermitionController();
            permitions = controller.getExercises();

            Log.d("doINBackground:", " "+permitions[1].getGrasp().getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //downloadLevel(/*grasp*/);
            Grasp graspTest = Grasp.findById(Grasp.class, 1);
            if(graspTest != null){
                Log.d("SUGAR ORM:", "NAO NULO");
                if (permitions[0].getGrasp().getId() != graspTest.getId()) { //TODO: Vericando se os mesmos dados ja existem localmente. -Melhorar depois-
                    Grasp.deleteAll(Grasp.class); //delete all data stored previously.
                    manageResponseData();
                }
            }else{
                Log.d("SUGAR ORM:", "NULO");
                manageResponseData();
            }

            /*try {

                if (gras1 != null) {
                }else{
                    Log.d("GRASP!", "E NULL");
                }
            }catch(SQLException e){
                    Log.d("Nao existe!", "@@@");
                    manageResponseData();
            }*/
        }

        private void manageResponseData() {
            grasp = new Grasp[permitions.length];
            for (int i = 0; i < permitions.length; i++) {
                grasp[i] = permitions[i].getGrasp();
                grasp[i].getLatestUpdate();
               // Log.d("manageResponseData for","Iterator: "+i);
                //grasp[i].setId(Long.parseLong("1"));
                Log.d("manageResponseData for","OBJECT TO STRING: \n"+ permitions[i].getId()+"\n"+grasp[i].getLatestUpdate());

                grasp[i].save();
            }

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
    }

    private DownloadListener1 createCustomDownloadLisneter(){
        DownloadListener1 listener = new DownloadListener1() {
            @Override
            public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {
                Log.d("DownloadVideos","taskStart");
            }

            @Override
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {
                Log.d("DownloadVideos","retry");
            }

            @Override
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {
                Log.d("DownloadVideos","connected");

            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                //final float progress = ((float)currentOffset/totalLength);
                float relativeProgress = ((float)currentOffset/totalLength);
                float progress = (relativeProgress / grasp.length);
                Log.d("grasp", "grasp length: " + grasp.length + "\nrelativeProgress: " + relativeProgress);
                Log.d("Download "+task.getFilename()+":", ""+((progress)));

                progressBar.setProgress(progressBar.getProgress()+progress);
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {
                Log.d("taskEnd", "message Expected: " + EndCause.COMPLETED.name() + "\ntaskEnd: "+ cause.name());
                if (EndCause.COMPLETED.name().equals(cause.name())) {
                    /*if (downloadsCompleted == null) {
                        downloadsCompleted = new ArrayList<>();
                    }*/
                    downloadsCompleted.add(task.getId());
                    //changeVideoPath();//change video path to local path
                    Log.d("DownloadVideos", "end1: " + downloadsCompleted.size() + " == " + grasp.length);

                    if (downloadsCompleted.size() == grasp.length) {
                        Log.d("DownloadVideos", "end2.3");
                        downloadsCompleted.clear();
                        Log.d("DownloadVideos", "end2.4");
                        levelStatus[currentDownloadingLevel] = getString(R.string.LV_STATUS_ENABLED);
                        Log.d("DownloadVideos", "end3");
                        List<Long> exerciseQueue = new ArrayList<>();
                        for (int i = 0; i < grasp.length; i++) {
                            Log.d("filePath", ""+task.getFile().getPath());
                            grasp[i].getExercise().getMidia().setPathVideo(task.getFile().getPath());
                            grasp[i].save();

                            exerciseQueue.add(grasp[i].getId());
                        }
                        Log.d("DownloadVideos", "end4");
                        userProgress = new Progress(exerciseQueue);
                        userProgress.save();
                        levelPreferences.setLevelDefaults(levelStatus);//sets the preferences
                        Log.d("DownloadVideos", "end5");
                        playProgressBarAnimation(true);
                        btnLv[currentDownloadingLevel].setEnabled(true);

                        Log.d("DownloadVideos", "end-End");
                    }

                }else
                    Log.d("DownloadVideosEndCause", "" + cause.name());
            }
        };
        return listener;
    }
}


