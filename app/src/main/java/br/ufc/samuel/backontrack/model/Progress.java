package br.ufc.samuel.backontrack.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;

public class Progress extends SugarRecord {
    @Ignore
    private List<Report> reportSubmissionQueue;
    private String serializedReportsQueue;
    @Ignore
    private List<Long> exercisesQueue;
    private String serializedExerciseQueue;
    private Long currentExercise;

    private int maxProgress;

    public Progress(){}

    public Progress(List<Long> exercisesQueue) {
        this.exercisesQueue = exercisesQueue;
        this.maxProgress = exercisesQueue.size();
        this.reportSubmissionQueue = new ArrayList<>();
        //this.currentExercise = exercisesQueue.get(0);
    }

    public List<Report> getReportSubmissionQueue() {
        reportSubmissionQueue = new Gson().fromJson(serializedReportsQueue, new TypeToken<List<Report>>(){}.getType());
        if(reportSubmissionQueue == null){
            reportSubmissionQueue = new ArrayList<>();

        }
        return reportSubmissionQueue;
    }

    public void setReportSubmissionQueue(List<Report> reportSubmissionQueue) {
        this.reportSubmissionQueue = reportSubmissionQueue;
    }

    public List<Long> getExercisesQueue() {
        exercisesQueue = new Gson().fromJson(serializedExerciseQueue, new TypeToken<List<Long>>(){}.getType());

        return exercisesQueue;
    }

    public void setExercisesQueue(List<Long> exercisesQueue) {
        this.exercisesQueue = exercisesQueue;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

//    public Long getCurrentExercise() {
//        return currentExercise;
//    }
//
//    public void setCurrentExercise(Long currentExercise) {
//        this.currentExercise = currentExercise;
//    }

    @Override
    public long save() {
        Gson gson = new Gson();
        serializedExerciseQueue = gson.toJson(exercisesQueue);
        serializedReportsQueue = gson.toJson(reportSubmissionQueue);
        return super.save();
    }

}
