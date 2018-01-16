package br.ufc.samuel.backontrack.connection;

import java.util.List;

import br.ufc.samuel.backontrack.model.Level;
import br.ufc.samuel.backontrack.model.LevelExercises;
import br.ufc.samuel.backontrack.model.Midia;
import br.ufc.samuel.backontrack.model.Recommendation;
import br.ufc.samuel.backontrack.model.Serie;

/**
 * Created by samue on 13/01/2018.
 */

public class webServiceConnection {

    private LevelExercises levelOne =  new LevelExercises();
    private LevelExercises levelTwo =  new LevelExercises();
    private LevelExercises levelThree =  new LevelExercises();

    private Level lv1 = new Level(1);
    private Level lv2 = new Level(2);
    private Level lv3 = new Level(3);

    private Serie serie = new Serie(3, 10);

    private Recommendation recommendation = new Recommendation();
    //recommendation.setSerie();
    //private Midia midia1 =  new Midia("");

}
