package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Serie extends SugarRecord{
    @Ignore
    private transient Long id;
    private int sets;
    private int repeats;

    public Serie(){}

    public Serie(int sets, int repeats){
        this.sets = sets;
        this.repeats = repeats;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }
}
