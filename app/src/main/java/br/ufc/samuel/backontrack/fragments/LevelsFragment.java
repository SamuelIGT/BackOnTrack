package br.ufc.samuel.backontrack.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.golshadi.majid.core.DownloadManagerPro;
import com.golshadi.majid.core.enums.QueueSort;
import com.golshadi.majid.report.exceptions.QueueDownloadInProgressException;
import com.golshadi.majid.report.listener.DownloadManagerListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;

import br.ufc.samuel.backontrack.ExerciseExecutionActivity;
import br.ufc.samuel.backontrack.util.showcase.CustomShowcaseView;
import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.util.preferences.LevelPreferences;
//TODO: Remover tela de login da pilha de retorno

public class LevelsFragment extends Fragment implements DownloadManagerListener {

    private ImageButton[] btnLv;
    //private ImageButton btnLv2;
    //private ImageButton btnLv3;
    private CircularProgressBar progressBar;
    private LevelPreferences levelPreferences;
    private boolean isFirstTime;
    private String[] levelStatus;
    private View rootView;
    private String currentDownloadingLevel;

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

        //TODO:Verificar se algum nivel foi desbloqueado(servidor).

        //Sets up the icons of the levels
        setUpLevels();

        return rootView;
    }


    private void setUpLevels() {
        Context context = rootView.getContext();

        getPreferences();

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
                }
                else {
                    btnLv[index].setImageResource(R.drawable.ic_lv3_inactive);
                }
                break;

            default:
                break;

        }
    }

    private void setDownloadLevelButtonClickListener(final int index) {
        btnLv[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDownloadingLevel = "/exercisesVideos/level"+(index+1)+"/";
                progressBar.setVisibility(View.VISIBLE);

                if (ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Tem permissão?  ", "SIM");

                    downloadLevel();

                } else {
                    Log.d("Tem permissão?  ", "NÃO");
                    // Request permission from the user
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }


            }
        });

    }

    private void downloadLevel() {
        DownloadManagerPro dm = new DownloadManagerPro(rootView.getContext().getApplicationContext());

        dm.init(currentDownloadingLevel, 10, LevelsFragment.this);

        int taskToken1 = dm.addTask("ex1", "https://volafile.org/get/xrYSKEhvhszK/6%20-%20Flex%C3%A3o%20de%20um%20bra%C3%A7o.mp4", true, false);
        int taskToken2 = dm.addTask("ex2", "https://volafile.org/get/xrYea9IwZmBP/17%20-%20Gar%C3%A7om%20com%20o%20copo.mp4", true, false);
        try {
            dm.startQueueDownload(1, QueueSort.oldestFirst);

        } catch (QueueDownloadInProgressException e) {
            e.printStackTrace();
        }
    }

    private void setLevelButtonClickListener(final int index) {
       //if the Level is enabled
        if(rootView.getContext().getString(R.string.LV_STATUS_ENABLED).equals(levelStatus[index])){
            btnLv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(rootView.getContext(), ExerciseExecutionActivity.class);
                    intent.putExtra(getString(R.string.LEVEL_NUMBER), index+1);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                downloadLevel();
        }
    }


    //-----------------------------------DOWNLOAD MANAGER--------------------------------------\\

    @Override
    public void OnDownloadStarted(long taskId) {

    }

    @Override
    public void OnDownloadPaused(long taskId) {

    }

    @Override
    public void onDownloadProcess(long taskId, double percent, long downloadedLength) {
        final float progress = ((float)(percent / 100)) / 2;
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

    }

    @Override
    public void connectionLost(long taskId) {

    }
}
