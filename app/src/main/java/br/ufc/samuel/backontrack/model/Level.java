package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

public class Level extends SugarRecord{
    private Long id;
    private int level;

    public Level(){

    }
    public Level(int level){
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
