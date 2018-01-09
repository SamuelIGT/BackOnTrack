package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

public class Object extends SugarRecord<Object>{
    private Long id;
    private String name;

    public Object(){

    }

    public Object(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
