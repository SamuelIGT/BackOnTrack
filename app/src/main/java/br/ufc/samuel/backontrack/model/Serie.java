package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

public class Serie extends SugarRecord{
    private Long id;
    private int sets;
    private int repeats;

    public Serie(){}

    public Serie(int sets, int repeats){
        this.sets = sets;
        this.repeats = repeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
