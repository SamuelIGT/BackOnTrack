package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

import java.util.List;

public class Recommendation extends SugarRecord{
    private transient Long id;
    private List<Serie> serie;

    public Recommendation(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Serie> getSerie() {
        return serie;
    }

    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }

}
