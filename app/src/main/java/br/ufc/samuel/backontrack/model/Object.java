package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Object extends SugarRecord{
    @Ignore
    private transient Long id;
    private String name;

    public Object(){

    }

    public Object(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
