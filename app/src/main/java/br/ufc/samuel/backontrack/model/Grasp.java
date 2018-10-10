package br.ufc.samuel.backontrack.model;


import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class Grasp extends SugarRecord{

    private Long graspId;
    private Exercise exercise;
    private Level level;
    private Recommendation recommendation;
    private String tip;
    private Date latestUpdate;
    private int sequence;

    private Long permitionId;

    public Grasp() {
    }
/*
    public Grasp(Long graspId, Exercise exercise, Level level, Recommendation recommendation, String tip, Date updateDate, int sequence) {
        this.graspId = graspId;
        this.exercise = exercise;
        this.level = level;
        this.recommendation = recommendation;
        this.tip = tip;
        this.updateDate = updateDate;
        this.sequence = sequence;
    }

    public Grasp(Exercise exercise, Level level, String tip, int sequence){
        this.exercise = exercise;
        this.level = level;
        this.tip = tip;
        this.sequence = sequence;
    }*/
    public Long getGraspId() {
        return graspId;
    }

    public void setGraspId(Long graspId) {
        this.graspId = graspId;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(Date latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public int getSequence() { return sequence; }

    public void setSequence(int sequence) { this.sequence = sequence; }

    public Long getPermitionId() {
        return permitionId;
    }

    public void setPermitionId(Long permitionId) {
        this.permitionId = permitionId;
    }

    @Override
    public long save() {
        exercise.save();
        level.save();
        recommendation.save();

        return super.save();
    }
}
