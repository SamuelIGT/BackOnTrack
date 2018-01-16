package br.ufc.samuel.backontrack.model;

import java.util.List;

/**
 * Created by samue on 13/01/2018.
 */

public class LevelExercises {
    private List<Grasp> exercises;

    public LevelExercises(List<Grasp> exercises) {
        this.exercises = exercises;
    }

    public LevelExercises() {
    }

    public List<Grasp> getExercises() {
        return exercises;
    }

    public void setExercises(List<Grasp> exercises) {
        this.exercises = exercises;
    }
}
