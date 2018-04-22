package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class Progress extends SugarRecord {
    private List<Report> reportSubmissionQueue;
    private List<Long> exercisesQueue;
    private int maxProgress;

    public Progress(List<Long> exercisesQueue) {
        this.exercisesQueue = exercisesQueue;
        this.maxProgress = exercisesQueue.size();
        this.reportSubmissionQueue = new ArrayList<>();
    }

    public List<Report> getReportSubmissionQueue() {
        return reportSubmissionQueue;
    }

    public void setReportSubmissionQueue(List<Report> reportSubmissionQueue) {
        this.reportSubmissionQueue = reportSubmissionQueue;
    }

    public List<Long> getExercisesQueue() {
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
}
