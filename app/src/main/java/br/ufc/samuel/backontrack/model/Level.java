package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Level extends SugarRecord{
    @Ignore
    private transient Long id;
    private int level;

    public Level(){

    }
    public Level(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
